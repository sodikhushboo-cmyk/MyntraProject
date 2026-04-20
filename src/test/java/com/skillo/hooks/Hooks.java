package com.skillo.hooks;

import com.skillo.base.Keyword;
import com.skillo.utils.App;
import com.skillo.utils.ScreenshotUtil;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

import io.qameta.allure.Allure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Hooks {

    private static final Logger LOG = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {

        String scenarioName = scenario.getName();

        LOG.info("========== SCENARIO START ==========");
        LOG.info("Starting Scenario: {}", scenarioName);

        try {
            String browser = App.browser();
            String url = App.url();

            LOG.info("Opening browser: {}", browser);
            Keyword.openBrowser(browser);

            LOG.info("Launching URL: {}", url);
            Keyword.launchUrl(url);

        } catch (Exception e) {
            LOG.error("Failed during setup for scenario: {}", scenarioName, e);
            throw e;
        }
    }

    @After
    public void tearDown(Scenario scenario) {

        String scenarioName = scenario.getName();

        try {

            if (scenario.isFailed()) {

                LOG.error("❌ SCENARIO FAILED: {}", scenarioName);

                // 📸 Attach Screenshot
                try {
                    byte[] screenshot = ScreenshotUtil.takeScreenshot();

                    Allure.addAttachment(
                            "Failure Screenshot",
                            new ByteArrayInputStream(screenshot)
                    );

                    LOG.info("Screenshot attached to Allure");

                } catch (Exception e) {
                    LOG.error("Failed to capture screenshot", e);
                }

                // 📜 Attach LOG FILE  ✅ YOUR QUESTION ANSWER
                try {
                    byte[] logFile = Files.readAllBytes(Paths.get("logs/automation.log"));

                    Allure.addAttachment(
                            "Execution Logs",
                            "text/plain",
                            new ByteArrayInputStream(logFile),
                            ".log"
                    );

                    LOG.info("Log file attached to Allure");

                } catch (Exception e) {
                    LOG.error("Failed to attach log file", e);
                }

            } else {
                LOG.info("✅ SCENARIO PASSED: {}", scenarioName);
            }

        } catch (Exception e) {
            LOG.error("Error while evaluating scenario result: {}", scenarioName, e);
        }

        // Driver cleanup
        try {
            if (Keyword.getDriver() != null) {

                LOG.info("Closing browser for scenario: {}", scenarioName);

                Keyword.getDriver().quit();
                Keyword.unload();

                LOG.info("Browser closed successfully");
            }

        } catch (Exception e) {
            LOG.error("Error while closing browser for scenario: {}", scenarioName, e);
        }

        LOG.info("========== SCENARIO END ==========\n");
    }
}
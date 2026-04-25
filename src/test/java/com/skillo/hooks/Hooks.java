package com.skillo.hooks;

import java.time.Duration;

import com.skillo.base.Keyword;
import com.skillo.utils.App;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {

    private static final Logger LOG = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {

        LOG.info("Starting Scenario: {}", scenario.getName());

        // ✅ Open browser from config
        Keyword.openBrowser(App.browser());

        // ✅ Waits for stability
        Keyword.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Keyword.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        // ✅ Launch application
        Keyword.launchUrl(App.url());
    }

    @After
    public void tearDown(Scenario scenario) {

        LOG.info("Ending Scenario: {}", scenario.getName());

        // 📸 Screenshot handling (PASS + FAIL)
        if (scenario.isFailed()) {

            byte[] screenshot = Keyword.takeScreenshot(scenario.getName(), "failed");

            if (screenshot != null) {
                scenario.attach(screenshot, "image/png", "FAILED Screenshot");
            }

        } else {

            byte[] screenshot = Keyword.takeScreenshot(scenario.getName(), "passed");

            if (screenshot != null) {
                scenario.attach(screenshot, "image/png", "PASSED Screenshot");
            }
        }

        // ✅ Always close browser
        Keyword.closeBrowser();
    }
}
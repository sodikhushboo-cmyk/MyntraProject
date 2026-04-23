package com.skillo.hooks;

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

        Keyword.openBrowser(App.browser());
        Keyword.launchUrl(App.url());
    }

    @After
    public void tearDown(Scenario scenario) {

        LOG.info("Ending Scenario: {}", scenario.getName());

        Keyword.closeBrowser();   // ✅ updated
    }
}
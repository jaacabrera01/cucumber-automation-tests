package com.connectos.qa.core;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    @Before
    public void setUp() {
        DriverManager.createDriver();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}

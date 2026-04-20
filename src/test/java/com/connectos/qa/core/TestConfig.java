package com.connectos.qa.core;

public final class TestConfig {
    private static final String DEFAULT_FIXTURE_BASE_URL =
            "file://" + System.getProperty("user.dir") + "/src/test/resources/the-internet-fixtures";
    private static final String BASE_URL =
            System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", DEFAULT_FIXTURE_BASE_URL));

    private TestConfig() {
    }

    public static String baseUrl() {
        return BASE_URL;
    }

    public static boolean isFileBased() {
        return BASE_URL.startsWith("file://");
    }
}

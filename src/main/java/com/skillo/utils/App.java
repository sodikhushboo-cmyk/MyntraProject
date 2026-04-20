package com.skillo.utils;

	
public class App {

    static PropUtil app =
            new PropUtil("./src/main/resources/app.properties");

    public static String browser() {

        return app.get("browser");
    }

    public static String url() {

        return app.get("app.url");
    }

    public static String loginUrl() {

        return app.get("login.url");
    }

    public static String timeout() {

        return app.get("timeout");
    }
}
package com.example.syshotel.config;

public class Config {
    public String URL = ConfigurationRoute.protocol + "://" + ConfigurationRoute.route + ":" + ConfigurationRoute.port;
    public String ViaCep = ConfigurationRouteApiCep.protocol + "://" +  ConfigurationRouteApiCep.route;


    static class ConfigurationRoute {
        public static String protocol = "http";
        public static String route = "127.0.0.1";
        public static Integer port = 8080;
    }

    static class ConfigurationRouteApiCep{
        public static String protocol = "https";
        public static String route = "viacep.com.br/ws/";
    }

    public static class ConfigurationApi {
        public static String routeClient = "/client";
        public static String routeLogin = "/user";
    }
}

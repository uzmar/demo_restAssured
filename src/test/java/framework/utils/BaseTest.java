package framework.utils;


import java.util.HashMap;
import java.util.Map;


public class BaseTest extends BusinessUtils {

    protected static String BASE_URL;
    protected static String AUTH_HEADER_TOKEN;

    protected static String ROUTE_PATH;
    protected static Map<String, String> headers;


    public static void setup() throws NoSuchFieldException {
        String env = System.getProperty("Env");
        setEnvironment(env);
    }

    public static void setEnvironment(String env) throws NoSuchFieldException {
        setBaseUrlAndAuthToken(env);
        headers = getHeaders();
        System.setProperty("https.protocols", "TLSv1.3");
        System.setProperty("https.protocols", "SSLv3");
        System.setProperty("jdk.tls.client.cipherSuites", "TLS_RSA_WITH_AES_128_CBC_SHA256");
    }

    public static String setBaseUrlAndAuthToken(String env) throws NoSuchFieldException {
        if (env.equalsIgnoreCase("Dev")) {
            BASE_URL = readConfigurationFile("DEV_URL");
            AUTH_HEADER_TOKEN = System.getenv("AUTH_TOKEN_DEV");
        } else if (env.equalsIgnoreCase("Staging")) {
            BASE_URL = readConfigurationFile("STAGING_URL");
            AUTH_HEADER_TOKEN = System.getenv("AUTH_TOKEN_STAGING");
        } else {
            System.out.println("Invalid  Environment :" + env);
        }
        return BASE_URL;
    }

    public static Map<String, String> getHeaders() throws NoSuchFieldException {
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", AUTH_HEADER_TOKEN);
        return map;
    }


}

package framework.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class BusinessUtils {

    protected static final Configuration configuration = Configuration.builder()
            .jsonProvider(new JacksonJsonNodeJsonProvider())
            .mappingProvider(new JacksonMappingProvider())
            .build();

    /****************************************************************************************************************************************************
     Function Name : readJson
     Description : Reads a JSON file
     Parameters : Json file name
     Return Value : Return JSON object
     @throws Exception
     *****************************************************************************************************************************************************/

    public static JSONObject readJson(String filename) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject data = (JSONObject) parser.parse(new FileReader(filename));  //path to the JSON file.
        String json = data.toJSONString();
        return data;
    }

    /****************************************************************************************************************************************************
     Function Name : randomAlphaNumeric
     Description : Generates a randomAlphaNumeric string
     Parameters : Length of the string
     Return Value : Return randomAlphaNumeric string
     *****************************************************************************************************************************************************/
    public static String randomAlphaNumeric(int count) {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJ3456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    /****************************************************************************************************************************************************
     Function Name : modifyJson
     Description : Reads the json file and returns the payload changing the values provided through the map
     Parameters : HashMap of key fields and values, JSON filename
     Return Value : Return the String of JSON payload
     * @throws Exception
     *****************************************************************************************************************************************************/
    public static String modifyJson(Map<String, String> map, String fileName) throws Exception {
        JSONObject payload = BusinessUtils.readJson(System.getProperty("user.dir") + "/src/test/resources/" + fileName);
        String originalJson = payload.toString();
        for (Map.Entry m : map.entrySet()) {
            JsonNode updatedJson = JsonPath.using(configuration).parse(originalJson).set("$." + m.getKey(), m.getValue()).json();
            originalJson = updatedJson.toString();
        }
        return originalJson;
    }

    /****************************************************************************************************************************************************
     Function Name : readConfigurationFile
     Description : Reads the Configuration file and returns the value of the property with the specified key in the property list.
     Parameters : Key.
     Return Value : Return the value of the property with the specified key in the property list.
     * @throws NoSuchFieldException
     *****************************************************************************************************************************************************/
    public static String readConfigurationFile(String key) throws NoSuchFieldException {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(System.getProperty("user.dir") + "/src/test/resources/Config.properties"));
            String value = properties.getProperty(key).trim();

            return value;
        } catch (Exception e) {
            throw new NoSuchFieldException("Cannot find key: " + key + " in Config file.");
        }
    }

    /****************************************************************************************************************************************************
     Function Name : getDateTimeStamp
     Description :
     Return Value :
     *****************************************************************************************************************************************************/
    public String getDateTimeStamp() {
        // TODO: Add the UTC date format
        DateFormat dateFormat = new SimpleDateFormat("");
        String strDate = dateFormat.format(new Date());
        return strDate;
    }

    public static String encodeToBase64(String text) {
        byte[] encoded = Base64.getEncoder().encode(text.getBytes());
        return new String(encoded);
    }

    public static String decodeFromBase64(String text) {
        byte[] decoded = Base64.getDecoder().decode(text.getBytes());
        return new String(decoded);
    }
}

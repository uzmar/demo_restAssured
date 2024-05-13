package framework.utils;

import java.util.HashMap;
import java.util.Map;

public class userUtils {
    public static String getCreateUsersJson() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        return BusinessUtils.modifyJson(map, "data/createUsers.json");
    }
}

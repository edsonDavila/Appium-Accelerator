package common.cmdSetup;

import java.util.Map;

public class EnvMap {
    public static Map<String, String> env = System.getenv();

    public static void EnvMap () {
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                    envName,
                    env.get(envName));
        }
    }

}
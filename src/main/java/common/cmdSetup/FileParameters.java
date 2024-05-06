package common.cmdSetup;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;

public class FileParameters {

    public static HashMap<String, String> loadParameter (String file) throws IOException {
        System.out.println("file: " + file);
        File paramsFile = Paths.get(System.getProperty("user.dir"), "/TestMobileParameters", file).toFile();
        BufferedReader fileReader = new BufferedReader(new FileReader(paramsFile));
        HashMap<String,String> parameters = new HashMap<>();
        String line;
        while ((line = fileReader.readLine()) != null){
            String[] lineSplited = line.split("=");
            parameters.put(lineSplited[0],lineSplited[1]);
        }
        return parameters;
    }

}
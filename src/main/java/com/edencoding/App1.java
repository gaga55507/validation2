package com.edencoding;
import java.util.Properties;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class App1 {

    public static void main() {

        try (OutputStream output = new FileOutputStream("src/main/java/com/edencoding/pop.properties")) {

            Properties prop = new Properties();
            Path currentDir = Paths.get("");
            System.out.println(currentDir.toAbsolutePath());
     
    
            String pathsRESULTATPDF = currentDir.toAbsolutePath().toString() + "/Resultats PDF";
            String pathsARCHIVE = currentDir.toAbsolutePath().toString() + "/ARCHIVE";

            // set the properties value
         
            prop.setProperty("RESULTATPDF", pathsRESULTATPDF);
            prop.setProperty("ARCHIVE", pathsARCHIVE);

            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
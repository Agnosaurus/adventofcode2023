package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtils {

    /**
     *
     * @param filename path to the file resource
     * @return List of lines within the file
     */
    public List<String> readFile(String filename){
        List<String> result = new ArrayList<>();
        URL resource = getClass().getClassLoader().getResource(filename);

        try {
            File file = new File(resource.toURI());
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                br.lines().forEach(result::add);
            }
        } catch ( Exception e){
            e.printStackTrace();
        }

        return result;

    }

}

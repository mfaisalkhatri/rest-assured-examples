package fileupload;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Faisal Khatri
 * @since 1/3/2023
 **/
public class FileReader {

    public File fileToUpload (String fileName) {
        URL resource = getClass ().getClassLoader ()
            .getResource (fileName);
        if (resource == null) {
            throw new IllegalArgumentException ("file not found! " + fileName);
        }
        try {
            return new File (resource.toURI ());
        } catch (URISyntaxException e) {
            throw new Error ("File not found!!");
        }
    }
}
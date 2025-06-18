package restfulecommerce.endtoend;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class FileReader {

    public static File getFile (String fileName) {
        URL resource = FileReader.class.getClassLoader ()
            .getResource (fileName);
        if (resource == null) {
            throw new IllegalArgumentException ("File not found!!" + fileName);
        }

        try {
            System.out.println ("File found!" + fileName);
            return new File (resource.toURI ());
        } catch (URISyntaxException e) {
            throw new IllegalStateException ("Invalid URI Syntax for file" + fileName);
        }
    }
}

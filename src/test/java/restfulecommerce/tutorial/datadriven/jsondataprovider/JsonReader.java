package restfulecommerce.tutorial.datadriven.jsondataprovider;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class JsonReader {

    public static List<Map<String, Object>> getOrderData (String fileName) {
        InputStream inputStream = JsonReader.class.getClassLoader ()
            .getResourceAsStream (fileName);

        if (inputStream == null) {
            throw new RuntimeException ("File not found: " + fileName);
        }
        try (
            Reader reader = new InputStreamReader (inputStream)) {

            Type listType = new TypeToken<List<Map<String, Object>>> () {
            }.getType ();
            return new Gson ().fromJson (reader, listType);

        } catch (IOException e) {
            throw new RuntimeException ("File not found: " + fileName);
        }
    }
}
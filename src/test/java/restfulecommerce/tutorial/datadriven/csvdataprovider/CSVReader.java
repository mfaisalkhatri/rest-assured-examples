package restfulecommerce.tutorial.datadriven.csvdataprovider;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.SneakyThrows;

public class CSVReader {
    @SneakyThrows
    public static List<Order> getOrderData (String filename) {
        InputStream inputStream = CSVReader.class.getClassLoader ()
            .getResourceAsStream (filename);
        if (inputStream == null) {
            throw new RuntimeException ("File not found: " + filename);
        }

        CsvSchema schema = CsvSchema.emptySchema ()
            .withHeader ();
        MappingIterator<Order> iterator;

        try {
            iterator = new CsvMapper ().readerFor (Order.class)
                .with (schema)
                .readValues (inputStream);

        } catch (IOException e) {
            throw new RuntimeException (e);
        }
        return iterator.readAll ();
    }
}
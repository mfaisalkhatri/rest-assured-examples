package restfulecommerce.tutorial.datadriven.csvdataprovider;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.SneakyThrows;

public class CSVReader {
    @SneakyThrows
    public static List<Order> getOrderData (File file) {
        CsvMapper csvMapper = new CsvMapper ();
        CsvSchema schema = csvMapper.schemaFor (Order.class)
            .withHeader ();
        MappingIterator<Order> iterator;

        try {
            iterator = csvMapper.readerFor (Order.class)
                .with (schema)
                .readValues (file);

        } catch (IOException e) {
            throw new RuntimeException (e);
        }
        return iterator.readAll ();
    }
}

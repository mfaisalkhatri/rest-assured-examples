package restfulecommerce.tutorial.datadriven.jsondataprovider.pojoimplementation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Order {
    private String user_id;
    private String product_id;
    private String product_name;
    private double product_amount;
    private int    qty;
    private double tax_amt;
    private double total_amt;
}

package restfulecommerce.tutorial.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Orders {
    private String user_id;
    private String product_id;
    private String product_name;
    private double product_amount;
    private int    qty;
    private double tax_amt;
    private double total_amt;
}

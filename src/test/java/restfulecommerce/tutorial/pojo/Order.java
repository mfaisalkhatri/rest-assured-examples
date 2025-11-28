package restfulecommerce.tutorial.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private int    id;
    @JsonProperty ("user_id")
    private String userId;
    @JsonProperty ("product_id")
    private String productId;
    @JsonProperty ("product_name")
    private String productName;
    @JsonProperty ("product_amount")
    private int    productAmount;
    private int    qty;
    @JsonProperty ("tax_amt")
    private int    taxAmt;
    @JsonProperty ("total_amt")
    private int    totalAmt;
}

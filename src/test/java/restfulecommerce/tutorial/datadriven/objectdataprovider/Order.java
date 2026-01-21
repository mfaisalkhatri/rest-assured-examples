package restfulecommerce.tutorial.datadriven.objectdataprovider;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString (exclude = "expectedStatus")
public class Order {
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
    private int    expectedStatus;
}

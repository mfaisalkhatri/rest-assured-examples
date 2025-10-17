package restfulecommerce.tutorial.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonPropertyOrder ({ "user_id", "product_id", "product_name", "product_amount", "qty", "tax_amt", "total_amt" })
public class OrderData {
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
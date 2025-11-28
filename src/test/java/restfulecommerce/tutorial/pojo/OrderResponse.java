package restfulecommerce.tutorial.pojo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
    private String      message;
    private List<Order> orders;
    
}

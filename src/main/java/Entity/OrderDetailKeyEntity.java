package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Embeddable
public class OrderDetailKeyEntity implements Serializable {

    @Column(name = "order_id")
    String orderId;
    @Column(name = "product_id")
    String productId;
}

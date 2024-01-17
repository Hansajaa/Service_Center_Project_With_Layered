package Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;




@NoArgsConstructor
@Setter
@Getter
@Entity(name = "OrderDetail")
public class OrderDetailEntity {

    @EmbeddedId
    OrderDetailKeyEntity id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_Id", nullable = false)
    OrdersEntity orders;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_Id", nullable = false)
    ProductEntity product;

    private String itemName;

    public OrderDetailEntity(String itemName) {
        this.itemName = itemName;
    }
}

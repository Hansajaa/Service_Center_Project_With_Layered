package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;



@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "OrderDetail")
public class OrderDetailEntity {

    @EmbeddedId
    OrderDetailKeyEntity id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_Id")
    OrdersEntity orders;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_Id")
    ProductEntity product;

    private String itemName;
    private double price;

}

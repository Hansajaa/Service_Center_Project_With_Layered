package Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "orders")
public class OrdersEntity {

    @Id
    private String orderId;
    private String category;
    private String description;
    private String date;
    private double total;

    @ManyToOne
    @JoinColumn(name = "customer")
    private CustomerEntity customer;

    public OrdersEntity(String orderId, String category, String description, String date, double total) {
        this.orderId = orderId;
        this.category = category;
        this.description = description;
        this.date = date;
        this.total = total;
    }
}

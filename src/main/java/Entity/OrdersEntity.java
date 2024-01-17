package Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "orders")
public class OrdersEntity {

    @Id
    private String orderId;
    private String category;
    private String mainItem;
    private String description;
    private String date;
    private String status;
    private String zone;
    private double total;

    @ManyToOne
    @JoinColumn(name = "customer")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "employee")
    EmployeeEntity employee;

    @ManyToOne
    @JoinColumn(name = "admin")
    AdminEntity admin;

    public OrdersEntity(String orderId, String category, String mainItem, String description, String date, double total) {
        this.orderId = orderId;
        this.category = category;
        this.mainItem=mainItem;
        this.description = description;
        this.date = date;
        this.total = total;
    }
}

package Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity(name = "product")
public class ProductEntity {

    @Id
    private String productId;
    private String productName;
    private String category;
    private String emailOfUser;

    @ManyToOne
    @JoinColumn(name = "admin")
    private AdminEntity admin;

    @ManyToOne
    @JoinColumn(name = "employee")
    private EmployeeEntity employee;

    public ProductEntity(String productId, String productName, String category, String emailOfUser) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.emailOfUser = emailOfUser;
    }
}

package Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "Employee")
public class EmployeeEntity {

    private String username;
    @Id
    private String email;
    private String password;

    @OneToMany(mappedBy = "employee")
    private List<ProductEntity> products=new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<OrdersEntity> orders=new ArrayList<>();
    public EmployeeEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

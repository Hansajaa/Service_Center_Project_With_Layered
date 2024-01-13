package Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "customer")
public class CustomerEntity {

    @Id
    private String contactNumber;
    private String email;
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<OrdersEntity> orders=new ArrayList<>();

    public CustomerEntity(String contactNumber, String email, String name) {
        this.contactNumber = contactNumber;
        this.email = email;
        this.name = name;
    }
}

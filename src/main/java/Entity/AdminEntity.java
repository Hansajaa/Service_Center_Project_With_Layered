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
@ToString
@Entity
public class AdminEntity {
    @Id
    private String email;
    private String password;

//    @OneToMany(mappedBy = "adminentity")
//    private List<ProductEntity> products=new ArrayList<>();

    public AdminEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

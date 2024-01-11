package Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Employee")
public class EmployeeEntity {

    private String username;
    @Id
    private String email;
    private String password;
}

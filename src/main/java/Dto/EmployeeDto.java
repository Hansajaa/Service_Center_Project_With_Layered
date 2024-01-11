package Dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {
    private String username;
    private String email;
    private String password;
}

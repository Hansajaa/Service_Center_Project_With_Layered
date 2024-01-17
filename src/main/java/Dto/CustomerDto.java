package Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CustomerDto {
    private String contactNumber;
    private String email;
    private String name;
}

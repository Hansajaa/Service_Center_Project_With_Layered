package Dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    private String productId;
    private String productName;
    private String category;
    private String emailOfUser;
}

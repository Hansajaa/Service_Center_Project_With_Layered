package Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderDetailDto {
    private String orderId;
    private double total;
    private String productId;
    private double  price;
}

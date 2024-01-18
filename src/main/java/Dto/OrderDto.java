package Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderDto {
    private String orderId;
    private String customerContactNumber;
    private String customerEmail;
    private String customerName;
    private String category;
    private String mainItem;
    private String description;
    private String emailOfUser;
    private String date;
    private String status;
    private String zone;
    private double total;
    private double serviceCharge;
}

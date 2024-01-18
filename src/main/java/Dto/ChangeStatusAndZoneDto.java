package Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ChangeStatusAndZoneDto {
    private String orderId;
    private String status;
    private String zone;
}

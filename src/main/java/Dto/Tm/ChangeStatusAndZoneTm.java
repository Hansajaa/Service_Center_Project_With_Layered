package Dto.Tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ChangeStatusAndZoneTm {
    private String orderId;
    private String date;
    private String status;
    private String zone;
}

package Dto.Tm;

import com.jfoenix.controls.JFXButton;
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
    private JFXButton btnSendAlert;
    private JFXButton btnBill;
}

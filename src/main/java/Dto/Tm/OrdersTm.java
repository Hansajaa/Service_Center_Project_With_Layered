package Dto.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrdersTm {
    private String orderId;
    private String mainItem;
    private String date;
    private double total;
}

package Dto.Tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
import lombok.*;

import java.awt.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductTm {
    private String productId;
    private String productName;
    private String category;
    private String emailOfUser;
    private JFXButton btn;
}

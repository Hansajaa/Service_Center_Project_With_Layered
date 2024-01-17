package Bo.Custom;

import Bo.SuperBo;
import Dto.OrderDto;

import java.sql.SQLException;

public interface OrderBo extends SuperBo {
    String getLastOrderId() throws SQLException, ClassNotFoundException;

    boolean saveOrder(OrderDto dto) ;
}

package Bo.Custom;

import Bo.SuperBo;
import Dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderBo extends SuperBo {
    String getLastOrderId() throws SQLException, ClassNotFoundException;

    boolean saveOrder(OrderDto dto) ;

    List<OrderDto> getAllOrders();

    OrderDto getOrder(String orderId);
}

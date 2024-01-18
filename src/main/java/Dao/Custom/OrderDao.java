package Dao.Custom;

import Dao.CrudDao;
import Dto.OrderDto;
import Entity.OrdersEntity;

import java.sql.SQLException;

public interface OrderDao extends CrudDao<OrdersEntity,String> {
    String getLastOrderId() throws SQLException, ClassNotFoundException;

    boolean saveOrder(OrderDto dto);

    boolean updateTotal(String orderId, double total);

    boolean addServiceCharge(OrdersEntity ordersEntity);
}

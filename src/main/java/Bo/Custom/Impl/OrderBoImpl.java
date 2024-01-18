package Bo.Custom.Impl;

import Bo.Custom.OrderBo;
import Dao.Custom.OrderDao;
import Dao.DaoFactory;
import Dao.util.DaoType;
import Dto.OrderDto;
import Entity.OrdersEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBoImpl implements OrderBo {

    OrderDao dao= DaoFactory.getInstance().daoType(DaoType.ORDER);

    @Override
    public String getLastOrderId() throws SQLException, ClassNotFoundException {
        String lastOrderId = dao.getLastOrderId();

        if (lastOrderId==null){
            return null;
        }else {
            String id = lastOrderId.split("ORD")[1];
            int num = Integer.parseInt(id);
            num++;
            String newId = String.format("ORD%03d", num);
            return newId;
        }

    }

    @Override
    public boolean saveOrder(OrderDto dto){
        boolean isSaved = dao.saveOrder(dto);

        return isSaved;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrdersEntity> allOrders = dao.getAll();

        List<OrderDto> orderDtos=new ArrayList<>();

        for (OrdersEntity order:allOrders) {
            orderDtos.add(
                    new OrderDto(
                            order.getOrderId(),
                            null,
                            null,
                            null,
                            order.getCategory(),
                            order.getMainItem(),
                            order.getDescription(),
                            null,
                            order.getDate(),
                            order.getStatus(),
                            order.getZone(),
                            order.getTotal(),
                            order.getServiceCharge()
                    )
            );
        }

        return orderDtos;
    }

    @Override
    public OrderDto getOrder(String orderId) {

        if (orderId.isEmpty()){

            return null;

        }else {

            List<OrdersEntity> allOrders = dao.getAll();

            OrderDto orderDto=new OrderDto();

            for (OrdersEntity ordersEntity:allOrders) {
                if (ordersEntity.getOrderId().equals(orderId)){
                    orderDto.setOrderId(ordersEntity.getOrderId());
                    orderDto.setTotal(ordersEntity.getTotal());
                    return orderDto;
                }
            }
        }

        return null;
    }
}

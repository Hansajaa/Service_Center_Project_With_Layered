package Dao.Custom;

import Dao.CrudDao;
import Dto.OrderDetailDto;
import Entity.OrderDetailEntity;

public interface OrderDetailDao extends CrudDao<OrderDetailEntity,String> {
    boolean saveOrderDetail(OrderDetailDto dto);
}

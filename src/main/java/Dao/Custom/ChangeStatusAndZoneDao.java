package Dao.Custom;

import Dao.CrudDao;
import Dto.ChangeStatusAndZoneDto;
import Entity.OrdersEntity;

public interface ChangeStatusAndZoneDao extends CrudDao<OrdersEntity,String> {
    boolean updateStatusAndZone(ChangeStatusAndZoneDto dto);
}

package Bo.Custom.Impl;

import Bo.Custom.OrderBo;
import Dao.Custom.OrderDao;
import Dao.DaoFactory;
import Dao.util.DaoType;
import Dto.OrderDto;
import Entity.OrdersEntity;

import java.sql.SQLException;

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
}

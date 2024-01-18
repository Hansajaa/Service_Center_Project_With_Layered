package Bo.Custom.Impl;

import Bo.Custom.OrderDetailBo;
import Dao.Custom.OrderDetailDao;
import Dao.DaoFactory;
import Dao.util.DaoType;
import Dto.OrderDetailDto;

public class OrderDetailBoImpl implements OrderDetailBo {

    OrderDetailDao dao= DaoFactory.getInstance().daoType(DaoType.ORDER_DETAIL);

    @Override
    public boolean saveItems(OrderDetailDto dto) {
        boolean isSaved=false;

        if (dto!=null){
            isSaved = dao.saveOrderDetail(dto);
        }

        return isSaved;
    }
}

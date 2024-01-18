package Dao.Custom.Impl;

import Dao.Custom.OrderDao;
import Dao.Custom.OrderDetailDao;
import Dao.DaoFactory;
import Dao.util.DaoType;
import Dao.util.HibernateUtil;
import Dto.OrderDetailDto;
import Entity.OrderDetailEntity;
import Entity.OrderDetailKeyEntity;
import Entity.OrdersEntity;
import Entity.ProductEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {

    OrderDao orderDao= DaoFactory.getInstance().daoType(DaoType.ORDER);

    @Override
    public boolean save(OrderDetailEntity entity) {
        return false;
    }

    @Override
    public boolean update(OrderDetailEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String value) {
        return false;
    }

    @Override
    public List<OrderDetailEntity> getAll() {
        return null;
    }

    @Override
    public boolean saveOrderDetail(OrderDetailDto dto) {
        double total= dto.getTotal()+ dto.getPrice();
        boolean isUpdated = orderDao.updateTotal(dto.getOrderId(), total);

        if (isUpdated) {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            OrdersEntity ordersEntity = session.find(OrdersEntity.class, dto.getOrderId());
            ProductEntity productEntity = session.find(ProductEntity.class, dto.getProductId());

            OrderDetailKeyEntity detailKeyEntity=new OrderDetailKeyEntity(
                    ordersEntity.getOrderId(),
                    productEntity.getProductId()
            );


            OrderDetailEntity orderDetail=new OrderDetailEntity();
            orderDetail.setOrders(ordersEntity);
            orderDetail.setProduct(productEntity);
            orderDetail.setId(detailKeyEntity);
            orderDetail.setItemName(productEntity.getProductName());
            orderDetail.setPrice(dto.getPrice());

            session.save(orderDetail);

            transaction.commit();

            session.close();
            return true;
        }else {
            return false;
        }

    }
}

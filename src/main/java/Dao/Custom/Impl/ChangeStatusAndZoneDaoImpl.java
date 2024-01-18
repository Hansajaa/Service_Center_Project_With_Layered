package Dao.Custom.Impl;

import Dao.Custom.ChangeStatusAndZoneDao;
import Dao.util.HibernateUtil;
import Dto.ChangeStatusAndZoneDto;
import Entity.OrdersEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ChangeStatusAndZoneDaoImpl implements ChangeStatusAndZoneDao {

    @Override
    public boolean save(OrdersEntity entity) {
        return false;
    }

    @Override
    public boolean update(OrdersEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String value) {
        return false;
    }

    @Override
    public List<OrdersEntity> getAll() {
        return null;
    }

    @Override
    public boolean updateStatusAndZone(ChangeStatusAndZoneDto dto) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        OrdersEntity ordersEntity = session.find(OrdersEntity.class, dto.getOrderId());
        ordersEntity.setStatus(dto.getStatus());
        ordersEntity.setZone(dto.getZone());

        session.save(ordersEntity);
        transaction.commit();

        session.close();
        return true;
    }
}

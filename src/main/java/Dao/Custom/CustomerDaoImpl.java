package Dao.Custom;

import Dao.util.HibernateUtil;
import Entity.CustomerEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao{
    @Override
    public boolean save(CustomerEntity entity) {
        Session session = HibernateUtil.getSession();

        Transaction transaction = session.beginTransaction();
        session.save(entity);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(CustomerEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String value) {
        return false;
    }

    @Override
    public List<CustomerEntity> getAll() {
        return null;
    }
}

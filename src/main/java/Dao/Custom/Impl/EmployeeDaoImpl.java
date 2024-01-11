package Dao.Custom.Impl;

import Dao.Custom.EmployeeDao;
import Dao.util.HibernateUtil;
import Entity.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeDaoImpl implements EmployeeDao<EmployeeEntity,String> {

    @Override
    public boolean registerEmployee(EmployeeEntity entity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateEmployee(EmployeeEntity entity) {
        return false;
    }
}

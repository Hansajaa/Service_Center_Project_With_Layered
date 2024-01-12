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
    public boolean changePassword(EmployeeEntity entity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        EmployeeEntity employee = session.find(EmployeeEntity.class, entity.getEmail());
        employee.setPassword(entity.getPassword());

        session.save(employee);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public EmployeeEntity authenticate(String value) {
        Session session = HibernateUtil.getSession();

        return session.find(EmployeeEntity.class,value);
    }

}

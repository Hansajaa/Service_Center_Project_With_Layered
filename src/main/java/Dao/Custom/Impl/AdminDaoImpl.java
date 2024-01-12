package Dao.Custom.Impl;

import Dao.Custom.AdminDao;
import Dao.util.HibernateUtil;
import Entity.AdminEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AdminDaoImpl implements AdminDao{

    @Override
    public AdminEntity authenticate(String value) {
        Session session = HibernateUtil.getSession();

        AdminEntity admin = session.find(AdminEntity.class, value);

        session.close();

        return admin;

    }

    @Override
    public boolean save(AdminEntity entity) {
        return false;
    }

//    Change Password
    @Override
    public boolean update(AdminEntity entity) {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        AdminEntity admin = session.find(AdminEntity.class, entity.getEmail());
        admin.setPassword(entity.getPassword());

        session.save(admin);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(String value) {
        return false;
    }

    @Override
    public List<AdminEntity> getAll() {
        return null;
    }
}

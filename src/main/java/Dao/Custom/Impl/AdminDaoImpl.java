package Dao.Custom.Impl;

import Dao.Custom.AdminDao;
import Dao.util.HibernateUtil;
import Entity.AdminEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AdminDaoImpl implements AdminDao<AdminEntity,String> {

    @Override
    public boolean changePassword(AdminEntity entity) {
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
    public AdminEntity authenticate(String value) {
        Session session = HibernateUtil.getSession();

        AdminEntity admin = session.find(AdminEntity.class, value);

        session.close();

        return admin;

    }
}

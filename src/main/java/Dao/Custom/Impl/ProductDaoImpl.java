package Dao.Custom.Impl;

import DB.DBConnection;
import Dao.Custom.ProductDao;
import Dao.util.HibernateUtil;
import Entity.ProductEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(ProductEntity entity) {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(ProductEntity entity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        ProductEntity productEntity = session.find(ProductEntity.class, entity.getProductId());
        productEntity.setProductName(entity.getProductName());
        productEntity.setCategory(entity.getCategory());
        productEntity.setEmailOfUser(entity.getEmailOfUser());

        session.save(productEntity);
        transaction.commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(String value) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(session.find(ProductEntity.class,value));
        transaction.commit();
        session.close();

        return true;

    }

    @Override
    public List<ProductEntity> getAll() {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM product");
        List<ProductEntity> list = query.list();
        session.close();
        return list;

    }

    @Override
    public ProductEntity getLastId() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM product ORDER BY productId DESC LIMIT 1";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return new ProductEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }else {
            System.out.println("product dao");
            return null;
        }

//        Session session = HibernateUtil.getSession();
//        Query query = session.createQuery("FROM product ORDER BY productId DESC LIMIT 1");
//        List<ProductEntity> list = query.list();
//
//        if (list!=null){
//            return list;
//        }
//        System.out.println("product dao");
//        return null;
    }
}

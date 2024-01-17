package Dao.Custom.Impl;

import DB.DBConnection;
import Dao.Custom.*;
import Dao.DaoFactory;
import Dao.util.DaoType;
import Dao.util.HibernateUtil;
import Dto.EmployeeDto;
import Dto.OrderDto;
import Entity.AdminEntity;
import Entity.CustomerEntity;
import Entity.EmployeeEntity;
import Entity.OrdersEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    AdminDao adminDao = DaoFactory.getInstance().daoType(DaoType.ADMIN);
    EmployeeDao employeeDao=DaoFactory.getInstance().daoType(DaoType.EMPLOYEE);

    CustomerDao customerDao=DaoFactory.getInstance().daoType(DaoType.CUSTOMER);


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
    public String getLastOrderId() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM orders ORDER BY orderId DESC LIMIT 1";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString(1);
        }else {
            System.out.println("product dao");
            return null;
        }
    }

    @Override
    public boolean saveOrder(OrderDto dto) throws RuntimeException{
        CustomerEntity customer=new CustomerEntity(
                dto.getCustomerContactNumber(),
                dto.getCustomerEmail(),
                dto.getCustomerName()
        );

        //customer save
        boolean isCustomerSaved = customerDao.save(customer);

        if (isCustomerSaved){

            OrdersEntity newOrder=new OrdersEntity(
                    dto.getOrderId(),
                    dto.getCategory(),
                    dto.getMainItem(),
                    dto.getDescription(),
                    dto.getDate(),
                    dto.getStatus(),
                    dto.getZone(),
                    dto.getTotal()
            );

            newOrder.setCustomer(customer);

            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            session.save(newOrder);
            transaction.commit();

            session.close();
            return true;
        }
        return false;
    }
}

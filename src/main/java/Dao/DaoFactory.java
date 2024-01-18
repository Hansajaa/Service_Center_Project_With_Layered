package Dao;

import Dao.Custom.Impl.*;
import Dao.util.DaoType;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){

    }

    public static DaoFactory getInstance(){
        return daoFactory!=null ? daoFactory:(daoFactory=new DaoFactory());
    }

    public <T extends SuperDao>T daoType(DaoType type){
        switch (type){
            case EMPLOYEE: return (T) new EmployeeDaoImpl();
            case ADMIN: return (T) new AdminDaoImpl();
            case ITEM: return (T) new ProductDaoImpl();
            case ORDER: return (T) new OrderDaoImpl();
            case CUSTOMER: return(T) new CustomerDaoImpl();
            case ORDER_DETAIL: return (T) new OrderDetailDaoImpl();
        }
        return null;
    }
}

package Dao;

import Dao.Custom.Impl.AdminDaoImpl;
import Dao.Custom.Impl.EmployeeDaoImpl;
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
        }
        return null;
    }
}

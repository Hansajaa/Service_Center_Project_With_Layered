package Dao.Custom;

import Dao.SuperDao;
import Dto.EmployeeDto;
import Entity.EmployeeEntity;

public interface EmployeeDao<T,dataType> extends SuperDao {
    boolean registerEmployee(T entity);

    boolean updateEmployee(T entity);
}

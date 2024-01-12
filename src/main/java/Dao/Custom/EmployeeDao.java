package Dao.Custom;

import Dao.CrudDao;
import Dao.SuperDao;
import Dto.EmployeeDto;
import Entity.EmployeeEntity;

public interface EmployeeDao extends CrudDao<EmployeeEntity,String> {

    EmployeeEntity authenticate(String value);
}

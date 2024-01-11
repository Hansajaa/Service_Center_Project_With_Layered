package Bo.Custom.Impl;

import Bo.Custom.EmployeeBo;
import Dao.Custom.EmployeeDao;
import Dao.DaoFactory;
import Dao.util.DaoType;
import Dto.EmployeeDto;
import Entity.EmployeeEntity;

public class EmployeeBoImpl implements EmployeeBo {

    EmployeeDao<EmployeeEntity,String> dao = DaoFactory.getInstance().daoType(DaoType.EMPLOYEE);
    @Override
    public boolean registerEmployee(EmployeeDto dto) {
        boolean isRegistered = dao.registerEmployee(
                new EmployeeEntity(
                        dto.getUsername(),
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        return isRegistered;
    }

    @Override
    public boolean updateEmployee(EmployeeDto dto) {
        return false;
    }

}

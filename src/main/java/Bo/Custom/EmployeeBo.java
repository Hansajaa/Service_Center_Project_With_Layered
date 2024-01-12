package Bo.Custom;

import Bo.SuperBo;
import Dto.EmployeeDto;

public interface EmployeeBo extends SuperBo {
    boolean registerEmployee(EmployeeDto dto);

    boolean changePassword(EmployeeDto dto);

    String encryptPassword(String password);

    boolean authenticate(EmployeeDto dto);
}

package Bo.Custom.Impl;

import Bo.Custom.EmployeeBo;
import Dao.Custom.EmployeeDao;
import Dao.DaoFactory;
import Dao.util.DaoType;
import Dto.EmployeeDto;
import Entity.EmployeeEntity;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EmployeeBoImpl implements EmployeeBo {

    EmployeeDao dao = DaoFactory.getInstance().daoType(DaoType.EMPLOYEE);
    @Override
    public boolean registerEmployee(EmployeeDto dto) {

        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();

        boolean isRegistered = dao.save(
                new EmployeeEntity(
                        dto.getUsername(),
                        dto.getEmail(),
                        encryptor.encryptPassword(dto.getPassword())
                        //encryptPassword(dto.getPassword())
                )
        );

        return isRegistered;
    }

    @Override
    public boolean changePassword(EmployeeDto dto) {

        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();

        boolean isChanged = dao.update(
                new EmployeeEntity(
                        dto.getUsername(),
                        dto.getEmail(),
                        encryptor.encryptPassword(dto.getPassword())
                        //encryptPassword(dto.getPassword())
                )
        );

        return isChanged;

    }


//    MD5 algorithm
    @Override
    public String encryptPassword(String password) {
        /* Plain-text password initialization. */
        String encryptedpassword = null;
        try {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptedpassword = s.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return encryptedpassword;
    }

    @Override
    public boolean authenticate(EmployeeDto dto) {
        EmployeeEntity employee = dao.authenticate(dto.getEmail());

        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();

        if (encryptor.checkPassword(dto.getPassword(), employee.getPassword())){
            return true;
        }
        return false;
//        if (employee.getPassword().equals(encryptPassword(dto.getPassword()))){
//            return true;
//        }
//        return false;



    }
}

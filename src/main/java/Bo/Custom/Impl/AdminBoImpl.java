package Bo.Custom.Impl;

import Bo.Custom.AdminBo;
import Dao.Custom.AdminDao;
import Dao.DaoFactory;
import Dao.util.DaoType;
import Dto.AdminDto;
import Entity.AdminEntity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AdminBoImpl implements AdminBo {

    AdminDao<AdminEntity,String> dao = DaoFactory.getInstance().daoType(DaoType.ADMIN);
    @Override
    public boolean changePassword(AdminDto dto) {
        return dao.changePassword(
                new AdminEntity(
                        dto.getEmail(),
                        encryptPassword(dto.getPassword())
                )
        );
    }

    @Override
    public boolean authenticate(AdminDto dto) {
        AdminEntity admin = dao.authenticate(dto.getEmail());

        if (admin.getPassword().equals(encryptPassword(dto.getPassword()))){
            return true;
        }
        return false;
    }

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
}

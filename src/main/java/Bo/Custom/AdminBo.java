package Bo.Custom;

import Bo.SuperBo;
import Dto.AdminDto;

public interface AdminBo extends SuperBo {
    boolean changePassword(AdminDto dto);

    boolean authenticate(AdminDto dto);

    String encryptPassword(String password);

    String sendOtp(String mail);

    String generateOtp();

    void mailSend(String mail,String subject,String message);
}

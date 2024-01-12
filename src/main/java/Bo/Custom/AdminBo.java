package Bo.Custom;

import Bo.SuperBo;
import Dto.AdminDto;

public interface AdminBo extends SuperBo {
    boolean changePassword(AdminDto dto);
}

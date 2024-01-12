package Bo.Custom.Impl;

import Bo.Custom.AdminBo;
import Dao.Custom.AdminDao;
import Dao.DaoFactory;
import Dao.util.DaoType;
import Dto.AdminDto;
import Entity.AdminEntity;

public class AdminBoImpl implements AdminBo {

    AdminDao<AdminEntity,String> dao = DaoFactory.getInstance().daoType(DaoType.ADMIN);
    @Override
    public boolean changePassword(AdminDto dto) {
        return dao.changePassword(
                new AdminEntity(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );
    }

    @Override
    public boolean authenticate(AdminDto dto) {
        AdminEntity admin = dao.authenticate(dto.getEmail());

        if (dto.getPassword().equals(admin.getPassword())){
            return true;
        }
        return false;
    }
}

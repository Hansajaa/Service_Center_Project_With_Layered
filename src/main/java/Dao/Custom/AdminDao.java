package Dao.Custom;

import Dao.CrudDao;
import Dao.SuperDao;
import Dto.AdminDto;
import Entity.AdminEntity;

public interface AdminDao extends CrudDao<AdminEntity,String> {

    AdminEntity authenticate(String value);
}

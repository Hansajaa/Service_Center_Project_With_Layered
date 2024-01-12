package Dao.Custom;

import Dao.SuperDao;
import Dto.AdminDto;
import Entity.AdminEntity;

public interface AdminDao<T,dataType> extends SuperDao {
    boolean changePassword(T entity);
}

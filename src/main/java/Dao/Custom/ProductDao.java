package Dao.Custom;

import Dao.CrudDao;
import Entity.ProductEntity;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends CrudDao<ProductEntity,String> {
    ProductEntity getLastId() throws SQLException, ClassNotFoundException;
}

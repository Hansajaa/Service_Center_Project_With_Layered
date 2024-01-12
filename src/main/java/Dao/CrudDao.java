package Dao;

import java.util.List;

public interface CrudDao<T,DataType> extends SuperDao {
    boolean save(T entity);
    boolean update(T entity);

    boolean delete(DataType value);

    List<T> getAll();
}

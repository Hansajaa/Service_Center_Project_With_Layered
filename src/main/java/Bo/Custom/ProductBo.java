package Bo.Custom;

import Bo.SuperBo;
import Dao.SuperDao;
import Dto.ProductDto;

import java.sql.SQLException;
import java.util.List;

public interface ProductBo extends SuperBo {
    String getLastId() throws SQLException, ClassNotFoundException;

    boolean productSave(ProductDto dto);

    List<ProductDto> getAllProducts();

    boolean deleteProduct(String id);

    boolean updateProduct(ProductDto dto);
}

package Bo.Custom.Impl;

import Bo.Custom.ProductBo;
import Dao.Custom.ProductDao;
import Dao.DaoFactory;
import Dao.util.DaoType;
import Dto.ProductDto;
import Entity.ProductEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBoImpl implements ProductBo {

    ProductDao dao= DaoFactory.getInstance().daoType(DaoType.ITEM);

    @Override
    public String getLastId() throws SQLException, ClassNotFoundException {
        ProductEntity lastItem = dao.getLastId();

        if (lastItem==null){
            return null;
        }else {
            String lastItemProductId = lastItem.getProductId();
            if (lastItemProductId!=null){
                String lastId = lastItemProductId.split("P")[1];
                int num = Integer.parseInt(lastId);
                num++;
                String newId = String.format("P%03d", num);
                return newId;
            }
            return null;
        }
    }

    @Override
    public boolean productSave(ProductDto dto) {
        boolean isSaved = dao.save(
                new ProductEntity(
                        dto.getProductId(),
                        dto.getProductName(),
                        dto.getCategory(),
                        dto.getEmailOfUser()
                )
        );

        return isSaved;

    }

    @Override
    public List<ProductDto> getAllProducts() {

        List<ProductEntity> all = dao.getAll();

        List<ProductDto> productDtos=new ArrayList<>();

        for (ProductEntity productEntity:all) {
            productDtos.add(
                    new ProductDto(
                            productEntity.getProductId(),
                            productEntity.getProductName(),
                            productEntity.getCategory(),
                            productEntity.getEmailOfUser()
                    )
            );
        }

        return productDtos;
    }

    @Override
    public boolean deleteProduct(String id) {
        boolean isDeleted = dao.delete(id);
        return isDeleted;
    }

    @Override
    public boolean updateProduct(ProductDto dto) {
        boolean isUpdated = dao.update(
                new ProductEntity(
                        dto.getProductId(),
                        dto.getProductName(),
                        dto.getCategory(),
                        dto.getEmailOfUser()
                )
        );

        return  isUpdated;
    }
}

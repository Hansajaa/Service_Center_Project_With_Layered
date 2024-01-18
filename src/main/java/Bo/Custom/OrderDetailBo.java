package Bo.Custom;

import Bo.SuperBo;
import Dto.OrderDetailDto;

public interface OrderDetailBo extends SuperBo {
    boolean saveItems(OrderDetailDto dto);
}

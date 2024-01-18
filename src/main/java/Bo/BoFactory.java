package Bo;

import Bo.Custom.ChangeStatusAndZoneBo;
import Bo.Custom.Impl.*;
import Dao.util.BoType;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory(){

    }

    public static BoFactory getInstance(){
        return boFactory!=null ? boFactory:(boFactory=new BoFactory());
    }

    public <T extends SuperBo>T boType(BoType type){
        switch (type){
            case EMPLOYEE: return (T) new EmployeeBoImpl();
            case ADMIN: return (T) new AdminBoImpl();
            case ITEM: return (T) new ProductBoImpl();
            case ORDER: return (T) new OrderBoImpl();
            case ORDER_DETAIL: return (T) new OrderDetailBoImpl();
            case CHANGE_STATUS_ZONE: return (T) new ChangeStatusAndZoneBoImpl();
        }
        return null;
    }
}

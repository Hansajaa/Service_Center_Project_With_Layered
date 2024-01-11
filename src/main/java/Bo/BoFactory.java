package Bo;

import Bo.Custom.Impl.EmployeeBoImpl;
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
        }
        return null;
    }
}

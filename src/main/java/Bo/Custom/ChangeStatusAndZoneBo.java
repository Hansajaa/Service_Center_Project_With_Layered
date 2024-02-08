package Bo.Custom;

import Bo.SuperBo;
import Dto.ChangeStatusAndZoneDto;

import java.util.List;

public interface ChangeStatusAndZoneBo extends SuperBo {

    List<String> getStatuses();

    List<String> getZones();

    boolean update(ChangeStatusAndZoneDto dto);

    boolean sendAlert(String orderId);

    boolean sendBill(String orderId);

    void sendMail(String to,String subject, String message);

}

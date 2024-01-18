package Bo.Custom.Impl;

import Bo.Custom.ChangeStatusAndZoneBo;
import Dao.Custom.ChangeStatusAndZoneDao;
import Dao.DaoFactory;
import Dao.util.DaoType;
import Dto.ChangeStatusAndZoneDto;

import java.util.ArrayList;
import java.util.List;

public class ChangeStatusAndZoneBoImpl implements ChangeStatusAndZoneBo {

    ChangeStatusAndZoneDao changeStatusAndZoneDao= DaoFactory.getInstance().daoType(DaoType.CHANGE_STATUS_ZONE);
    @Override
    public List<String> getStatuses() {
        List<String> statuses=new ArrayList<>();

        statuses.add("Pending");
        statuses.add("Processing");
        statuses.add("Completed");
        statuses.add("Closed");

        return statuses;
    }

    @Override
    public List<String> getZones() {
        List<String> zones=new ArrayList<>();

        zones.add("None");
        zones.add("Orange Zone");
        zones.add("Red Zone");
        zones.add("Yellow Zone");
        zones.add("Green Zone");

        return zones;
    }

    @Override
    public boolean update(ChangeStatusAndZoneDto dto) {
        boolean isUpdated = changeStatusAndZoneDao.updateStatusAndZone(dto);

        return isUpdated;
    }
}

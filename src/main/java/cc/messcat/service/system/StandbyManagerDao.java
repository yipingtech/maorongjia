package cc.messcat.service.system;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.entity.Standby;

public interface StandbyManagerDao {

	public void addStandby(Standby standby);
	
	public void modifyStandby(Standby standby);
	
	public void removeStandby(Standby standby);
	
	public void removeStandby(Long id);

	public void update(Standby standby);
	
	public Standby getStandbyById(Long id);
	
	public Standby retrieveStandby(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllStandbys();
	
	public Pager retrieveStandbysPager(int pageSize, int pageNo);

	public Standby getFirstEntity();
	
}
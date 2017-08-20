package cc.messcat.dao.system;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.entity.Standby;

public interface StandbyDao {

	public void save(Standby standby);
	
	public void update(Standby standby);
	
	public void delete(Standby standby);
	
	public void delete(Long id);
	
	public Standby get(Long id);
	
	public Standby getStandbyById(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);

	public Standby getFirstEntity();
	
}
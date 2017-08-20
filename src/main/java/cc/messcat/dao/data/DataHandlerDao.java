package cc.messcat.dao.data;

import cc.modules.commons.Pager;
import cc.messcat.entity.BackupHistory;

public interface DataHandlerDao {

	public BackupHistory get(Long long1);

	public void save(BackupHistory backupHistory);

	public void update(BackupHistory backupHistory);

	public void delete(BackupHistory backupHistory);

	public void delete(Long long1);

	public Pager getBackupHistoryPager(int pageSize, int pageNo);

}

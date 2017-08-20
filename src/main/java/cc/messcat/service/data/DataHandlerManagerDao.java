package cc.messcat.service.data;

import cc.modules.commons.Pager;
import cc.messcat.entity.BackupHistory;

public interface DataHandlerManagerDao {

	public BackupHistory getHistory(Long long1);

	public void saveHistory(BackupHistory backupHistory);

	public void updateHistory(BackupHistory backupHistory);

	public void deleteHistory(BackupHistory backupHistory);

	public void deleteHistory(Long long1);

	public Pager getAllBackupHistories(int pageSize, int pageNo);

}

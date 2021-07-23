package org.ssssssss.magicapi.provider;

import org.ssssssss.magicapi.model.*;
import org.ssssssss.magicapi.utils.JsonUtils;

import java.util.List;

/**
 * 数据备份接口
 */
public interface MagicBackupService {

	int FETCH_SIZE = 100;

	default void backup(ApiInfo apiInfo) {
		doBackup(new Backup(apiInfo.getId(), Constants.PATH_API, apiInfo.getName(), JsonUtils.toJsonString(apiInfo)));
	}

	default void backup(FunctionInfo functionInfo) {
		doBackup(new Backup(functionInfo.getId(), Constants.PATH_FUNCTION, functionInfo.getName(), JsonUtils.toJsonString(functionInfo)));
	}

	default void backup(DataSourceInfo dataSourceInfo) {
		doBackup(new Backup(dataSourceInfo.getId(), Constants.PATH_DATASOURCE, dataSourceInfo.get("name"), JsonUtils.toJsonString(dataSourceInfo)));
	}

	/**
	 * 执行备份动作
	 */
	void doBackup(Backup backup);

	/**
	 * 根据时间戳查询最近的 FETCH_SIZE 条记录
	 */
	List<Backup> backupList(long timestamp);

	/**
	 * 根据对象ID查询备份记录
	 */
	List<Backup> backupById(String id);

	/**
	 * 根据对象ID和备份时间查询
	 */
	Backup backupInfo(String id, long timestamp);

	/**
	 * 根据标签查询备份记录
	 */
	List<Backup> backupByTag(String tag);

	/**
	 * 删除备份
	 *
	 * @return 返回删除的记录数
	 */
	long removeBackup(String id);

	/**
	 * 删除一组备份信息
	 *
	 * @return 返回删除的记录数
	 */
	long removeBackup(List<String> idList);

	/**
	 * 根据13位时间戳删除备份记录（清除小于该值的记录）
	 *
	 * @return 返回删除的记录数
	 */
	long removeBackupByTimestamp(long timestamp);


}
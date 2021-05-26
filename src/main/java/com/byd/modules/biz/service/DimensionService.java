package com.byd.modules.biz.service;

import com.byd.modules.biz.entity.DimensionEntity;

import java.util.List;
import java.util.Map;

/**
 * 维度词库
 * 
 * @author kaiyue.cheng
 * @email 779363522@qq.com
 * @date 2017-12-22 13:58:27
 */
public interface DimensionService {
	
	DimensionEntity queryObject(Long id);
	
	List<DimensionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DimensionEntity dimension);
	
	void update(DimensionEntity dimension);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}

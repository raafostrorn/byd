package com.byd.modules.biz.service;

import com.byd.modules.biz.entity.DimensionEntity;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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

    /** 
     * 过滤检索维度
     * @param parentId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<DimensionEntity> queryListFilter(Long parentId);
    
    void export(Map query, HttpServletResponse response);
}

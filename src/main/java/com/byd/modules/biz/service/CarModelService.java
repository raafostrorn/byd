package com.byd.modules.biz.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.byd.modules.biz.entity.CarModelEntity;

/**
 * 维度词库
 * 
 * @author kaiyue.cheng
 * @email 779363522@qq.com
 * @date 2017-12-22 13:58:27
 */
public interface CarModelService {
	
	CarModelEntity queryObject(Long id);
	
	List<CarModelEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CarModelEntity dimension);
	
	void update(CarModelEntity dimension);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

    List<CarModelEntity> queryListFilter(Long parentId);
    
    void export(Map query, HttpServletResponse response);
}

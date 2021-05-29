package com.byd.modules.biz.service;

import com.byd.modules.biz.entity.ScenePatternEntity;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * 句式库
 * 
 * @author YMX
 * @email YMX@gmail.com
 * @date 2018-01-04 16:16:42
 */
public interface ScenePatternService {
	
	ScenePatternEntity queryObject(Long id);
	
	List<ScenePatternEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ScenePatternEntity scenePattern);
	
	void update(ScenePatternEntity scenePattern);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param response
     * @see [类、类#方法、类#成员]
     */
    void export(HttpServletResponse response);
}

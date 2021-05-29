package com.byd.modules.biz.service;

import com.byd.modules.biz.entity.EmotionEntity;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * 情感词库
 * 
 * @author YMX
 * @email YMX@gmail.com
 * @date 2017-12-27 20:02:39
 */
public interface EmotionService {
	
	EmotionEntity queryObject(Long id);
	
	List<EmotionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(EmotionEntity emotion);
	
	void update(EmotionEntity emotion);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
	
	void export(HttpServletResponse response);
}

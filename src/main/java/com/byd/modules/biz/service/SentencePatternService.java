package com.byd.modules.biz.service;

import com.byd.modules.biz.entity.SentencePatternEntity;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * 句式库
 * 
 * @author YMX
 * @email YMX@gmail.com
 * @date 2017-12-27 21:01:17
 */
public interface SentencePatternService {
	
	SentencePatternEntity queryObject(Long id);
	
	List<SentencePatternEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SentencePatternEntity sentencePattern);
	
	void update(SentencePatternEntity sentencePattern);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

    void export(HttpServletResponse response);
}

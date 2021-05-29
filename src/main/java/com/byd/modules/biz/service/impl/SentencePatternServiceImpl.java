package com.byd.modules.biz.service.impl;

import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.byd.modules.biz.dao.SentencePatternDao;
import com.byd.modules.biz.entity.EmotionEntity;
import com.byd.modules.biz.entity.SentencePatternEntity;
import com.byd.modules.biz.service.ExcelService;
import com.byd.modules.biz.service.SentencePatternService;



@Service("sentencePatternService")
public class SentencePatternServiceImpl implements SentencePatternService {
	@Autowired
	private SentencePatternDao sentencePatternDao;
	
	@Autowired
    private ExcelService excelService;
	
	@Override
	public SentencePatternEntity queryObject(Long id){
		return sentencePatternDao.queryObject(id);
	}
	
	@Override
	public List<SentencePatternEntity> queryList(Map<String, Object> map){
		return sentencePatternDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sentencePatternDao.queryTotal(map);
	}
	
	@Override
	public void save(SentencePatternEntity sentencePattern){
		sentencePatternDao.save(sentencePattern);
	}
	
	@Override
	public void update(SentencePatternEntity sentencePattern){
		sentencePatternDao.update(sentencePattern);
	}
	
	@Override
	public void delete(Long id){
		sentencePatternDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		sentencePatternDao.deleteBatch(ids);
	}
	
	@Override
    public void export(HttpServletResponse response) {
        List queryList = sentencePatternDao.queryAll();
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=SentencePattern.xls");
        excelService.exportExcel(new ExportParams(), SentencePatternEntity.class, queryList, response);
    
        
    }
	
}

package com.byd.modules.biz.service.impl;

import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.byd.modules.biz.dao.EmotionDao;
import com.byd.modules.biz.entity.DimensionEntity;
import com.byd.modules.biz.entity.EmotionEntity;
import com.byd.modules.biz.service.EmotionService;
import com.byd.modules.biz.service.ExcelService;



@Service("emotionService")
public class EmotionServiceImpl implements EmotionService {
	@Autowired
	private EmotionDao emotionDao;
	
	@Autowired
    private ExcelService excelService;
	
	@Override
	public EmotionEntity queryObject(Long id){
		return emotionDao.queryObject(id);
	}
	
	@Override
	public List<EmotionEntity> queryList(Map<String, Object> map){
		return emotionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return emotionDao.queryTotal(map);
	}
	
	@Override
	public void save(EmotionEntity emotion){
		emotionDao.save(emotion);
	}
	
	@Override
	public void update(EmotionEntity emotion){
		emotionDao.update(emotion);
	}
	
	@Override
	public void delete(Long id){
		emotionDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		emotionDao.deleteBatch(ids);
	}
	
	 /**
     * 重载方法
     * @param parentId
     * @param response
     */
    @Override
    public void export(HttpServletResponse response) {
        List queryList = emotionDao.queryAll();
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=emotion.xls");
        excelService.exportExcel(new ExportParams(), EmotionEntity.class, queryList, response);
    
        
    }
	
}

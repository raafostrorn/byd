package com.byd.modules.biz.service.impl;

import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.byd.modules.biz.dao.ScenePatternDao;
import com.byd.modules.biz.entity.ScenePatternEntity;
import com.byd.modules.biz.entity.SentencePatternEntity;
import com.byd.modules.biz.service.ExcelService;
import com.byd.modules.biz.service.ScenePatternService;

@Service("scenePatternService")
public class ScenePatternServiceImpl implements ScenePatternService {
    @Autowired
    private ScenePatternDao scenePatternDao;

    @Autowired
    private ExcelService excelService;

    @Override
    public ScenePatternEntity queryObject(Long id) {
        return scenePatternDao.queryObject(id);
    }

    @Override
    public List<ScenePatternEntity> queryList(Map<String, Object> map) {
        return scenePatternDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return scenePatternDao.queryTotal(map);
    }

    @Override
    public void save(ScenePatternEntity scenePattern) {
        scenePatternDao.save(scenePattern);
    }

    @Override
    public void update(ScenePatternEntity scenePattern) {
        scenePatternDao.update(scenePattern);
    }

    @Override
    public void delete(Long id) {
        scenePatternDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        scenePatternDao.deleteBatch(ids);
    }

    /**
     * 重载方法
     * 
     * @param response
     */
    @Override
    public void export(HttpServletResponse response) {
        List queryList = scenePatternDao.queryAll();
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=ScenePattern.xls");
        excelService.exportExcel(new ExportParams(), ScenePatternEntity.class, queryList, response);

    }

}

package com.byd.modules.biz.service;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;

public interface ExcelService {

    void exportExcel(ExportParams entity, Class<?> pojoClass, Collection<?> dataSet, HttpServletResponse response);

    public List importExcel(InputStream inputstream, Class<?> pojoClass, ImportParams params);
    
    public void exportTemplate(ExportParams entity, Class<?> pojoClass, HttpServletResponse response);
}

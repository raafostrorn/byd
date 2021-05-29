package com.byd.modules.biz.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.stereotype.Service;

import com.byd.common.exception.RRException;
import com.byd.modules.biz.service.ExcelService;



/**
 * excel导入导出
 */
@Service("excelService")
public class ExcelServiceImpl implements ExcelService {


    @Override
    public void exportExcel(ExportParams entity, Class<?> pojoClass, Collection<?> dataSet, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(entity, pojoClass, dataSet);
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RRException("", e);
        }
    }
    
    @Override
    public List importExcel(InputStream inputstream, Class<?> pojoClass, ImportParams params) {
        try {
        	return ExcelImportUtil.importExcel(inputstream, pojoClass, params);
        } catch (Exception e) {
            throw new RRException("", e);
        }
    }
    
    @Override
    public void exportTemplate(ExportParams entity, Class<?> pojoClass, HttpServletResponse response) {
    	Collection<?> dataSet = new ArrayList<>();
    	exportExcel(entity, pojoClass, dataSet, response);
    }

}

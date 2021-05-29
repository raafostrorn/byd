package com.byd.modules.biz.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byd.modules.biz.dao.CarModelDao;
import com.byd.modules.biz.entity.CarModelEntity;
import com.byd.modules.biz.service.CarModelService;
import com.byd.modules.biz.service.ExcelService;



@Service("carModelService")
public class CarModelServiceImpl implements CarModelService {
	@Autowired
	private CarModelDao carModelDao;
	
	@Autowired
    private ExcelService excelService;
	
	@Override
	public CarModelEntity queryObject(Long id){
		return carModelDao.queryObject(id);
	}
	
	@Override
	public List<CarModelEntity> queryList(Map<String, Object> map){
		return carModelDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return carModelDao.queryTotal(map);
	}
	
	@Override
	public void save(CarModelEntity dimension){
	    CarModelEntity parentDim = this.carModelDao.queryObject(dimension.getParentId());
		carModelDao.save(dimension);
		dimension.setLinkId(parentDim!=null?parentDim.getLinkId()+dimension.getId()+"-":"-"+dimension.getId()+"-");
		dimension.setLinkName(parentDim!=null?parentDim.getLinkName()+dimension.getName()+"-":"-"+dimension.getName()+"-");
		dimension.setLevel(parentDim!=null?parentDim.getLevel()+1:1);
		dimension.setParentName(parentDim!=null?parentDim.getName():"");
		carModelDao.update(dimension);
	}
	
	@Override
	public void update(CarModelEntity dimension){
	    CarModelEntity parentDim = this.carModelDao.queryObject(dimension.getParentId());
	    dimension.setLinkId(parentDim!=null?parentDim.getLinkId()+dimension.getId()+"-":"-"+dimension.getId()+"-");
        dimension.setLinkName(parentDim!=null?parentDim.getLinkName()+dimension.getName()+"-":"-"+dimension.getName()+"-");
        dimension.setLevel(parentDim!=null?parentDim.getLevel()+1:1);
        dimension.setParentName(parentDim!=null?parentDim.getName():"");
		carModelDao.update(dimension);
	}
	
	@Override
	public void delete(Long id){
		carModelDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		carModelDao.deleteBatch(ids);
	}

    /**
     * 重载方法
     * @param parentId
     * @return
     */
    @Override
    public List<CarModelEntity> queryListFilter(Long parentId) {
        return carModelDao.queryListFilter(parentId);
    }

    /**
     * 重载方法
     * @param parentId
     * @param response
     */
    @Override
    public void export(Map query, HttpServletResponse response) {
        List<CarModelEntity> queryList = carModelDao.queryListTree(query);
        for (CarModelEntity dimensionEntity : queryList) {
            dimensionEntity.setName(dim2Space(dimensionEntity.getLevel(),dimensionEntity.getName()));
        }
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=CarModel.xls");
        excelService.exportExcel(new ExportParams(), CarModelEntity.class, queryList, response);
    
        
    }
    
    public String dim2Space(int level,String name){
        String newName="";
        for (int i = 0; i < level; i++) {
            newName+="  ";
        }
       return newName+name; 
    }

}

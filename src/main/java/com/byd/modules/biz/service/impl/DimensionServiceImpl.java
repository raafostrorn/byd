package com.byd.modules.biz.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byd.modules.biz.dao.DimensionDao;
import com.byd.modules.biz.entity.DimensionEntity;
import com.byd.modules.biz.service.DimensionService;
import com.byd.modules.biz.service.ExcelService;



@Service("dimensionService")
public class DimensionServiceImpl implements DimensionService {
	@Autowired
	private DimensionDao dimensionDao;
	
	@Autowired
    private ExcelService excelService;
	
	@Override
	public DimensionEntity queryObject(Long id){
		return dimensionDao.queryObject(id);
	}
	
	@Override
	public List<DimensionEntity> queryList(Map<String, Object> map){
		return dimensionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dimensionDao.queryTotal(map);
	}
	
	@Override
	public void save(DimensionEntity dimension){
	    DimensionEntity parentDim = this.dimensionDao.queryObject(dimension.getParentId());
		dimensionDao.save(dimension);
		dimension.setLinkId(parentDim!=null?parentDim.getLinkId()+dimension.getId()+"-":"-"+dimension.getId()+"-");
		dimension.setLinkName(parentDim!=null?parentDim.getLinkName()+dimension.getName()+"-":"-"+dimension.getName()+"-");
		dimension.setLevel(parentDim!=null?parentDim.getLevel()+1:1);
		dimension.setParentName(parentDim!=null?parentDim.getName():"");
		dimensionDao.update(dimension);
	}
	
	@Override
	public void update(DimensionEntity dimension){
	    DimensionEntity parentDim = this.dimensionDao.queryObject(dimension.getParentId());
	    dimension.setLinkId(parentDim!=null?parentDim.getLinkId()+dimension.getId()+"-":"-"+dimension.getId()+"-");
        dimension.setLinkName(parentDim!=null?parentDim.getLinkName()+dimension.getName()+"-":"-"+dimension.getName()+"-");
        dimension.setLevel(parentDim!=null?parentDim.getLevel()+1:1);
        dimension.setParentName(parentDim!=null?parentDim.getName():"");
		dimensionDao.update(dimension);
	}
	
	@Override
	public void delete(Long id){
		dimensionDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		dimensionDao.deleteBatch(ids);
	}

    /**
     * ????????????
     * @param parentId
     * @return
     */
    @Override
    public List<DimensionEntity> queryListFilter(Long parentId) {
        return dimensionDao.queryListFilter(parentId);
    }

    /**
     * ????????????
     * @param parentId
     * @param response
     */
    @Override
    public void export(Map query, HttpServletResponse response) {
        List<DimensionEntity> queryList = dimensionDao.queryListTree(query);
        for (DimensionEntity dimensionEntity : queryList) {
            dimensionEntity.setName(dim2Space(dimensionEntity.getLevel(),dimensionEntity.getName()));
        }
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=Dimension.xls");
        excelService.exportExcel(new ExportParams(), DimensionEntity.class, queryList, response);
    
        
    }
    
    public String dim2Space(int level,String name){
        String newName="";
        for (int i = 0; i < level; i++) {
            newName+="  ";
        }
       return newName+name; 
    }

}

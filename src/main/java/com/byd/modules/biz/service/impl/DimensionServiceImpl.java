package com.byd.modules.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.byd.modules.biz.dao.DimensionDao;
import com.byd.modules.biz.entity.DimensionEntity;
import com.byd.modules.biz.service.DimensionService;



@Service("dimensionService")
public class DimensionServiceImpl implements DimensionService {
	@Autowired
	private DimensionDao dimensionDao;
	
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
		dimensionDao.save(dimension);
	}
	
	@Override
	public void update(DimensionEntity dimension){
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
	
}

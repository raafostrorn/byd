package com.byd.modules.biz.dao;

import com.byd.modules.biz.entity.DimensionEntity;
import com.byd.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 维度词库
 * 
 * @author kaiyue.cheng
 * @email 779363522@qq.com
 * @date 2017-12-22 13:58:27
 */
@Mapper
public interface DimensionDao extends BaseDao<DimensionEntity> {
	
}

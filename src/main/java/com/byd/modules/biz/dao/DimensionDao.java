package com.byd.modules.biz.dao;

import com.byd.modules.biz.entity.DimensionEntity;
import com.byd.modules.sys.dao.BaseDao;

import java.util.List;
import java.util.Map;

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

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param parentId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<DimensionEntity> queryListFilter(Long parentId);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param query
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<DimensionEntity> queryListTree(Map query);

	
}

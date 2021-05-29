package com.byd.modules.biz.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.byd.modules.biz.entity.CarModelEntity;
import com.byd.modules.sys.dao.BaseDao;

/**
 * 车型
 * 
 * @author kaiyue.cheng
 * @email 779363522@qq.com
 * @date 2017-12-22 13:58:27
 */
@Mapper
public interface CarModelDao extends BaseDao<CarModelEntity> {

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param parentId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<CarModelEntity> queryListFilter(Long parentId);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param query
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<CarModelEntity> queryListTree(Map query);

	
}

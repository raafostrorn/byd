package com.byd.modules.biz.dao;

import com.byd.modules.biz.entity.ScenePatternEntity;
import com.byd.modules.sys.dao.BaseDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 句式库
 * 
 * @author YMX
 * @email YMX@gmail.com
 * @date 2018-01-04 16:16:42
 */
@Mapper
public interface ScenePatternDao extends BaseDao<ScenePatternEntity> {

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List queryAll();
	
}

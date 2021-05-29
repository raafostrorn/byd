package com.byd.modules.biz.dao;

import com.byd.modules.biz.entity.EmotionEntity;
import com.byd.modules.sys.dao.BaseDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 情感词库
 * 
 * @author YMX
 * @email YMX@gmail.com
 * @date 2017-12-27 20:02:39
 */
@Mapper
public interface EmotionDao extends BaseDao<EmotionEntity> {

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List queryAll();
	
}

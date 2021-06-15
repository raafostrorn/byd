package com.byd.modules.biz.service;

import java.util.List;
import java.util.Map;

import com.byd.modules.biz.entity.SuperviseEntity;
import com.byd.modules.biz.entity.SuperviseREntity;

public interface SuperviseService {

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param params
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<SuperviseEntity> queryList(Map<String, Object> params);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    SuperviseEntity queryObject(String id);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param supervise
     * @see [类、类#方法、类#成员]
     */
    void update(SuperviseREntity supervise);
}

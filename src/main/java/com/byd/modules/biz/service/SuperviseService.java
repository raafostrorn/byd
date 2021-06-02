package com.byd.modules.biz.service;

import java.util.List;
import java.util.Map;

import com.byd.modules.biz.entity.SuperviseEntity;

public interface SuperviseService {
    String search(String value);

    void querySearch(String index, String type, String term, String queryString);

    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @param params
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<SuperviseEntity> queryList(Map<String, Object> params);
}

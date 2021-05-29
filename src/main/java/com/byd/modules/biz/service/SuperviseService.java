package com.byd.modules.biz.service;

public interface SuperviseService {
    String search(String value);

    void querySearch(String index, String type, String term, String queryString);
}

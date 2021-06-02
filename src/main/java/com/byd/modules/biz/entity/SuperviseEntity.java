/*
 * 文 件 名:  SuperviseEntity.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  卢慧 55318
 * 修改时间:  2018年1月5日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.byd.modules.biz.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author 姓名 工号
 * @version [版本号, 2018年1月5日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SuperviseEntity {
    private String website;
    private String bbs_name;
    private String car_series;
    private String user_id;
    private String user_name;
    private String post_time;
    private String thread_context;
    private String first_fault;
    private String second_fault;
    private String plan_fault;
    private String thread_url;
    private String create_date;
    private List<SuperviseSubEntity> result = new ArrayList<SuperviseSubEntity>();

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBbs_name() {
        return bbs_name;
    }

    public void setBbs_name(String bbs_name) {
        this.bbs_name = bbs_name;
    }

    public String getCar_series() {
        return car_series;
    }

    public void setCar_series(String car_series) {
        this.car_series = car_series;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public String getThread_context() {
        return thread_context;
    }

    public void setThread_context(String thread_context) {
        this.thread_context = thread_context;
    }

    public String getFirst_fault() {
        return first_fault;
    }

    public void setFirst_fault(String first_fault) {
        this.first_fault = first_fault;
    }

    public String getSecond_fault() {
        return second_fault;
    }

    public void setSecond_fault(String second_fault) {
        this.second_fault = second_fault;
    }

    public String getPlan_fault() {
        return plan_fault;
    }

    public void setPlan_fault(String plan_fault) {
        this.plan_fault = plan_fault;
    }

    public String getThread_url() {
        return thread_url;
    }

    public void setThread_url(String thread_url) {
        this.thread_url = thread_url;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public List<SuperviseSubEntity> getResult() {
        return result;
    }

    public void setResult(List<SuperviseSubEntity> result) {
        this.result = result;
    }

}

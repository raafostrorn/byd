package com.byd.modules.biz.entity;

import java.io.Serializable;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**
 * 句式库
 * 
 * @author YMX
 * @email YMX@gmail.com
 * @date 2018-01-04 16:16:42
 */
@ExcelTarget("ScenePatternEntity")
public class ScenePatternEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    // 场景分类
    @Excel(name = "场景分类", orderNum = "1")
    private String sentenceDemo;
    // 购车场景分类
    @Excel(name = "购车场景分类", orderNum = "2")
    private String scene;
    // 关键词
    @Excel(name = "句式", orderNum = "3")
    private String keyWord;
    // 类型
    @Excel(name = "类型", orderNum = "4")
    private String type;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：场景分类
     */
    public void setSentenceDemo(String sentenceDemo) {
        this.sentenceDemo = sentenceDemo;
    }

    /**
     * 获取：场景分类
     */
    public String getSentenceDemo() {
        return sentenceDemo;
    }

    /**
     * 设置：关键词
     */
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * 获取：关键词
     */
    public String getKeyWord() {
        return keyWord;
    }

    /**
     * 设置：类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取：类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置：购车场景分类
     */
    public void setScene(String scene) {
        this.scene = scene;
    }

    /**
     * 获取：购车场景分类
     */
    public String getScene() {
        return scene;
    }
}

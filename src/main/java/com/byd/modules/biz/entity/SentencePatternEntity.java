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
 * @date 2017-12-27 21:01:17
 */
@ExcelTarget("SentencePatternEntity")
public class SentencePatternEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    // 句式
    @Excel(name = "句式", orderNum = "1")
    private String sentenceDemo;
    //
    @Excel(name = "关键词", orderNum = "2")
    private String keyWord;
    // 属性
    @Excel(name = "类型", orderNum = "3")
    private String type;
    // 描述
    @Excel(name = "饱和度", orderNum = "4")
    private Integer sentenceWorth;
    //
    @Excel(name = "极性值", orderNum = "5")
    private Integer worth;

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
     * 设置：维度名称
     */
    public void setSentenceDemo(String sentenceDemo) {
        this.sentenceDemo = sentenceDemo;
    }

    /**
     * 获取：维度名称
     */
    public String getSentenceDemo() {
        return sentenceDemo;
    }

    /**
     * 设置：
     */
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * 获取：
     */
    public String getKeyWord() {
        return keyWord;
    }

    /**
     * 设置：属性
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取：属性
     */
    public String getType() {
        return type;
    }

    public void setWorth(Integer worth) {
        this.worth = worth;
    }

    public Integer getSentenceWorth() {
        return sentenceWorth;
    }

    public void setSentenceWorth(Integer sentenceWorth) {
        this.sentenceWorth = sentenceWorth;
    }

    /**
     * 获取：
     */
    public Integer getWorth() {
        return worth;
    }
}

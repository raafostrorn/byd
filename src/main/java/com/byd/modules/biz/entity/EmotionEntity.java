package com.byd.modules.biz.entity;

import java.io.Serializable;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**
 * 情感词库
 * 
 * @author YMX
 * @email YMX@gmail.com
 * @date 2017-12-27 20:02:39
 */
@ExcelTarget("EmotionEntity")
public class EmotionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    @Excel(name = "情感词", orderNum = "1")
    private String emotionWord;

    private Long dimensionId;
    //
    @Excel(name = "维度", orderNum = "2")
    private String dimensionName;
    // 属性
    @Excel(name = "极性值", orderNum = "3")
    private Integer worth;
    // 描述
    @Excel(name = "故障", orderNum = "4")
    private Integer isfault;
    //
    @Excel(name = "别名", orderNum = "5")
    private String emotionNickname;

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
    public void setEmotionWord(String emotionWord) {
        this.emotionWord = emotionWord;
    }

    /**
     * 获取：维度名称
     */
    public String getEmotionWord() {
        return emotionWord;
    }

    /**
     * 设置：属性
     */
    public void setWorth(Integer worth) {
        this.worth = worth;
    }

    /**
     * 获取：属性
     */
    public Integer getWorth() {
        return worth;
    }

    public Long getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(Long dimensionId) {
        this.dimensionId = dimensionId;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    /**
     * 设置：描述
     */
    public void setIsfault(Integer isfault) {
        this.isfault = isfault;
    }

    /**
     * 获取：描述
     */
    public Integer getIsfault() {
        return isfault;
    }

    /**
     * 设置：
     */
    public void setEmotionNickname(String emotionNickname) {
        this.emotionNickname = emotionNickname;
    }

    /**
     * 获取：
     */
    public String getEmotionNickname() {
        return emotionNickname;
    }
}

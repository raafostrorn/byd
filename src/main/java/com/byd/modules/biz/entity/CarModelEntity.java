package com.byd.modules.biz.entity;

import java.io.Serializable;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;


/**
 * 车型
 * 
 * @author kaiyue.cheng
 * @email 779363522@qq.com
 * @date 2017-12-22 13:58:27
 */
@ExcelTarget("DimensionEntity")
public class CarModelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    // 父菜单ID，一级菜单为0
    private Long parentId;
    // 维度名称
    @Excel(name = "车型", orderNum = "1")
    private String name;
    // 属性
    private String type;
    // 描述
    @Excel(name = "别名", orderNum = "1")
    private String nickname;
    //
    private String linkId;

    private String linkName;

    private Integer level;

    // 父菜单name
    private String parentName;

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
     * 设置：父菜单ID，一级菜单为0
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：父菜单ID，一级菜单为0
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置：维度名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：维度名称
     */
    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }


}

package com.byd.modules.biz.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 维度词库
 * 
 * @author kaiyue.cheng
 * @email 779363522@qq.com
 * @date 2017-12-22 13:58:27
 */
public class DimensionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//父菜单ID，一级菜单为0
	private Long parentId;
	//维度名称
	private String name;
	//属性
	private String attributes;
	//描述
	private String description;
	//
	private String json;
	

	//父菜单name
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
	/**
	 * 设置：属性
	 */
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	/**
	 * 获取：属性
	 */
	public String getAttributes() {
		return attributes;
	}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：
	 */
	public void setJson(String json) {
		this.json = json;
	}
	/**
	 * 获取：
	 */
	public String getJson() {
		return json;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}

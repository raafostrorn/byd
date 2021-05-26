package com.byd.modules.biz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.byd.modules.biz.entity.DimensionEntity;
import com.byd.modules.biz.service.DimensionService;
import com.byd.modules.sys.entity.SysMenuEntity;
import com.byd.common.utils.PageUtils;
import com.byd.common.utils.Query;
import com.byd.common.utils.R;

/**
 * 维度词库
 * 
 * @author kaiyue.cheng
 * @email 779363522@qq.com
 * @date 2017-12-22 13:58:27
 */
@RestController
@RequestMapping("dimension")
public class DimensionController {
	@Autowired
	private DimensionService dimensionService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("dimension:list")
	public List<DimensionEntity>  list(@RequestParam Map<String, Object> params){
		//查询列表数据
//        Query query = new Query(params);
//
//		List<DimensionEntity> dimensionList = dimensionService.queryList(query);
//		int total = dimensionService.queryTotal(query);
//		
//		PageUtils pageUtil = new PageUtils(dimensionList, total, query.getLimit(), query.getPage());
//		
//		return R.ok().put("page", pageUtil);
		

		List<DimensionEntity> list = dimensionService.queryList(new HashMap<String, Object>());
		return list;
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("dimension:info")
	public R info(@PathVariable("id") Long id){
		DimensionEntity dimension = dimensionService.queryObject(id);
		return R.ok().put("dimension", dimension);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("dimension:save")
	public R save(@RequestBody DimensionEntity dimension){
		dimensionService.save(dimension);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("dimension:update")
	public R update(@RequestBody DimensionEntity dimension){
		dimensionService.update(dimension);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("dimension:delete")
	public R delete(@RequestBody Long id){
		dimensionService.delete(id);
		return R.ok();
	}
	
}

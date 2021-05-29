package com.byd.modules.biz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.byd.modules.biz.entity.EmotionEntity;
import com.byd.modules.biz.service.EmotionService;
import com.byd.common.utils.PageUtils;
import com.byd.common.utils.Query;
import com.byd.common.utils.R;




/**
 * 情感词库
 * 
 * @author YMX
 * @email YMX@gmail.com
 * @date 2017-12-27 20:02:39
 */
@RestController
@RequestMapping("/emotion")
public class EmotionController {
	@Autowired
	private EmotionService emotionService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("emotion:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<EmotionEntity> emotionList = emotionService.queryList(query);
		int total = emotionService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(emotionList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("emotion:info")
	public R info(@PathVariable("id") Long id){
		EmotionEntity emotion = emotionService.queryObject(id);
		
		return R.ok().put("emotion", emotion);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("emotion:save")
	public R save(@RequestBody EmotionEntity emotion){
		emotionService.save(emotion);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("emotion:update")
	public R update(@RequestBody EmotionEntity emotion){
		emotionService.update(emotion);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("emotion:delete")
	public R delete(@RequestBody Long[] ids){
		emotionService.deleteBatch(ids);
		
		return R.ok();
	}
	
	@RequestMapping("/export")
    public void exportFeaturesByParam(HttpServletResponse response) {
        emotionService.export(response);
    }
	
}

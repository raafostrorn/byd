package com.byd.modules.biz.controller;

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

import com.byd.modules.biz.entity.SentencePatternEntity;
import com.byd.modules.biz.service.SentencePatternService;
import com.byd.common.utils.PageUtils;
import com.byd.common.utils.Query;
import com.byd.common.utils.R;




/**
 * 句式库
 * 
 * @author YMX
 * @email YMX@gmail.com
 * @date 2017-12-27 21:01:17
 */
@RestController
@RequestMapping("/sentencepattern")
public class SentencePatternController {
	@Autowired
	private SentencePatternService sentencePatternService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sentencepattern:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SentencePatternEntity> sentencePatternList = sentencePatternService.queryList(query);
		int total = sentencePatternService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sentencePatternList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sentencepattern:info")
	public R info(@PathVariable("id") Long id){
		SentencePatternEntity sentencePattern = sentencePatternService.queryObject(id);
		
		return R.ok().put("sentencePattern", sentencePattern);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sentencepattern:save")
	public R save(@RequestBody SentencePatternEntity sentencePattern){
		sentencePatternService.save(sentencePattern);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sentencepattern:update")
	public R update(@RequestBody SentencePatternEntity sentencePattern){
		sentencePatternService.update(sentencePattern);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sentencepattern:delete")
	public R delete(@RequestBody Long[] ids){
		sentencePatternService.deleteBatch(ids);
		
		return R.ok();
	}
	
	@RequestMapping("/export")
    public void exportFeaturesByParam(HttpServletResponse response) {
	    sentencePatternService.export(response);
    }
}

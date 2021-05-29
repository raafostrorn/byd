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

import com.byd.modules.biz.entity.ScenePatternEntity;
import com.byd.modules.biz.service.ScenePatternService;
import com.byd.common.utils.PageUtils;
import com.byd.common.utils.Query;
import com.byd.common.utils.R;




/**
 * 句式库
 * 
 * @author YMX
 * @email YMX@gmail.com
 * @date 2018-01-04 16:16:42
 */
@RestController
@RequestMapping("/scenepattern")
public class ScenePatternController {
	@Autowired
	private ScenePatternService scenePatternService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("scenepattern:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ScenePatternEntity> scenePatternList = scenePatternService.queryList(query);
		int total = scenePatternService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(scenePatternList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("scenepattern:info")
	public R info(@PathVariable("id") Long id){
		ScenePatternEntity scenePattern = scenePatternService.queryObject(id);
		
		return R.ok().put("scenePattern", scenePattern);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("scenepattern:save")
	public R save(@RequestBody ScenePatternEntity scenePattern){
		scenePatternService.save(scenePattern);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("scenepattern:update")
	public R update(@RequestBody ScenePatternEntity scenePattern){
		scenePatternService.update(scenePattern);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("scenepattern:delete")
	public R delete(@RequestBody Long[] ids){
		scenePatternService.deleteBatch(ids);
		
		return R.ok();
	}
	
	@RequestMapping("/export")
    public void exportFeaturesByParam(HttpServletResponse response) {
	    scenePatternService.export(response);
    }
	
}

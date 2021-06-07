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
import org.springframework.web.bind.annotation.RestController;

import com.byd.common.utils.R;
import com.byd.modules.biz.entity.DimensionEntity;
import com.byd.modules.biz.service.DimensionService;

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
    @RequestMapping("/list/{selectType}/{selectId}")
    // @RequiresPermissions("dimension:list")
    public List<DimensionEntity> listTreeGrid(@PathVariable("selectType") String selectType,@PathVariable("selectId") Long selectId) {
    	Map<String, Object> query = new HashMap<>();
    	if (!selectType.equals("null")) {
            query.put("selectType", selectType);
		}
        query.put("selectId", selectId);
        List<DimensionEntity> list = dimensionService.queryList(query);
        if (list != null && list.size() > 1) {
            list.get(0).setParentId(0L);
        }
        return list;
    }

    /**
     * 列表
     */
    @RequestMapping("/listfilter/{parentId}")
    // @RequiresPermissions("dimension:list")
    public List<DimensionEntity> listFilter(@PathVariable("parentId") Long parentId) {
        List<DimensionEntity> list = dimensionService.queryListFilter(parentId);
        return list;
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("dimension:info")
    public R info(@PathVariable("id") Long id) {
        DimensionEntity dimension = dimensionService.queryObject(id);
        return R.ok().put("dimension", dimension);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("dimension:save")
    public R save(@RequestBody DimensionEntity dimension) {
        dimensionService.save(dimension);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("dimension:update")
    public R update(@RequestBody DimensionEntity dimension) {
        dimensionService.update(dimension);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("dimension:delete")
    public R delete(@RequestBody Long id) {
        dimensionService.delete(id);
        return R.ok();
    }

    @RequestMapping("/export")
    public void exportFeaturesByParam(Long selectId, HttpServletResponse response) {
        Map query = new HashMap<>();
        query.put("selectId", selectId);
        dimensionService.export(query, response);
    }

}

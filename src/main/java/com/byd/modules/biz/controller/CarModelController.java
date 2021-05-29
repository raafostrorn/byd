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
import com.byd.modules.biz.entity.CarModelEntity;
import com.byd.modules.biz.service.CarModelService;

/**
 * 维度词库
 * 
 * @author kaiyue.cheng
 * @email 779363522@qq.com
 * @date 2017-12-22 13:58:27
 */
@RestController
@RequestMapping("carmodel")
public class CarModelController {
    @Autowired
    private CarModelService carModelService;

    /**
     * 列表
     */
    @RequestMapping("/list/{selectId}")
    // @RequiresPermissions("carmodel:list")
    public List<CarModelEntity> listTreeGrid(@PathVariable("selectId") Long selectId) {
        Map query = new HashMap<>();
        query.put("selectId", selectId);
        List<CarModelEntity> list = carModelService.queryList(query);
        if (list != null && list.size() > 1) {
            list.get(0).setParentId(0L);
        }
        return list;
    }

    /**
     * 列表
     */
    @RequestMapping("/listfilter/{parentId}")
    // @RequiresPermissions("carmodel:list")
    public List<CarModelEntity> listFilter(@PathVariable("parentId") Long parentId) {
        List<CarModelEntity> list = carModelService.queryListFilter(parentId);
        return list;
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("carmodel:info")
    public R info(@PathVariable("id") Long id) {
        CarModelEntity carmodel = carModelService.queryObject(id);
        return R.ok().put("carmodel", carmodel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("carmodel:save")
    public R save(@RequestBody CarModelEntity carmodel) {
        carModelService.save(carmodel);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("carmodel:update")
    public R update(@RequestBody CarModelEntity carmodel) {
        carModelService.update(carmodel);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("carmodel:delete")
    public R delete(@RequestBody Long id) {
        carModelService.delete(id);
        return R.ok();
    }

    @RequestMapping("/export")
    public void exportFeaturesByParam(Long selectId, HttpServletResponse response) {
        Map query = new HashMap<>();
        query.put("selectId", selectId);
        carModelService.export(query, response);
    }

}

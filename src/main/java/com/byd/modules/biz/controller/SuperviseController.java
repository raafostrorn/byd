package com.byd.modules.biz.controller;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.byd.common.utils.R;
import com.byd.modules.biz.entity.SuperviseEntity;
import com.byd.modules.biz.entity.SuperviseREntity;
import com.byd.modules.biz.service.SuperviseService;
 
@RestController
@RequestMapping("/supervise")
public class SuperviseController {
    Logger logger = Logger.getLogger(SuperviseController.class);
    @Autowired
    SuperviseService superviseService;

    /**
     * 信息
     */
    @RequestMapping("/info")
    public R info(String docid) {
        SuperviseEntity supervise = superviseService.queryObject(docid);
        return R.ok().put("supervise", supervise);
    }    
    
    /**
     * 列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params){
        List<SuperviseEntity> list = superviseService.queryList(params);
        return R.ok().put("page", list);
    }
    
    
    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SuperviseREntity supervise) {
        superviseService.update(supervise);
        return R.ok();
    }
}
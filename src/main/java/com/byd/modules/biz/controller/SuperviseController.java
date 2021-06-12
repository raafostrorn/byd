package com.byd.modules.biz.controller;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.byd.common.utils.R;
import com.byd.modules.biz.entity.DimensionEntity;
import com.byd.modules.biz.entity.SuperviseEntity;
import com.byd.modules.biz.service.SuperviseService;
 
@Controller
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
}
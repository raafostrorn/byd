package com.byd.modules.biz.controller;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.byd.modules.biz.service.SuperviseService;
 
@Controller
@RequestMapping("/supervise")
public class SuperviseController {
    Logger logger = Logger.getLogger(SuperviseController.class);
    @Autowired
    SuperviseService superviseService;

    @RequestMapping("/search_{value}")
    @ResponseBody
    public String search(@PathVariable("value") String value) {
        return superviseService.search(value);
    }
    
    @RequestMapping("/search1_{value}")
    public void search1(@PathVariable("value") String value) {
        superviseService.querySearch("byd", "article", "thread_context", value);
    }
}
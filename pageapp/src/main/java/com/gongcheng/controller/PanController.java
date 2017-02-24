package com.gongcheng.controller;

import com.gongcheng.pojo.Disk;
import com.gongcheng.service.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dell on 2017/2/21.
 */
@Controller
@RequestMapping("/pan")
public class PanController {
    @Autowired
    private DiskService diskService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(required = false,defaultValue = "0")Integer path, Model model){
        List<Disk> diskList = diskService.findByFid(path);
        model.addAttribute("diskList",diskList);
        model.addAttribute("fid",path);
        return "/disk/list";
    }

    @RequestMapping(value = ("/new"),method = RequestMethod.POST)
    @ResponseBody
    public String newDisk(Disk disk){

        diskService.saveNewDisk(disk);
        return "redirect:/pan";
    }



}


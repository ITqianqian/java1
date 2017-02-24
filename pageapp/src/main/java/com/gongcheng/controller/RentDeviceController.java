package com.gongcheng.controller;

import com.gongcheng.pojo.Device;
import com.gongcheng.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by dell on 2017/2/23.
 */
@Controller
@RequestMapping("/device/rent")
public class RentDeviceController {
    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String newRent(Model model){


        List<Device> deviceList = deviceService.findAllDevice();
        model.addAttribute("deviceList",deviceList);
        return "/device/rent/new";
    }

}

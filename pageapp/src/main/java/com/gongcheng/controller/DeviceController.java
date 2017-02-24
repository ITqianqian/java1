package com.gongcheng.controller;
import com.gongcheng.pojo.Device;
import com.gongcheng.service.DeviceService;
import com.google.common.collect.Maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/2/20.
 */
@Controller
@RequestMapping("/setting/device")
public class DeviceController {


    @Autowired
    private DeviceService deviceService;

    @RequestMapping(method = RequestMethod.GET)
    public String listDevice(){
        return "/device/list";

    }
    @RequestMapping(value = ("/new"),method = RequestMethod.GET)
    public String newDevice(){
        return "/device/new";
    }


    @RequestMapping(value = ("/new"),method = RequestMethod.POST)
    public String newDevice(Device device, RedirectAttributes redirectAttributes){

        deviceService.saveNewDevice(device);
        redirectAttributes.addFlashAttribute("messge","添加成功");
        return "redirect:/setting/device";
    }
    /*@RequestMapping(value = ("/rent/list"),method = RequestMethod.GET)
    public String listRent(Model model){


    }*/



    @RequestMapping(value = "/load",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> load(HttpServletRequest request){
       String draw = request.getParameter("draw");
       String start=request.getParameter("start");
       String length=request.getParameter("length");
       String orderIndex = request.getParameter("order[0][column]");
       String orderType = request.getParameter("order[0][dir]");
       String orderColumn = request.getParameter("columns["+orderIndex+"][name]");
       String deviceName = request.getParameter("deviceName");
       String sarchvalue = request.getParameter("search[value]");

       Map<String,Object> sarchParm =Maps.newHashMap();
       sarchParm.put("start",start);
       sarchParm.put("length",length);
       sarchParm.put("orderType",orderType);
       sarchParm.put("orderIndex",orderIndex);
       sarchParm.put("orderColumn",orderColumn);

       List<Device> deviceList = deviceService.findDeviceBySarchparm(sarchParm);

       Long count = deviceService.count();


       Map<String,Object> requestMap = Maps.newHashMap();

       requestMap.put("draw",draw);
       requestMap.put("recordsTotal",count);
       requestMap.put("recordsFiltered",count);
       requestMap.put("data",deviceList);


       return requestMap;

    }

    @RequestMapping(value = "/{id:\\d+}/del",method = RequestMethod.GET)
    @ResponseBody
    public String delDevice(@PathVariable Integer id){
        deviceService.delDeviceById(id);
        return "success";
    }



}

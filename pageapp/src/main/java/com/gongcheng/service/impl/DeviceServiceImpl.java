package com.gongcheng.service.impl;

import com.gongcheng.mapper.DeviceMapper;
import com.gongcheng.pojo.Device;
import com.gongcheng.pojo.User;
import com.gongcheng.service.DeviceService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/2/20.
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    private Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Autowired
    private DeviceMapper deviceMapper;





    @Override
    public void saveNewDevice(Device device) {
        deviceMapper.addNewDevice(device);
        /*Subject subject=SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        logger.info("{}添加了设备{}",user.getUserName(),device.getDeviceName());*/

}

    public List<Device> findAllDevice() {

        return deviceMapper.findAll();
    }

    @Override
    public List<Device> findDeviceByPage(String start, String length) {
        return deviceMapper.findDeviceByPage(start,length);
    }

    @Override
    public Long count() {
        return deviceMapper.count();
    }

    @Override
    public List<Device> findDeviceBySarchparm(Map<String, Object> sarchParm) {
        return deviceMapper.findDeviceBySarchparm(sarchParm);
    }

    @Override
    public Long sarchCount(Map<String, Object> sarchParm) {
        return deviceMapper.sarchCount(sarchParm);
    }

    @Override
    public void delDeviceById(Integer id) {
        deviceMapper.delDeviceById(id);
    }


}

package com.gongcheng.service;

import com.gongcheng.pojo.Device;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/2/20.
 */
public interface DeviceService {
    void saveNewDevice(Device device);

    List<Device> findAllDevice();

    List<Device> findDeviceByPage(String start, String length);

    Long count();

    List<Device> findDeviceBySarchparm(Map<String, Object> sarchParm);


    Long sarchCount(Map<String, Object> sarchParm);

    void delDeviceById(Integer id);
}

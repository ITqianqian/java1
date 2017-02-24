package com.gongcheng.mapper;

import com.gongcheng.pojo.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/2/20.
 */
public interface DeviceMapper {


    void addNewDevice(Device device);

    List<Device> findAll();

    List<Device> findDeviceByPage(@Param("start") String start,@Param("length") String length);

    Long count();

    List<Device> findDeviceBySarchparm(Map<String, Object> sarchParm);


    Long sarchCount(Map<String, Object> sarchParm);

    void delDeviceById(Integer id);
}

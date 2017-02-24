package com.gongcheng.service.impl;

import com.gongcheng.mapper.DiskMapper;
import com.gongcheng.pojo.Disk;
import com.gongcheng.service.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dell on 2017/2/21.
 */
@Service
public class DiskServiceImpl implements DiskService {
    @Autowired
    private DiskMapper diskMapper;

    @Override
    public List<Disk> findByFid(Integer path) {
        return diskMapper.findByFid(path);
    }

    @Override
    public void saveNewDisk(Disk disk) {
        
    }
}

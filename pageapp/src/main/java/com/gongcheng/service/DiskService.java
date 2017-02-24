package com.gongcheng.service;

import com.gongcheng.pojo.Disk;

import java.util.List;

/**
 * Created by dell on 2017/2/21.
 */

public interface DiskService {
    List<Disk> findByFid(Integer path);

    void saveNewDisk(Disk disk);
}

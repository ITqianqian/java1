package com.gongcheng.mapper;

import com.gongcheng.pojo.Disk;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dell on 2017/2/21.
 */
public interface DiskMapper {
    List<Disk> findByFid(@Param("fid") Integer path);
}

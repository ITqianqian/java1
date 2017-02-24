package com.gongcheng.pojo;

import java.io.Serializable;

/**
 * Created by dell on 2017/2/21.
 */
public class Disk implements Serializable {


    private Integer id;
    private String scourceName;
    private String name;
    private Integer fid;
    private String size;
    private String createTime;
    private String createName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScourceName() {
        return scourceName;
    }

    public void setScourceName(String scourceName) {
        this.scourceName = scourceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Override
    public String toString() {
        return "Disk{" +
                "id=" + id +
                ", scourceName='" + scourceName + '\'' +
                ", name='" + name + '\'' +
                ", fid=" + fid +
                ", size='" + size + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createName='" + createName + '\'' +
                '}';
    }
}

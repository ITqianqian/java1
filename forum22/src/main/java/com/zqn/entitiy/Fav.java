package com.zqn.entitiy;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/12/24 0024.
 */
public class Fav {
    private Integer userid;
    private Integer topicid;
    private Timestamp createtime;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTopicid() {
        return topicid;
    }

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
}
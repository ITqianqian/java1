package com.zqn.dao;

import com.zqn.entity.Reply;
import com.zqn.util.DbHelp;

/**
 * Created by dell on 2016/12/26.
 */
public class ReplyDao {
    public void addNewReply(Reply reply) {
        String sql = "insert into t_reply(content,userid,topicid)values(?,?,?)";
        DbHelp.update(sql,reply.getContent(),reply.getUserid(),reply.getTopicid());
    }
}

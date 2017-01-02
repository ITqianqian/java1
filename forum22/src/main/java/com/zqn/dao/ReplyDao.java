package com.zqn.dao;

import com.zqn.entitiy.Reply;
import com.zqn.entitiy.User;
import com.zqn.util.Config;
import com.zqn.util.DbHelp;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.handlers.AbstractListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by Administrator on 2016/12/22 0022.
 */
public class ReplyDao {
    public void addReply(Reply reply) {
        String sql="insert into t_reply (content,userid,topicid) values(?,?,?)";
        DbHelp.update(sql,reply.getContent(),reply.getUserid(),reply.getTopicid());

    }

    public List<Reply> findReplyListByTopicid(String topicid) {
        String sql="select tu.id,tu.avatar,tu.username,tr.* from t_reply tr,t_user tu where tr.userid=tu.id and topicid=?";
        return DbHelp.query(sql, new AbstractListHandler<Reply>() {
            @Override
            protected Reply handleRow(ResultSet rs) throws SQLException {
                Reply reply=new BasicRowProcessor().toBean(rs,Reply.class);
                User user=new User();
                user.setAvatar(Config.get("qiniu.domain")+rs.getString("avatar"));
                user.setUsername(rs.getString("username"));
                user.setId(rs.getInt("id"));
                reply.setUser(user);
                return reply;

            }
        },topicid);
    }

    public void delByTopicId(String id) {
        String sql = "delete from t_reply where topicid = ?";
        DbHelp.update(sql,id);
    }
}

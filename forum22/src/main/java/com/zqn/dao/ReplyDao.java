package com.zqn.dao;

import com.zqn.entity.Reply;
import com.zqn.entity.User;
import com.zqn.util.Config;
import com.zqn.util.DbHelp;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.handlers.AbstractListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dell on 2016/12/26.
 */
public class ReplyDao {
    public void addNewReply(Reply reply) {
        String sql = "insert into t_reply(content,userid,topicid)values(?,?,?)";
        DbHelp.update(sql,reply.getContent(),reply.getUserid(),reply.getTopicid());
    }

    public List<Reply> findAllReplyByTopic(Integer topicid) {
      /*  String sql = "tu.avatar,tu.username,tr.* FROM t_reply tr ,t_user tu WHERE tr.userid = tu.id AND topicid =? ";
         List<Reply> replyList =DbHelp.query(sql, new AbstractListHandler<Reply>() {
            @Override
            protected Reply handleRow(ResultSet rs) throws SQLException {

                Reply reply = new BasicRowProcessor().toBean(rs,Reply.class);
                User user = new User();
                user.setAvatar(Config.get("qiniu.domain") + rs.getString("avatar"));
                user.setUsername(rs.getString("username"));
                user.setId(rs.getInt("id"));
                reply.setUser(user);
                return reply;
            }
        }, topicid);
         return replyList;*/
        String sql = "select * from t_reply where topicid=?";
        return DbHelp.query(sql,new BeanListHandler<>(Reply.class),topicid);
    }

}

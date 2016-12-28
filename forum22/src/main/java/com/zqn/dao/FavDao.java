package com.zqn.dao;

import com.zqn.entitiy.Fav;
import com.zqn.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by Administrator on 2016/12/24 0024.
 */
public class FavDao {

    public Fav findFavByUseridAndTopicid(Integer topicid, Integer userid) {
        String sql="select * from t_fav where topicid=? and userid=?";
        return DbHelp.query(sql,new BeanHandler<>(Fav.class),topicid,userid);
    }

    public void addFav(Fav fav) {
        String sql="insert into t_fav (topicid,userid)values (?,?)";
        DbHelp.update(sql,fav.getTopicid(),fav.getUserid());
    }

    public void deleteFav(String topicid, Integer userid) {
        String sql="delete from t_fav where topicid=? and userid=?";
        DbHelp.update(sql,Integer.valueOf(topicid),userid);
    }
}

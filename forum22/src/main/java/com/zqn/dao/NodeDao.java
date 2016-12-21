package com.zqn.dao;

import com.zqn.entity.Node;
import com.zqn.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by dell on 2016/12/21.
 */
public class NodeDao {


    public List<Node> findAllnode(){
        String sql = "select * from t_node";
        return DbHelp.query(sql,new BeanListHandler<>(Node.class));

    }


    public Node findNodeById(Integer nodeid) {
        String sql ="select * from t_node where id =? ";
        return DbHelp.query(sql,new BeanHandler<Node>(Node.class),nodeid);
    }
}
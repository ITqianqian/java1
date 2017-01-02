package com.zqn.service;


import com.zqn.dao.NodeDao;
import com.zqn.entitiy.Node;
import com.zqn.exception.ServiceException;
import com.zqn.util.StringUtils;

public class NodeService {

    NodeDao nodeDao = new NodeDao();

    public void updateNode(String nodeId, String nodeName) {
        if (StringUtils.isNumeric(nodeId) && StringUtils.isNotEmpty(nodeName)) {
            Node node = nodeDao.findNodeById(Integer.valueOf(nodeId));
            if(node != null) {
                node.setNodename(nodeName);
                nodeDao.update(node);
            }else{
                throw new ServiceException("该节点不存在");
            }

        } else {
            throw new ServiceException("参数异常");
        }

    }

    public String validateNodeName(String nodeId, String nodeName) {
        // 根据nodeid查询node,并判断nodeName是否等于node的nodename
        Node node = nodeDao.findNodeById(Integer.valueOf(nodeId));
        if (node.getNodename().equals(nodeName)) {
            return "true";
        } else {
            Node nodeIsIn = nodeDao.findByNodeName(nodeName);
            if (nodeIsIn == null) {
                return "true";
            }
        }
        return "false";
    }

    public void delNodeById(String id) {
        Node node = nodeDao.findNodeById(Integer.valueOf(id));
        if (node.getTopicnum() > 0){
            throw  new ServiceException("该节点下已有主题,不可删除");
        }else{
            nodeDao.del(id);
        }
    }

    public void addnode(String nodename) {
        Node node = nodeDao.findByNodeName(nodename);
        if(node!= null){
            throw new ServiceException("账户已被占用");
        }else{
            nodeDao.saveNode(nodename);
        }
    }
}

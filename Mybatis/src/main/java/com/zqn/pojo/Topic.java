package com.zqn.pojo;

/**
 * Created by dell on 2017/1/4.
 */
public class Topic {
    private  Integer id;
    private  String topicname;
    private  Integer nodeid;
    private  Node node;

    public Topic(){}

    public Topic(String topicname, Integer nodeid) {
        this.topicname = topicname;
        this.nodeid = nodeid;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public Integer getNodeid() {
        return nodeid;
    }

    public void setNodeid(Integer nodeid) {
        this.nodeid = nodeid;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", topicname='" + topicname + '\'' +
                ", nodeid=" + nodeid +
                ", node=" + node +
                '}';
    }
}

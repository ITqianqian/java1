package com.zqn;

import com.zqn.mapper.TopicMapper;
import com.zqn.mapper.UserMapper;
import com.zqn.pojo.Node;
import com.zqn.pojo.Topic;
import com.zqn.pojo.User;
import com.zqn.util.SqlSessionFactoryUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.*;


/**
 * Created by dell on 2017/1/4.
 */
public class MybatisTestCase {
    @Test
    public void readXml(){
        //1.读取 classpath中的配置文件
        Reader reader = null;
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        try {
            reader = Resources.getResourceAsReader("mybatis.xml");
            //2.构建SqlSessionFactory对象
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            //3.创建sqlsession对象
            sqlSession = sqlSessionFactory.openSession();

            //执行查询单个对象语句
            User user = sqlSession.selectOne("com.zqn.mapper.UserMapper.findById",1);
            //释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void findByIds(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = userMapper.findByIds(Arrays.asList(1,2));

        for(User user : userList){
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            System.out.println("----------------------");
        }
        sqlSession.close();

    }
    @Test
    public void findById(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        User user = sqlSession.selectOne("com.zqn.mapper.UserMapper.findById",1);
        System.out.println(user.getUsername());

        sqlSession.close();
    }
    @Test
    public void save(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        Node node = new Node();
        node.setNodename("jsp");

        sqlSession.insert("com.zqn.mapper.NodeMapper.save",node);

        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void saveTopic(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        Topic topic = new Topic();
        topic.setTopicname("jsp应用");
        topic.setNodeid(5);

        sqlSession.insert("com.zqn.mapper.TopicMapper.save",topic);

        sqlSession.commit();
        sqlSession.close();

    }
    @Test
    public void update(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        Topic topic = sqlSession.selectOne("com.zqn.mapper.TopicMapper.findById",4);

        topic.setNodeid(2);
        topic.setTopicname("aa");

        sqlSession.update("com.zqn.mapper.TopicMapper.update",topic);

        sqlSession.commit();
        sqlSession.close();

    }
   /* @Test
    public void findAll(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        TopicMapper topicMapper = sqlSession.getMapper(TopicMapper.class);

        List<Topic> topicList = topicMapper.findAll();

        for(Topic topic : topicList){
            System.out.println(topic.getTopicname());
            Node node = topic.getNode();
            System.out.println(node.getNodename());
            System.out.println("--------------------------");
        }

        sqlSession.close();

    }*/
    @Test
    public void findBynodeid(){

        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        TopicMapper topicMapper = sqlSession.getMapper(TopicMapper.class);

        List<Topic> topicList = topicMapper.findByNodeid(1);

        for(Topic topic : topicList){
            System.out.println(topic.getTopicname());
            Node node = topic.getNode();
            System.out.println(node.getNodename());
            System.out.println("--------------------------");
        }




        sqlSession.close();

    }
    @Test
    public void saveUser (){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = new ArrayList<>();
        userList.add(new User("王武","123456"));
        userList.add(new User("aa","123123"));

        userMapper.Save(userList);
        sqlSession.commit();
        sqlSession.close();


    }
    @Test
    public void findByUsernameAndPassword(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("username","jack");
        param.put("password","123456");

        User user = userMapper.findByUsernameAndPassword(param);
        System.out.println(user);
        sqlSession.close();

    }
    @Test
    public void bachSave(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        TopicMapper topicMapper = sqlSession.getMapper(TopicMapper.class);

        List<Topic> topicList = new ArrayList<>();
        topicList.add(new Topic("心情大",2));
        topicList.add(new Topic("出去走",2));

        topicMapper.bachSave(topicList);
        sqlSession.commit();
        sqlSession.close();
    }

}

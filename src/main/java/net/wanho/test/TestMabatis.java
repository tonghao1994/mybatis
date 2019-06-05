package net.wanho.test;

import net.wanho.entity.User;
import net.wanho.mapper.UserMapper;
import net.wanho.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * Created by tonghao on 2019/6/5.
 */
public class TestMabatis {
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void  before(){
        /*InputStream is = TestMabatis.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
        sqlSession = sf.openSession();*/
        SqlSession sqlSession = MybatisUtil.getSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void testAdd(){
        User user = new User(null,"fff","1234");
        //sqlSession.update("net.wanho.mapper.UserMapper.add",user);
        userMapper.add(user);
        SqlSession sqlSession = MybatisUtil.getSession();
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdate(){
        User user = new User(39,"mazi","12345");
        //sqlSession.update("net.wanho.mapper.UserMapper.update",user);
        userMapper.update(user);
        SqlSession session = MybatisUtil.getSession();
        session.commit();
        session.close();
    }

    @Test
    public void testDelete(){
        User user = new User(30);
        /*sqlSession.update("net.wanho.mapper.UserMapper.delete",user);
        sqlSession.commit();
        sqlSession.close();*/
        sqlSession = MybatisUtil.getSession();
        sqlSession.update("net.wanho.mapper.UserMapper.delete",user);
        sqlSession.commit();
        MybatisUtil.closeSession();
    }

    @Test
    public void testSelectAll(){
        SqlSession session = MybatisUtil.getSession();
        List<User> users = session.selectList("net.wanho.mapper.UserMapper.selectAll");
        System.out.println(users);
        MybatisUtil.closeSession();
    }

    @Test
    public void testSelectOne(){
        /*SqlSession session = MybatisUtil.getSession();
        User user = session.selectOne("net.wanho.mapper.UserMapper.selectUserByName", "bbb");
        System.out.println(user);
        MybatisUtil.closeSession();*/
        User user = userMapper.selectUserByName("bbb");
        System.out.println(user);
        MybatisUtil.closeSession();
    }

    @Test
    public void testSelectUser(){
        List<User> users = userMapper.selectUserByUsername("%b%");
        System.out.println(users);
        MybatisUtil.closeSession();
    }

    @Test
    public void testFindUser(){
        User user = new User();
        List<User> users = userMapper.findUser(user);
        System.out.println(users);
        MybatisUtil.closeSession();
    }

    @Test
    public void testReturnKey(){
        User user = new User(null,"zhaoliu","1234");
        userMapper.returnKey(user);
        SqlSession sqlSession = MybatisUtil.getSession();
        sqlSession.commit();
        System.out.println(user.getId());
    }

    @Test
    public void testReturnKey2(){
        User user = new User(null,"zhaoliu","1234");
        userMapper.returnKey2(user);
        SqlSession sqlSession = MybatisUtil.getSession();
        sqlSession.commit();
        System.out.println(user.getId());
    }

    @Test
    public void testSelectOneByUsernameAndPswd(){
        List<User> users = userMapper.selectUserByUsernameAndPswd("bbb", "1234");
        System.out.println(users);
        MybatisUtil.closeSession();
    }
}

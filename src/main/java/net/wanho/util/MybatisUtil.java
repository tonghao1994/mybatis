package net.wanho.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by tonghao on 2019/6/5.
 */
public class MybatisUtil {
    private static ThreadLocal<SqlSession>threadLocal;
    private static SqlSessionFactory sf;


    static{
        threadLocal = new ThreadLocal<SqlSession>();
        InputStream is = MybatisUtil.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        sf  = new SqlSessionFactoryBuilder().build(is);
    }

    public static SqlSession getSession(){
        SqlSession session = threadLocal.get();
        //如果session不存在
        if (session==null){
            session = sf.openSession();
            threadLocal.set(session);
        }
        return session;
    }

    public static void closeSession(){
        SqlSession session = threadLocal.get();
        if (session!=null){
            session.close();
            threadLocal.remove();
        }
    }
}

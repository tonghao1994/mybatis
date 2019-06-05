package net.wanho.mapper;

import net.wanho.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by tonghao on 2019/6/5.
 */
public interface UserMapper {
    List<User> selectAll();
    User selectUserByName(String username);
    List<User> selectUserByUsername(String username);
    List<User> selectUserByUsernameAndPswd(@Param("username") String username, @Param("password") String password);
    List<User> findUser(User user);
    void returnKey(User user);
    void returnKey2(User user);
    void add(User user);
    void delete(Integer id);
    void update(User user);
}

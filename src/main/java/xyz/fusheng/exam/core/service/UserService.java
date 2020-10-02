package xyz.fusheng.exam.core.service;

import xyz.fusheng.exam.core.entity.Menu;
import xyz.fusheng.exam.core.entity.Role;
import xyz.fusheng.exam.core.entity.User;

import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/9/30 8:40
 */
public interface UserService {

    /**
     * 根据用户名查询用户实体
     * @param username
     * @return
     */
    User selectUserByName(String username);

    /**
     * 根据用户id查询用户角色集合
     * @param userId
     * @return
     */
    List<Role> selectRoleByUserId(Long userId);

    /**
     * 根据用户id查询用户权限集合
     * @param userId
     * @return
     */
    List<Menu> selectMenusByUserId(Long userId);
}

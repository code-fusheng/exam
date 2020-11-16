package xyz.fusheng.exam.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.entity.Menu;
import xyz.fusheng.exam.core.entity.Role;
import xyz.fusheng.exam.core.entity.User;

import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/9/30 8:40
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查询实体
     *
     * @param username 用户名
     * @return User 用户实体
     */
    User selectUserByName(String username);

    /**
     * 根据用户id查询用户角色集合
     *
     * @param userId 用户id
     * @return List<Role> 角色名集合
     */
    List<Role> selectRolesByUserId(Long userId);

    /**
     * 根据用户id查询权限集合
     *
     * @param userId 用户id
     * @return List<Menu> 权限集合
     */
    List<Menu> selectMenusByUserId(Long userId);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    Page<User> getByPage(Page<User> page);

    /**
     * 根据id启用
     *
     * @param id
     */
    void enableById(Long id);

    /**
     * 根据id弃用
     *
     * @param id
     */
    void disableById(Long id);

    /**
     * 用户信息
     *
     * @param userId
     * @return
     */
    User getUserInfoById(Long userId);
}

package xyz.fusheng.exam.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import xyz.fusheng.exam.core.entity.Menu;
import xyz.fusheng.exam.core.entity.Role;
import xyz.fusheng.exam.core.mapper.RoleMapper;
import xyz.fusheng.exam.core.mapper.UserMapper;
import xyz.fusheng.exam.core.entity.User;
import xyz.fusheng.exam.core.service.UserService;

import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/9/30 8:40
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 根据用户名查询用户实体
     * @param username
     * @return
     */
    @Override
    public User selectUserByName(String username) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.lambda().eq(User::getUsername, username);
        return this.userMapper.selectOne(qw);
    }

    /**
     * 根据用户id查询用户角色列表
     * @param userId
     * @return
     */
    @Override
    public List<Role> selectRoleByUserId(Long userId) {
        return this.userMapper.selectRolesByUserId(userId);
    }

    /**
     * 根据用户id查询用户权限集合
     * @param userId
     * @return
     */
    @Override
    public List<Menu> selectMenusByUserId(Long userId) {
        return this.userMapper.selectMenusByUserId(userId);
    }

}

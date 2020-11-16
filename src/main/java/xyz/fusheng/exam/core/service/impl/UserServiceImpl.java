package xyz.fusheng.exam.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import xyz.fusheng.exam.common.enums.StateEnums;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.entity.Menu;
import xyz.fusheng.exam.core.entity.Role;
import xyz.fusheng.exam.core.mapper.UserMapper;
import xyz.fusheng.exam.core.entity.User;
import xyz.fusheng.exam.core.service.UserService;

import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/9/30 8:40
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 根据用户名查询用户实体
     * @param username 用户名
     * @return
     */
    @Override
    public User selectUserByName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, username);
        return this.baseMapper.selectOne(queryWrapper);
    }

    /**
     * 通过用户id查询角色集合
     * @param userId 用户id
     * @return List<Role> 角色名集合
     */
    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        return this.baseMapper.selectRolesByUserId(userId);
    }

    /**
     * 根据用户id查询权限集合
     * @param userId 用户id
     * @return List<Menu> 权限名集合
     */
    @Override
    public List<Menu> selectMenusByUserId(Long userId) {
        return this.baseMapper.selectMenusByUserId(userId);
    }

    /**
     * 多条件分页查询
     * @param page
     * @return
     */
    @Override
    public Page<User> getByPage(Page<User> page) {
        // 查询数据
        List<User> userList = userMapper.getByPage(page);
        page.setList(userList);
        // 查询总数
        int totalCount = userMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public void enableById(Long id) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(User::getUserId, id);
        updateWrapper.lambda().set(User::getIsEnabled, StateEnums.ENABLED.getCode());
        userMapper.update(null, updateWrapper);
    }

    @Override
    public void disableById(Long id) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(User::getUserId, id);
        updateWrapper.lambda().set(User::getIsEnabled, StateEnums.NOT_ENABLE.getCode());
        userMapper.update(null, updateWrapper);
    }

    @Override
    public User getUserInfoById(Long userId) {
        User userInfo = userMapper.getUserInfoById(userId);
        return userInfo;
    }
}

package xyz.fusheng.exam.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import xyz.fusheng.exam.common.enums.StateEnums;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.entity.Menu;
import xyz.fusheng.exam.core.entity.User;
import xyz.fusheng.exam.core.mapper.RoleMapper;
import xyz.fusheng.exam.core.entity.Role;
import xyz.fusheng.exam.core.mapper.UserMapper;
import xyz.fusheng.exam.core.service.RoleService;

import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/9/30 8:44
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public void saveRoleMenu(Long roleId, Long[] menuIds) {
        // 根据角色Id删除 sys_role_menu 全部的权限（重置）
        roleMapper.deleteRoleMenuByRoleId(roleId);
        for (Long menuId : menuIds) {
            roleMapper.saveRoleMenu(roleId, menuId);
        }
    }

    @Override
    public void saveUserRole(Long userId, Long[] roleIds) {
        // 根据用户Id删除 sys_user_role 全部的角色（初始化）
        roleMapper.deleteUserRoleByUserId(userId);
        for (Long roleId : roleIds) {
            roleMapper.saveUserRole(userId, roleId);
        }
    }

    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        return roleMapper.getRoleIdsByUserId(userId);
    }

    @Override
    public List<Role> selectAllRole() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Role::getIsEnabled, StateEnums.ENABLED.getCode());
        return roleMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteById(Long roleId) {
        // 逻辑删除角色
        // Role role = roleMapper.selectById(roleId);
        // role.setIsDeleted(StateEnums.DELETED.getCode());
        // roleMapper.updateById(role);
        roleMapper.deleteById(roleId);
        // 删除中间表数据 sys_user_role
        roleMapper.deleteUserRoleByRoleId(roleId);
        // 删除中间表数据 sys_role_menu
        roleMapper.deleteRoleMenuByRoleId(roleId);
    }

    @Override
    public void enableById(Long roleId) {
        UpdateWrapper<Role> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(Role::getRoleId, roleId);
        updateWrapper.lambda().set(Role::getIsEnabled, StateEnums.ENABLED.getCode());
        roleMapper.update(null, updateWrapper);
    }

    @Override
    public void disableById(Long roleId) {
        UpdateWrapper<Role> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(Role::getRoleId, roleId);
        updateWrapper.lambda().set(Role::getIsEnabled, StateEnums.NOT_ENABLE.getCode());
        roleMapper.update(null, updateWrapper);
        // 删除中间表数据 sys_user_role
        roleMapper.deleteUserRoleByRoleId(roleId);
        // 删除中间表数据 sys_role_menu
        roleMapper.deleteRoleMenuByRoleId(roleId);
    }

}

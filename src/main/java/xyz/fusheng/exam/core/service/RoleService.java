package xyz.fusheng.exam.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.entity.Menu;
import xyz.fusheng.exam.core.entity.Role;
import xyz.fusheng.exam.core.entity.User;

import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/9/30 8:44
  */
public interface RoleService extends IService<Role> {

     /**
      * 保存角色和菜单之间的关系
      *
      * @param roleId
      * @param menuIds
      */
     void saveRoleMenu(Long roleId, Long[] menuIds);

     /**
      * 逻辑扇出角色 - 但是要删除对应用户角色中间表
      *
      * @param roleId
      */
     void deleteById(Long roleId);


     /**
      * 根据id启用
      *
      * @param roleId
      */
     void enableById(Long roleId);

     /**
      * 根据id弃用
      *
      * @param roleId
      */
     void disableById(Long roleId);

     /**
      * 保存用户与角色之间的关系
      *
      * @param userId
      * @param roleIds
      */
     void saveUserRole(Long userId, Long[] roleIds);

     /**
      * 根据用户ID查询用户拥有的角色IDS
      *
      * @param userId
      * @return
      */
     List<Long> getRoleIdsByUserId(Long userId);

     /**
      * 查询所有可用角色
      *
      * @return
      */
     List<Role> selectAllRole();
}

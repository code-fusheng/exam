package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.fusheng.exam.core.entity.Menu;
import xyz.fusheng.exam.core.entity.Role;

import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/9/30 8:44
  */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

 /**
  * 根据角色id删除 sys_role_menu 中间表的数据
  *
  * @param roleId
  */
 void deleteRoleMenuByRoleId(@Param("roleId") Long roleId);

 /**
  * 保存角色和权限的关系
  *
  * @param roleId
  * @param menuId
  */
 void saveRoleMenu(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

 /**
  * 根据角色id删除 sys_user_role 中间表数据
  *
  * @param roleId
  */
 void deleteUserRoleByRoleId(@Param("roleId") Long roleId);

 /**
  * 根据用户id删除 sys_user_role 中间表数据
  *
  * @param userId
  */
 void deleteUserRoleByUserId(Long userId);

 /**
  * 保存用户与角色的关系
  *
  * @param userId
  * @param roleId
  */
 void saveUserRole(Long userId, Long roleId);

 /**
  * 根据用户ID查询用户拥有的角色IDS
  *
  * @param userId
  * @return
  */
 List<Long> getRoleIdsByUserId(Long userId);
}

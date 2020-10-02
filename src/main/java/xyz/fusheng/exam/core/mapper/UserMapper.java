package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.fusheng.exam.core.entity.Menu;
import xyz.fusheng.exam.core.entity.Role;
import xyz.fusheng.exam.core.entity.User;

import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/9/30 8:40
  */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户Id查询用户角色列表
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);

    /**
     * 通过用户id查询用户权限列表
     * @param userId
     * @return
     */
    List<Menu> selectMenusByUserId(Long userId);
}

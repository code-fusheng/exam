package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.entity.Menu;
import xyz.fusheng.exam.core.entity.Role;
import xyz.fusheng.exam.core.entity.User;

import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/9/30 8:40
  */
@Mapper
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

    /**
     * 通过用户id查询角色集合
     * @param userId 用户id
     * @return List<Role> 角色集合
     */
    List<Role> selectRoleByUserId(Long userId);


    /**
     * 通过用户id查询权限集合
     * @param userId 用户id
     * @return List<Menu> 权限集合
     */
    List<Menu> selectMenuByUserId(Long userId);

    /**
     * 多条件分页查询
     * @param page
     * @return
     */
    List<User> getByPage(Page<User> page);

    /**
     * 分页查询统计总数
     *
     * @param page
     * @return
     */
    int getCountByPage(Page<User> page);

    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    User getUserInfoById(Long userId);
}

package xyz.fusheng.exam.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.entity.Menu;

import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/9/30 8:47
  */
public interface MenuService extends IService<Menu> {
     /**
      * 多条件分页查询
      * @param page
      * @return
      */
     Page<Menu> getByPage(Page<Menu> page);

     /**
      * 根据角色id查询权限列表
      *
      * @param id
      * @return
      */
     List<Menu> getMenuListByRoleId(Long id);

     /**
      * 格式化权限
      *
      * @param id
      * @return
      */
     List<Menu> getFormatMenuListByRoleId(Long id);

     /**
      * 获取权限树
      *
      * @return
      */
     List<Menu> getFormatMenuTree();

     /**
      * 查询可用权限列表
      *
      * @return
      */
     List<Menu> getMenuTree();

     /**
      * 根据角色ID查询菜单权限ID数据
      *
      * @param roleId
      * @return
      */
     List<Long> getMenuIdsByRoleId(Long roleId);

     /**
      * 根据id删除权限
      *
      * @param id
      */
     void deleteById(Long id);
}

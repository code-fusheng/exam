package xyz.fusheng.exam.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.exam.common.enums.ResultEnums;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.common.utils.Result;
import xyz.fusheng.exam.common.utils.SecurityUtil;
import xyz.fusheng.exam.common.utils.StringUtils;
import xyz.fusheng.exam.core.dto.DictTypeDto;
import xyz.fusheng.exam.core.entity.DictType;
import xyz.fusheng.exam.core.service.DictTypeService;
import xyz.fusheng.exam.core.vo.DictTypeVo;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: DictTypeController
 * @Author: code-fusheng
 * @Date: 2020/10/9 8:43
 * @version: 1.0
 * Description: 字典分类控制器
 */

@RestController
@RequestMapping("/dict/type")
public class DictTypeController {

    @Resource
    private DictTypeService dictTypeService;

    /**
     * 添加字典类型
     * @param dictTypeDto
     * @return
     */
    @ApiOperation(value = "添加字典类型", notes = "添加字典类型")
    @PostMapping("/save")
    public Result<Object> save(@RequestBody DictTypeDto dictTypeDto) {
        // 先检查字典是否已经存在
        if (dictTypeService.checkDictTypeUnique(dictTypeDto.getDictId(), dictTypeDto.getDictType())) {
            return new Result<>(ResultEnums.ERROR.getCode(), "操作提示: 添加失败! 字典类型【" + dictTypeDto.getDictName() + "】已经存在");
        }
        dictTypeDto.setOperationUserId(SecurityUtil.getUserId());
        dictTypeService.save(dictTypeDto);
        return new Result<>("操作提示：添加成功!");
    }

    /**
     * 删除字典类型
     * @param dictIds
     * @return
     */
    @DeleteMapping("/deleteByIds")
    public Result<Object> deleteByIds(@RequestBody Long[] dictIds) {
        dictTypeService.deleteByIds(dictIds);
        return new Result<>("操作提示: 删除成功!");
    }

    /**
     * 修改字典类型
     * @param dictTypeDto
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@RequestBody DictTypeDto dictTypeDto) {
        dictTypeDto.setVersion(dictTypeService.getById(dictTypeDto.getDictId()).getVersion());
        dictTypeDto.setOperationUserId(SecurityUtil.getUserId());
        dictTypeService.update(dictTypeDto);
        return new Result<>("操作提示: 修改成功!");
    }

    /**
     * 根据id查询字典类型详情
     * @param dictId
     * @return
     */
    @GetMapping("/getById/{dictId}")
    public Result<DictTypeVo> getById(@PathVariable Long dictId) {
        DictTypeVo dictTypeVo = dictTypeService.getById(dictId);
        return new Result<>("操作提示: 查询成功!", dictTypeVo);
    }

    /**
     * 查询所有可用字典类型列表
     * @return
     */
    @GetMapping("/getList")
    public Result<List<DictType>> getList() {
        List<DictType> dictTypeList = dictTypeService.getList();
        return new Result<>("操作提示: 查询成功!", dictTypeList);
    }

    /**
     * 分页查询字典类型
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<DictTypeVo>> getByPage(@RequestBody Page<DictTypeVo> page) {
        String sortColumn = page.getSortColumn();
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(sortColumn)) {
            // 字典名称、字典类型、创建时间、更新时间
            String[] sortColumns = {"dict_name", "dict_type", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = dictTypeService.getByPage(page);
        return new Result<>("操作提示: 分页查询成功!", page);
    }

}

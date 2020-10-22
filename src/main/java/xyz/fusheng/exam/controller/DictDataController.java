package xyz.fusheng.exam.controller; /**
 * @author: code-fusheng
 * @Date: 2020/10/9 13:20
 */

import org.springframework.web.bind.annotation.*;
import xyz.fusheng.exam.common.enums.ResultEnums;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.common.utils.Result;
import xyz.fusheng.exam.common.utils.SecurityUtil;
import xyz.fusheng.exam.common.utils.StringUtils;
import xyz.fusheng.exam.core.dto.DictDataDto;
import xyz.fusheng.exam.core.entity.DictData;
import xyz.fusheng.exam.core.service.DictDataService;
import xyz.fusheng.exam.core.vo.DictDataVo;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: DictDataController
 * @Author: code-fusheng
 * @Date: 2020/10/9 13:20
 * @version: 1.0
 * Description:
 */

@RestController
@RequestMapping("/dict/data")
public class DictDataController {

    @Resource
    private DictDataService dictDataService;

    /**
     * 添加字典数据
     * @param dictDataDto
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@RequestBody DictDataDto dictDataDto) {
        dictDataDto.setOperationUserId(SecurityUtil.getUserId());
        dictDataService.save(dictDataDto);
        return new Result<>("操作提示：添加成功!");
    }

    /**
     * 删除字典数据
     * @param dictIds
     * @return
     */
    @DeleteMapping("/deleteByIds")
    public Result<Object> deleteByIds(@RequestBody Long[] dictIds) {
        dictDataService.deleteByIds(dictIds);
        return new Result<>("操作提示: 删除成功!");
    }

    /**
     * 更新字典数据
     * @param dictDataDto
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@RequestBody DictDataDto dictDataDto) {

        return new Result<>("操作提示: 更新成功!");
    }

    /**
     * 根据id查询字典数据详情
     * @param dictId
     * @return
     */
    @GetMapping("/getById/{dictId}")
    public Result<DictDataVo> getById(@PathVariable Long dictId) {
        DictDataVo dictDataVo = dictDataService.getById(dictId);
        return new Result<>("操作提示: 查询成功!", dictDataVo);
    }

    /**
     * 根据字典类型（dictType）查询所有可用字典数据列表
     * @param dictType
     * @return
     */
    @GetMapping("/getList/{dictType}")
    public Result<List<DictData>> getList(@PathVariable String dictType) {
        List<DictData> dictDataList = dictDataService.getList(dictType);
        return new Result<>("操作提示: 查询成功!", dictDataList);
    }

    /**
     * 自定义分页查询数据
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<DictDataVo>> getByPage(@RequestBody Page<DictDataVo> page) {
        String sortColumn = page.getSortColumn();
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(sortColumn)) {
            // 字典排序、字典标签、创建时间、更新时间
            String[] sortColumns = {"dict_sort", "dict_label", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = dictDataService.getByPage(page);
        return new Result<>("操作提示: 分页查询数据", page);
    }

}

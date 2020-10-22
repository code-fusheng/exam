package xyz.fusheng.exam.core.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.exam.core.mapper.ExamPaperMapper;
import xyz.fusheng.exam.core.service.ExamPaperService;
 /**
  * @author:   code-fusheng
  * @Date:     2020/10/20 14:19
  */
@Service
public class ExamPaperServiceImpl implements ExamPaperService{

    @Resource
    private ExamPaperMapper examPaperMapper;

}

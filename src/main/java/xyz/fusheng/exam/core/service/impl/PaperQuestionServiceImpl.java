package xyz.fusheng.exam.core.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.exam.core.mapper.PaperQuestionMapper;
import xyz.fusheng.exam.core.service.PaperQuestionService;
 /**
  * @author:   code-fusheng
  * @Date:     2020/10/19 11:15
  */
@Service
public class PaperQuestionServiceImpl implements PaperQuestionService{

    @Resource
    private PaperQuestionMapper paperQuestionMapper;

}

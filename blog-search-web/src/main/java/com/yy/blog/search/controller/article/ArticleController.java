package com.yy.blog.search.controller.article;

import com.yy.domain.Artical.Article;
import com.yy.rpc.domain.Result;
import com.yy.service.article.ArticleService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhengjm5
 * @date 2018/6/23 19:56
 */
@RestController
public class ArticleController {

    @Resource(name="articleService")
    private ArticleService articleService;

    /**
     * 日志
     */
    private final static Logger logger = Logger.getLogger(ArticleController.class);

    @RequestMapping("article/insertArticle")
    public Result insertArticle(Article article) {
        Result result = new Result(false);
        try {
            articleService.insertArticle(article);
            result.setSuccess(true);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorMessage(e.getMessage());
        }
        return result;
    }
}
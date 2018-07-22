package com.yy.blog.search.controller.article;

import com.yy.domain.Artical.Article;
import com.yy.domain.Artical.query.ArticleQuery;
import com.yy.rpc.domain.Result;
import com.yy.service.article.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhengjm5
 * @date 2018/6/23 19:56
 */
@RestController
@Api("ArticleController相关的API")
public class ArticleController {

    @Resource(name="articleService")
    private ArticleService articleService;

    /**
     * 日志
     */
    private final static Logger logger = Logger.getLogger(ArticleController.class);

    @ApiOperation(value="新增文章")
    @RequestMapping(value = "article/insertArticle", method = {RequestMethod.POST, RequestMethod.GET })
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

    @ApiOperation(value="根据条件查询文章")
    @RequestMapping(value = "article/getArticleByCond", method = {RequestMethod.POST, RequestMethod.GET })
    public Result getArticleByCond(ArticleQuery articleQuery) {
        Result result = new Result(false);
        try {
            List<Article> articleList = articleService.getArticleByCond(articleQuery);
            result.setSuccess(true);
            result.addDefaultModel("articleList", articleList);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorMessage(e.getMessage());
        }
        return result;
    }
}

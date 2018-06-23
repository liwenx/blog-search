package com.yy.service.article.impl;

import com.yy.dao.blog.article.ArticleDao;
import com.yy.domain.Artical.Article;
import com.yy.service.article.ArticleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 文章服务实现类
 * @author zhengjm5
 * @date 2018/6/23 19:52
 */
@Component("articleService")
public class ArticleServiceImpl implements ArticleService{

    @Resource(name="articleDao")
    private ArticleDao articleDao;

    @Override
    public void insertArticle(Article article) {
        articleDao.insertArticle(article);
    }
}

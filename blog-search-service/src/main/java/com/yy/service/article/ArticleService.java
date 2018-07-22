package com.yy.service.article;

import com.yy.domain.article.Article;
import com.yy.domain.article.query.ArticleQuery;

import java.util.List;

/**
 * 文章服务
 * @author zhengjm5
 * @date 2018/6/23 19:51
 */
public interface ArticleService {

    /**
     * 新增
     * @author: zhengjm5
     * @Date: 2018-07-01 18:41:49
     * @param article
     */
    public void insertArticle(Article article);

    /**
     * 根据条件查询
     * @author: zhengjm5
     * @Date: 2018-07-01 18:42:54
     * @param articleQuery
     * @return
     */
    public List<Article> getArticleByCond(ArticleQuery articleQuery);
}

package com.yy.dao.blog.article;


import com.yy.domain.Artical.Article;
import com.yy.domain.Artical.query.ArticleQuery;

import java.util.List;

/**
 * 文章DAO
 * @author: zhengjm5
 * @Date: 2018-06-23 19:34:02
 */
public interface ArticleDao {

	/**
	 * 添加
	 * @author: zhengjm5
	 * @Date: 2018-06-23 19:27:00
	 * @param article
	 */
	public void insertArticle(Article article);

	/**
	 * 根据条件查询
	 * @author: zhengjm5
	 * @Date: 2018-07-01 18:29:07
	 * @param articleQuery
	 * @return
	 */
	public List<Article> getArticleByCond(ArticleQuery articleQuery);

}
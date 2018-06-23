package com.yy.dao.blog.article;


import com.yy.domain.Artical.Article;

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

}
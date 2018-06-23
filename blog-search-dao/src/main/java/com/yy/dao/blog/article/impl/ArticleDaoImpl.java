package com.yy.dao.blog.article.impl;

import com.yy.dao.blog.article.ArticleDao;
import com.yy.domain.Artical.Article;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("articleDao")
public class ArticleDaoImpl implements ArticleDao{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insertArticle(Article article) {
		sqlSessionTemplate.insert("article.insertArticle", article);
	}
}
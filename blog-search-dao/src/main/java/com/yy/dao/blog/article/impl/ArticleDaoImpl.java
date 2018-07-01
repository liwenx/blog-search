package com.yy.dao.blog.article.impl;

import com.yy.dao.blog.article.ArticleDao;
import com.yy.domain.Artical.Article;
import com.yy.domain.Artical.query.ArticleQuery;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("articleDao")
public class ArticleDaoImpl implements ArticleDao{

	@Autowired
	@Qualifier("articleSqlSessionTemplate")
	private SqlSessionTemplate articleSqlSessionTemplate;

	@Override
	public void insertArticle(Article article) {
		articleSqlSessionTemplate.insert("article.insertArticle", article);
	}

	@Override
	public List<Article> getArticleByCond(ArticleQuery articleQuery) {
		return articleSqlSessionTemplate.selectList("article.getArticleByCond", articleQuery);
	}
}
package com.yy.domain.articleuser;

import java.io.Serializable;

/**
 * @author zhengjm5
 * @date 2018/7/22 19:09
 */
public class ArticleUser implements Serializable{

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名称
     */
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}


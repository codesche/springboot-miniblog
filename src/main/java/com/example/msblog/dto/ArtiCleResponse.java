package com.example.msblog.dto;

import com.example.msblog.domain.Article;
import lombok.Getter;

@Getter
public class ArtiCleResponse {

    private final String title;
    private final String content;

    // 엔티티를 인수로 받는 생성자 추가
    public ArtiCleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}

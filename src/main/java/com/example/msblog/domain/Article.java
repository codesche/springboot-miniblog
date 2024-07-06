package com.example.msblog.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity         // 엔티티로 지정
@Getter
public class Article {

    @Id                                                                     // Id 필드를 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)                     // 기본키를 자동으로 1씩 증가시키기
    private Long id;

    @Column(name = "title", nullable = false)                               // 'title' 이라는 not null 컬럼과 매핑
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder    // 빌더 패턴으로 작성
    public Article(Long id, String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 기본 생성자
    protected Article() {
    }

}

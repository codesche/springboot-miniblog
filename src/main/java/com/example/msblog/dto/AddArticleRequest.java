package com.example.msblog.dto;

import com.example.msblog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor                          // 기본 생성자 추가
@AllArgsConstructor                         // 모든 필드 값을 파라미터로 받는 생성자 추가
@Getter                                     // Getter 안해주면 500 에러 발생하므로 주의!
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toEntity() {             // 생성자를 사용하여 객체를 생성
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}

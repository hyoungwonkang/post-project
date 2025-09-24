package com.example.post_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Criteria {
    
    private String keyfield; // 검색 조건 : writer, title, contents

    private String keyword; // 검색어

}

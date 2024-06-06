package com.ssafy.funding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GenreSearchCondition {

    private int parentGenreId;
    private String order;
    //    private int page;

}

package com.potato112.springdemo.web.service.search.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class OffsetResponseDto<T> {

    private List<T> content;
    private OffsetInfoDto offsetInfo;
}

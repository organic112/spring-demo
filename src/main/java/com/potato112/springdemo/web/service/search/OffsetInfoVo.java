package com.potato112.springdemo.web.service.search;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class OffsetInfoVo {

    private long offset;
    private long limit;
    private long total;
}



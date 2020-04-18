package com.potato112.springdemo.web.ui;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class OffsetResponseVo<T> {

    private List<T> content;
    private OffsetInfoVo offsetInfo;
}

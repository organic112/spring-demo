package com.potato112.springdemo.web.ui.common;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortingKey {

    USER_SORTING("userSorting");

    private final String sortingKey;
}
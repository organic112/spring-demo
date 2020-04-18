package com.potato112.springdemo.web.ui;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortingKey {

    USER_SORTING("userSorting");

    private final String sortingKey;
}

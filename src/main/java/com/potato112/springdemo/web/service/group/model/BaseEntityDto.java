package com.potato112.springdemo.web.service.group.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class BaseEntityDto implements Serializable {

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String createUser;

    private String updateUser;

}

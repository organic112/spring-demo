package com.potato112.springdemo.web.service.group.model;

import com.potato112.springdemo.web.service.search.model.OffsetSearchDto;

import java.util.Collection;
import java.util.Optional;


public interface GroupService {

    Optional<GroupDto> getGroup(String id);

    Collection<GroupOverviewResponseDto> getGroups(OffsetSearchDto searchDto);

    int count(OffsetSearchDto searchDto);

    String create(GroupDto userVo);

    GroupDto update(GroupDto groupDto);

    // UserFormParametersVo getUserFromParameters();
    // TODO other
}

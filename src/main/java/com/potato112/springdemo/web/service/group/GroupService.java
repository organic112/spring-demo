package com.potato112.springdemo.web.service.group;

import com.potato112.springdemo.web.service.OffsetSearchDto;
import com.potato112.springdemo.web.ui.group.GroupDto;

import java.util.Collection;
import java.util.Optional;


public interface GroupService {

    String create(GroupDto userVo);

    Collection<GroupOverviewResponseDto> getGroups(OffsetSearchDto searchDto);

    int count(OffsetSearchDto searchDto);

    Optional<GroupDto> getGroup(String id);

    GroupDto update(GroupDto groupDto);

    //UserFormParametersVo getUserFromParameters();
    // TODO other
}

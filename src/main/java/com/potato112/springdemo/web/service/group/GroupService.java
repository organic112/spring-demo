package com.potato112.springdemo.web.service.group;

import com.potato112.springdemo.web.service.OffsetSearchDto;
import com.potato112.springdemo.web.ui.group.GroupDto;

import java.util.Collection;



public interface GroupService {

    String create(GroupDto userVo);

    Collection<GroupOverviewResponseDto> getGroups(OffsetSearchDto searchDto);

    int count(OffsetSearchDto searchDto);

    //UserFormParametersVo getUserFromParameters();
    // TODO other
}

package com.potato112.springdemo.web.service.group;

import com.potato112.springdemo.web.service.group.model.GroupOverviewResponseDto;
import com.potato112.springdemo.web.service.group.model.GroupService;
import com.potato112.springdemo.web.service.rest.JSONGroupClient;
import com.potato112.springdemo.web.service.search.model.OffsetResponseDto;
import com.potato112.springdemo.web.service.search.model.OffsetSearchDto;
import com.potato112.springdemo.web.service.group.model.GroupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class GroupServiceRestImpl implements GroupService {

    private final JSONGroupClient jsonGroupClient;

    public GroupServiceRestImpl(JSONGroupClient jsonGroupClient) {
        this.jsonGroupClient = jsonGroupClient;
    }

    @Override
    public Optional<GroupDto> getGroup(String id) {
        return Optional.of(this.jsonGroupClient.getGroupById(id));
    }

    @Override
    public Collection<GroupOverviewResponseDto> getGroups(OffsetSearchDto searchDto) {
        OffsetResponseDto<GroupOverviewResponseDto> response = getForSearch(searchDto);
        response.getContent().forEach(content -> {
            log.info("LIST OF PERMISSIONS:");
            content.getGroupPermissions().forEach(per -> log.info("" + per.getViewName().getEnumValue()));
        });
        return response.getContent();
    }

    @Override
    public int count(OffsetSearchDto searchDto) {
        OffsetResponseDto<GroupOverviewResponseDto> response = getForSearch(searchDto);
        return (int) response.getOffsetInfo().getTotal();
    }

    @Override
    public String create(GroupDto groupDto) {
        return this.jsonGroupClient.create(groupDto);
    }

    @Override
    public GroupDto update(GroupDto groupDto) {
        System.out.println("try update group" + groupDto.getId());
        System.out.println("try update group create user:" + groupDto.getCreateUser());
        return this.jsonGroupClient.update(groupDto);
    }

    private OffsetResponseDto<GroupOverviewResponseDto> getForSearch(OffsetSearchDto searchDto) {
        return this.jsonGroupClient.getGroups(searchDto.toParamMap());
    }
}

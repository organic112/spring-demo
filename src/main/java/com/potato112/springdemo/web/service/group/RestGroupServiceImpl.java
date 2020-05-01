package com.potato112.springdemo.web.service.group;

import com.google.gson.internal.$Gson$Preconditions;
import com.potato112.springdemo.web.service.OffsetResponseDto;
import com.potato112.springdemo.web.service.OffsetSearchDto;
import com.potato112.springdemo.web.ui.group.GroupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class RestGroupServiceImpl implements GroupService {

    private final JSONGroupClient jsonGroupClient;

    public RestGroupServiceImpl(JSONGroupClient jsonGroupClient) {
        this.jsonGroupClient = jsonGroupClient;
    }

    @Override
    public String create(GroupDto userVo) {
        return this.jsonGroupClient.create(userVo);
    }

    @Override
    public Collection<GroupOverviewResponseDto> getGroups(OffsetSearchDto searchDto) {
        OffsetResponseDto<GroupOverviewResponseDto> response = getForSearch(searchDto);

        response.getContent().forEach(content -> {
            log.info("LIST OF PERMISSIONS:");
            content.getGroupPermissions().forEach(per -> log.info(""+per.getViewName().getEnumValue()));
        });

        return response.getContent();
    }

    private OffsetResponseDto<GroupOverviewResponseDto> getForSearch(OffsetSearchDto searchDto) {
        return  this.jsonGroupClient.getGroups(searchDto.toParamMap());
    }

    @Override
    public int count(OffsetSearchDto searchDto) {
        OffsetResponseDto<GroupOverviewResponseDto> response = getForSearch(searchDto);
        return (int) response.getOffsetInfo().getTotal();
    }
}

package com.potato112.springdemo.web.service.group;

import com.potato112.springdemo.conf.ClientConfiguration;
import com.potato112.springdemo.web.service.OffsetResponseDto;
import com.potato112.springdemo.web.ui.group.GroupDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "jgroupsclient", url = "${app.service.api.url}", configuration = ClientConfiguration.class)
public interface JSONGroupClient {

    @GetMapping(value = "/api/v1/group/name/{groupname}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserGroupVO getGroupByName(@PathVariable("groupname") String groupName);

    @PostMapping(value = "/api/v1/group", produces = MediaType.APPLICATION_JSON_VALUE)
    String create(@RequestBody GroupDto groupDto);

    @GetMapping(value = "/api/v1/group", produces = MediaType.APPLICATION_JSON_VALUE)
    OffsetResponseDto<GroupOverviewResponseDto> getGroups(@SpringQueryMap Map<String, String> params);

    @GetMapping(value = "/api/v1/group/{groupId}", produces = MediaType.APPLICATION_JSON_VALUE)
    GroupDto getGroupById(@PathVariable("groupId") String id);

    @PutMapping(value = "/api/v1/group", produces =  MediaType.APPLICATION_JSON_VALUE)
    GroupDto update(@RequestBody GroupDto groupDto);

}

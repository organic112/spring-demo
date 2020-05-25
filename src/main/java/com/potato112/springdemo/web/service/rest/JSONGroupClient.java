package com.potato112.springdemo.web.service.rest;

import com.potato112.springdemo.conf.ClientConfiguration;
import com.potato112.springdemo.web.service.search.model.OffsetResponseDto;
//import com.potato112.springdemo.web.service.group.model.UserGroupDto;
import com.potato112.springdemo.web.service.group.model.GroupOverviewResponseDto;
import com.potato112.springdemo.web.service.group.model.GroupDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "jgroupsclient", url = "${app.service.api.url}", configuration = ClientConfiguration.class)
public interface JSONGroupClient {

    @GetMapping(value = "/api/v1/group/{groupId}", produces = MediaType.APPLICATION_JSON_VALUE)
    GroupDto getGroupById(@PathVariable("groupId") String id);

    @GetMapping(value = "/api/v1/group/name/{groupname}", produces = MediaType.APPLICATION_JSON_VALUE)
    GroupDto getGroupByName(@PathVariable("groupname") String groupName);

    @GetMapping(value = "/api/v1/group", produces = MediaType.APPLICATION_JSON_VALUE)
    OffsetResponseDto<GroupOverviewResponseDto> getGroups(@SpringQueryMap Map<String, String> params);

    @PostMapping(value = "/api/v1/group", produces = MediaType.APPLICATION_JSON_VALUE)
    String create(@RequestBody GroupDto groupDto);

    @PutMapping(value = "/api/v1/group", produces =  MediaType.APPLICATION_JSON_VALUE)
    GroupDto update(@RequestBody GroupDto groupDto);
}

package com.potato112.springdemo.web.service.group;

import com.potato112.springdemo.conf.ClientConfiguration;
import com.potato112.springdemo.web.service.OffsetResponseDto;
import com.potato112.springdemo.web.ui.group.GroupDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "jgroupsclient", url = "${app.service.api.url}", configuration = ClientConfiguration.class)
public interface JSONGroupClient {

    @GetMapping(value = "/api/v1/group/{groupname}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserGroupVO getGroupByName(@PathVariable("groupname") String groupName);

    @PostMapping(value = "/api/v1/group", produces = MediaType.APPLICATION_JSON_VALUE)
    String create(@RequestBody GroupDto groupDto);

    @GetMapping(value = "/api/v1/group", produces = MediaType.APPLICATION_JSON_VALUE)
    OffsetResponseDto<GroupOverviewResponseDto> getUsers(@SpringQueryMap Map<String, String> params);

/*    @GetMapping(value = "/api/v1/group/create-parameters", produces = MediaType.APPLICATION_JSON_VALUE)
    UserFormParametersVo getGroupParameters();*/
}

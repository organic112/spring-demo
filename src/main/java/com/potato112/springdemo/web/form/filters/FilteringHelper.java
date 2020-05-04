package com.potato112.springdemo.web.form.filters;

import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@UIScope
@Component
public class FilteringHelper implements Serializable {

    private Map<FilterKey, Map<String, String>> filters = new EnumMap<>(FilterKey.class);

    public Map<String, String> loadFilters(FilterKey filterKey) {

        Map<String, String> fromSession = filters.get(filterKey);
        if (null != fromSession) {
            return fromSession;
        }
        return new HashMap<>();
    }

    public void setFiltersInSession(FilterKey filterKey, Map<String, String> filterParams) {

        filters.put(filterKey, filterParams);
    }

    public void applyFiltersFromUrlParameters(FilterKey filterKey, QueryParameters queryParameters) {

        log.info("Echo01 try apply filters from url params");

        if (null != queryParameters && !queryParameters.getParameters().isEmpty()) {

            log.info("Echo02 Apply filters from url params");

            Map<String, List<String>> parametersMap = queryParameters.getParameters();
            Map<String, String> flattenedParameters = new HashMap<>();
            parametersMap.forEach((key, values) -> flattenedParameters.put(key, values.get(0)));
            this.setFiltersInSession(filterKey, flattenedParameters);
        }
    }
}

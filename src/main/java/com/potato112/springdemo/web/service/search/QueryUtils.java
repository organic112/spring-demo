package com.potato112.springdemo.web.service.search;

import com.potato112.springdemo.web.service.search.model.OffsetSearchDto;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;

import java.util.List;
import java.util.Map;

/**
 * Provides
 */
public class QueryUtils {

    private QueryUtils() {
    }

    public static OffsetSearchDto buildSearchDto(Query<?, ?> query, Map<String, String> filteringParams) {

        return buildSearchDto(query.getOffset(), query.getLimit(), query.getSortOrders(), filteringParams);
    }

    public static OffsetSearchDto buildSearchDtoForCountQuery(Query<?, ?> query, Map<String, String> filteringParams) {

        return buildSearchDto(0, 1, query.getSortOrders(), filteringParams);
    }

    private static OffsetSearchDto buildSearchDto(int offset, int limit, List<QuerySortOrder> sortOrders, Map<String, String> filteringParams) {

        OffsetSearchDto.SearchVoBuilder builder = OffsetSearchDto.builder();
        builder.part(offset, limit);

        for (Map.Entry<String, String> filter : filteringParams.entrySet()) {
            builder.byParam(filter.getKey(), filter.getValue());
        }

        if (!sortOrders.isEmpty()) {
            QuerySortOrder sortOrder = sortOrders.get(0);
            builder.sorted(sortOrder.getSorted(), sortOrder.getDirection().toString());
        }
        return builder.build();
    }
}

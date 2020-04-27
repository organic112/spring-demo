package com.potato112.springdemo.web.service.search;

import com.potato112.springdemo.web.service.OffsetSearchVo;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;

import java.util.List;
import java.util.Map;

public class QueryUtils {


    private QueryUtils() {
    }

    public static OffsetSearchVo buildSearchVo(Query<?, ?> query, Map<String, String> filteringParams) {

        return buildSearchVo(query.getOffset(), query.getLimit(), query.getSortOrders(), filteringParams);
    }

    public static OffsetSearchVo buildSearchVoForCountQuery(Query<?, ?> query, Map<String, String> filteringParams) {

        return buildSearchVo(0, 1, query.getSortOrders(), filteringParams);
    }

    private static OffsetSearchVo buildSearchVo(int offset, int limit, List<QuerySortOrder> sortOrders, Map<String, String> filteringParams) {

        OffsetSearchVo.SearchVoBuilder builder = OffsetSearchVo.builder();
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

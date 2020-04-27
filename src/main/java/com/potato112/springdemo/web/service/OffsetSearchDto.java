package com.potato112.springdemo.web.service;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class OffsetSearchDto {

    public static final String OFFSET = "offset";
    public static final String LIMIT = "limit";
    public static final String SORT_FIELD = "sortField";
    public static final String SORT_ORDER = "sortOrder";

    private Map<String, String> filterParams = new HashMap<>();
    private int offsetParam;
    private int limitParam;
    private String sortField;
    private String sortOrder;

    private OffsetSearchDto() {
    }

    public Map<String, String> toParamMap() {

        Map<String, String> paramMap = new HashMap<>(filterParams);
        paramMap.put(OFFSET, Integer.toString(offsetParam));
        paramMap.put(LIMIT, Integer.toString(limitParam));
        paramMap.put(SORT_FIELD, sortField);
        paramMap.put(SORT_ORDER, sortOrder);
        return paramMap;
    }

    public static SearchVoBuilder builder() {
        return new SearchVoBuilder();
    }

    public static class SearchVoBuilder {

        private Map<String, String> filterParams = new HashMap<>();
        private int offset;
        private int limit;
        private String sortOrder;
        private String sortField;

        public SearchVoBuilder byParam(String key, String value) {
            this.filterParams.put(key, value);
            return this;
        }

        public SearchVoBuilder part(int offset, int limit) {
            this.offset = offset;
            this.limit = limit;
            return this;
        }

        public SearchVoBuilder sorted(String sortField, String sortOrder) {

            this.sortField = sortField;
            this.sortOrder = sortOrder;
            return this;
        }

        public OffsetSearchDto build() {

            OffsetSearchDto ret = new OffsetSearchDto();
            ret.filterParams = this.filterParams;
            ret.offsetParam = this.offset;
            ret.limitParam = this.limit;
            ret.sortField = this.sortField;
            ret.sortOrder = this.sortOrder;
            return ret;
        }




    }
}

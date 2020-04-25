package com.potato112.springdemo.jms.crud;


import java.util.List;
import java.util.Map;

// returns POJO results of native query execution

public interface NativeReadService<RETURN_TYPE> {


    long countWithNativeQuery(String query, Map<String, Object> params);

    List<RETURN_TYPE> findPOJOWithNativeQuery(String query, Class<RETURN_TYPE> returnType,
                                              Map<String, Object> params);

    List<RETURN_TYPE> findPOJOWithNativeQuery(String query, Class<RETURN_TYPE> returnType,
                                              Map<String, Object> params, Integer startIndex, Integer resultLimit);

}

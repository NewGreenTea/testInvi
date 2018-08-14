package com.confClass;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSourceChange extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        //return DynamicDataSourceContextholder.get();
        return DynamicDataSourceContextholder.get();
    }
}

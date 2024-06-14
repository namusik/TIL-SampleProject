package com.example.multipledatasource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class MultiRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        // 트랜잭션이 읽기 전용인지 확인
        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            return "readOnlyDataSource";
        } else {
            return "writeDataSource";
        }
    }
}

package com.example.datasourcerouting.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // 현재 트랜잭션인 readOnly 인지 확인
        String key = TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "read" : "write";
        log.info("key :: {}", key);
        return key;
    }
}

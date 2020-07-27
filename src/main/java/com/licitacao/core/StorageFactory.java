package com.licitacao.core;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class StorageFactory {

    private Map<StorageType, UploadStorageInterface> strategies;

    public StorageFactory(Set<UploadStorageInterface> strategySet) {
        this.createStrategy(strategySet);
    }

    public UploadStorageInterface findStrategy(StorageType strategyName) {
        return strategies.get(strategyName);
    }

    private void createStrategy(Set<UploadStorageInterface> storageInterfaceSet){
        this.strategies = new HashMap<>();
        storageInterfaceSet.forEach(strategy -> this.strategies.put(strategy.getStrategyName(), strategy));
    }


}

package com.basementinteractive.civilwar.resource.service.impl;

import com.basementinteractive.civilwar.playerbase.model.PlayerBase;
import com.basementinteractive.civilwar.resource.model.ResourceStock;
import com.basementinteractive.civilwar.resource.model.ResourceType;
import com.basementinteractive.civilwar.resource.repository.ResourceStockRepository;
import com.basementinteractive.civilwar.resource.service.ResourceStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ResourceStockServiceImpl implements ResourceStockService {

    private final ResourceStockRepository resourceStockRepository;

    @Override
    public void initResourcesStockForBase(PlayerBase playerBase) {
        if (playerBase == null) {
            throw new IllegalArgumentException("Player base cannot be null");
        }

        Arrays.stream(ResourceType.values())
                .map(resourceType -> ResourceStock.builder()
                        .resourceType(resourceType)
                        .playerBase(playerBase)
                        .build())
                .forEach(resourceStockRepository::save);
    }

}

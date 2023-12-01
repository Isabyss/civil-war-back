package com.basementinteractive.civilwar.resource.service.impl;

import com.basementinteractive.civilwar.playerbase.model.PlayerBase;
import com.basementinteractive.civilwar.resource.model.ResourceProduction;
import com.basementinteractive.civilwar.resource.model.ResourceProductionSettings;
import com.basementinteractive.civilwar.resource.repository.ResourceProductionRepository;
import com.basementinteractive.civilwar.resource.service.ResourceProductionService;
import com.basementinteractive.civilwar.resource.service.ResourceProductionSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

import static com.basementinteractive.civilwar.common.constants.EntityConstants.STARTING_BONUS;

@Service
@RequiredArgsConstructor
public class ResourceProductionServiceImpl implements ResourceProductionService {

    private final ResourceProductionRepository resourceProductionRepository;
    private final ResourceProductionSettingsService resourceProductionSettingsService;

    @Override
    public void initResourcesProductionForBase(PlayerBase playerBase) {
        if (playerBase == null) {
            throw new IllegalArgumentException("Player base cannot be null");
        }

        Set<ResourceProductionSettings> resourceProductionSettingsSet =
                resourceProductionSettingsService.getDistinctResourceProductionSettings();

        resourceProductionSettingsSet.stream()
                .map(settings -> ResourceProduction.builder()
                        .resourceType(settings.getResourceType())
                        .zoneBonus(STARTING_BONUS)
                        .playerBase(playerBase)
                        .resourceProductionSettings(settings)
                        .build())
                .forEach(resourceProductionRepository::save);
    }

    @Override
    public BigDecimal calculateProductionPerSec(ResourceProduction resourceProduction) {
        return null;
    }

}

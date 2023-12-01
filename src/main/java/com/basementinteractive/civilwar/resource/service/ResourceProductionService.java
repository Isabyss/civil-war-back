package com.basementinteractive.civilwar.resource.service;

import com.basementinteractive.civilwar.playerbase.model.PlayerBase;
import com.basementinteractive.civilwar.resource.model.ResourceProduction;

import java.math.BigDecimal;

public interface ResourceProductionService {

    void initResourcesProductionForBase(PlayerBase playerBase);

    BigDecimal calculateProductionPerSec(ResourceProduction resourceProduction);

}

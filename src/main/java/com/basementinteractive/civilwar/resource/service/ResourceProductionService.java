package com.basementinteractive.civilwar.resource.service;

import com.basementinteractive.civilwar.resource.model.ResourceProduction;

import java.math.BigDecimal;

public interface ResourceProductionService {

    BigDecimal calculateProductionPerHour(ResourceProduction resourceProduction);

}

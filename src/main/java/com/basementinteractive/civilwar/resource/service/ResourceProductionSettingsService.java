package com.basementinteractive.civilwar.resource.service;

import com.basementinteractive.civilwar.common.model.dto.response.EntityCreatedResponse;
import com.basementinteractive.civilwar.resource.model.ResourceProductionSettings;
import com.basementinteractive.civilwar.resource.model.dto.request.ResourceProductionSettingsRequest;
import com.basementinteractive.civilwar.resource.model.dto.response.ResourceProductionSettingsResponse;

import java.util.List;
import java.util.Set;

public interface ResourceProductionSettingsService {

    EntityCreatedResponse create(ResourceProductionSettingsRequest resourceProductionSettingsRequest);

    ResourceProductionSettingsResponse update(Long id, ResourceProductionSettingsRequest resourceProductionSettingsRequest);

    void delete(Long id);

    ResourceProductionSettingsResponse get(Long id);

    List<ResourceProductionSettingsResponse> getAll();

    Set<ResourceProductionSettings> getDistinctResourceProductionSettings();

}

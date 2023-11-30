package com.basementinteractive.civilwar.resource.service;

import com.basementinteractive.civilwar.common.model.response.EntityCreatedResponse;
import com.basementinteractive.civilwar.resource.model.dto.request.ResourceProductionSettingsRequest;
import com.basementinteractive.civilwar.resource.model.dto.response.ResourceProductionSettingsResponse;

import java.util.List;

public interface ResourceProductionSettingsService {

    EntityCreatedResponse create(ResourceProductionSettingsRequest resourceProductionSettingsRequest);

    ResourceProductionSettingsResponse update(Long id, ResourceProductionSettingsRequest resourceProductionSettingsRequest);

    void delete(Long id);

    ResourceProductionSettingsResponse get(Long id);

    List<ResourceProductionSettingsResponse> getAll();

}

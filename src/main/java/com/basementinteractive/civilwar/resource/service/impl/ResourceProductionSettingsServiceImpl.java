package com.basementinteractive.civilwar.resource.service.impl;

import com.basementinteractive.civilwar.common.exception.EntityNotFoundException;
import com.basementinteractive.civilwar.common.mappers.EntityToResponseMapper;
import com.basementinteractive.civilwar.common.mappers.RequestToEntityMapper;
import com.basementinteractive.civilwar.common.model.dto.response.EntityCreatedResponse;
import com.basementinteractive.civilwar.resource.model.ResourceProductionSettings;
import com.basementinteractive.civilwar.resource.model.ResourceType;
import com.basementinteractive.civilwar.resource.model.dto.request.ResourceProductionSettingsRequest;
import com.basementinteractive.civilwar.resource.model.dto.response.ResourceProductionSettingsResponse;
import com.basementinteractive.civilwar.resource.repository.ResourceProductionSettingsRepository;
import com.basementinteractive.civilwar.resource.service.ResourceProductionSettingsService;
import lombok.RequiredArgsConstructor;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.basementinteractive.civilwar.resource.constants.ResourceConstants.VARIABLE_PLAYER_BONUS;
import static com.basementinteractive.civilwar.resource.constants.ResourceConstants.VARIABLE_LEVEL;
import static com.basementinteractive.civilwar.resource.constants.ResourceConstants.VARIABLE_WORKERS;
import static com.basementinteractive.civilwar.resource.constants.ResourceConstants.VARIABLE_ZONE_BONUS;

@Service
@RequiredArgsConstructor
public class ResourceProductionSettingsServiceImpl implements ResourceProductionSettingsService {

    private final ResourceProductionSettingsRepository resourceProductionSettingsRepo;
    private final RequestToEntityMapper<ResourceProductionSettingsRequest, ResourceProductionSettings> resourceProductionSettingsRequestToEntityMapper;
    private final EntityToResponseMapper<ResourceProductionSettings, ResourceProductionSettingsResponse> resourceProductionSettingsEntityToResponseMapper;

    @Override
    public EntityCreatedResponse create(ResourceProductionSettingsRequest resourceProductionSettingsRequest) {
        validateFormula(resourceProductionSettingsRequest.getFormula());

        ResourceProductionSettings newResourceProductionSettings = resourceProductionSettingsRequestToEntityMapper.map(resourceProductionSettingsRequest, ResourceProductionSettings.class);
        Long id = resourceProductionSettingsRepo.save(newResourceProductionSettings).getId();

        return new EntityCreatedResponse(id);
    }

    @Override
    public ResourceProductionSettingsResponse update(Long id, ResourceProductionSettingsRequest resourceProductionSettingsRequest) {
        ResourceProductionSettings resourceProductionSettings = getByIdOrThrowEx(id);
        validateFormula(resourceProductionSettingsRequest.getFormula());

        ResourceProductionSettings updatedResourceProductionSettings = resourceProductionSettingsRequestToEntityMapper.map(
                resourceProductionSettingsRequest,
                resourceProductionSettings
        );

        return resourceProductionSettingsEntityToResponseMapper.map(
                resourceProductionSettingsRepo.save(updatedResourceProductionSettings),
                ResourceProductionSettingsResponse.class
        );
    }

    @Override
    public void delete(Long id) {
        resourceProductionSettingsRepo.delete(getByIdOrThrowEx(id));
    }

    @Override
    public ResourceProductionSettingsResponse get(Long id) {
        return resourceProductionSettingsEntityToResponseMapper.map(getByIdOrThrowEx(id), ResourceProductionSettingsResponse.class);
    }

    @Override
    public List<ResourceProductionSettingsResponse> getAll() {
        return resourceProductionSettingsEntityToResponseMapper.mapToList(resourceProductionSettingsRepo.findAll(), ResourceProductionSettingsResponse.class);
    }

    @Override
    public Set<ResourceProductionSettings> getDistinctResourceProductionSettings() {
        Set<ResourceProductionSettings> distinctResourceProductionSettings = resourceProductionSettingsRepo.findDistinctByResourceType();

        Arrays.stream(ResourceType.values())
                .filter(resourceType -> distinctResourceProductionSettings.stream().noneMatch(settings -> settings.getResourceType() == resourceType))
                .findFirst()
                .ifPresent(missingType -> {
                    throw new RuntimeException("(initResourceProductionSettings) Missing resource type formula: " + missingType);
                });

        return distinctResourceProductionSettings;
    }

    private ResourceProductionSettings getByIdOrThrowEx(Long id) {
        return resourceProductionSettingsRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("ResourceProductionSettings with id " + id + " not found"));
    }

    private void validateFormula(String formula) {
        try {
            Expression expression = new ExpressionBuilder(formula)
                    .variables(VARIABLE_LEVEL, VARIABLE_WORKERS, VARIABLE_PLAYER_BONUS, VARIABLE_ZONE_BONUS)
                    .build();

            ValidationResult validationResult = expression.validate(false);
            if (!validationResult.isValid()) {
                String errorMessages = validationResult.getErrors().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));

                throw new IllegalArgumentException(errorMessages);
            }

            expression.setVariable(VARIABLE_LEVEL, 1)
                    .setVariable(VARIABLE_WORKERS, 1)
                    .setVariable(VARIABLE_PLAYER_BONUS, 1)
                    .setVariable(VARIABLE_ZONE_BONUS, 1)
                    .evaluate();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid formula: " + e.getMessage());
        }
    }

}

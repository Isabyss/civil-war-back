package com.basementinteractive.civilwar.resource.service.impl;

import com.basementinteractive.civilwar.common.exception.EntityNotFoundException;
import com.basementinteractive.civilwar.common.mappers.EntityToResponseMapper;
import com.basementinteractive.civilwar.common.mappers.RequestToEntityMapper;
import com.basementinteractive.civilwar.common.model.dto.response.EntityCreatedResponse;
import com.basementinteractive.civilwar.resource.model.ResourceProductionSettings;
import com.basementinteractive.civilwar.resource.model.dto.request.ResourceProductionSettingsRequest;
import com.basementinteractive.civilwar.resource.model.dto.response.ResourceProductionSettingsResponse;
import com.basementinteractive.civilwar.resource.repository.ResourceProductionSettingsRepository;
import com.basementinteractive.civilwar.resource.service.ResourceProductionSettingsService;
import lombok.RequiredArgsConstructor;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.basementinteractive.civilwar.resource.constants.ResourceConstants.VARIABLE_BONUS;
import static com.basementinteractive.civilwar.resource.constants.ResourceConstants.VARIABLE_LEVEL;
import static com.basementinteractive.civilwar.resource.constants.ResourceConstants.VARIABLE_WORKERS;

@Service
@RequiredArgsConstructor
public class ResourceProductionSettingsServiceImpl implements ResourceProductionSettingsService {

    private final ResourceProductionSettingsRepository resourceProductionSettingsRepository;
    private final RequestToEntityMapper<ResourceProductionSettingsRequest, ResourceProductionSettings> resourceProductionSettingsRequestToEntityMapper;
    private final EntityToResponseMapper<ResourceProductionSettings, ResourceProductionSettingsResponse> resourceProductionSettingsEntityToResponseMapper;

    @Override
    public EntityCreatedResponse create(ResourceProductionSettingsRequest resourceProductionSettingsRequest) {
        validateFormula(resourceProductionSettingsRequest.getFormula());

        Long id = resourceProductionSettingsRepository.save(
                resourceProductionSettingsRequestToEntityMapper.map(resourceProductionSettingsRequest, ResourceProductionSettings.class)).getId();

        return new EntityCreatedResponse(id);
    }

    @Override
    public ResourceProductionSettingsResponse update(Long id, ResourceProductionSettingsRequest resourceProductionSettingsRequest) {
        validateFormula(resourceProductionSettingsRequest.getFormula());

        ResourceProductionSettings updatedResourceProductionSettings = resourceProductionSettingsRequestToEntityMapper.map(
                resourceProductionSettingsRequest,
                getByIdOrThrowEx(id)
        );

        return resourceProductionSettingsEntityToResponseMapper.map(
                resourceProductionSettingsRepository.save(updatedResourceProductionSettings),
                ResourceProductionSettingsResponse.class
        );
    }

    @Override
    public void delete(Long id) {
        resourceProductionSettingsRepository.delete(getByIdOrThrowEx(id));
    }

    @Override
    public ResourceProductionSettingsResponse get(Long id) {
        return resourceProductionSettingsEntityToResponseMapper.map(getByIdOrThrowEx(id), ResourceProductionSettingsResponse.class);
    }

    @Override
    public List<ResourceProductionSettingsResponse> getAll() {
        return resourceProductionSettingsEntityToResponseMapper.mapToList(resourceProductionSettingsRepository.findAll(), ResourceProductionSettingsResponse.class);
    }

    private ResourceProductionSettings getByIdOrThrowEx(Long id) {
        return resourceProductionSettingsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("ResourceProductionSettings with id " + id + " not found"));
    }

    private void validateFormula(String formula) {
        try {
            ValidationResult result = new ExpressionBuilder(formula)
                    .variables(VARIABLE_LEVEL, VARIABLE_WORKERS, VARIABLE_BONUS)
                    .build().validate(false);

            if (!result.isValid()) {
                String errorMessages = result.getErrors().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));

                throw new IllegalArgumentException(errorMessages);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid formula: " + e.getMessage());
        }
    }

}

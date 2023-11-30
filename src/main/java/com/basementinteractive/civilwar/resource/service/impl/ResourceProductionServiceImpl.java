package com.basementinteractive.civilwar.resource.service.impl;

import com.basementinteractive.civilwar.resource.repository.ResourceProductionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResourceProductionServiceImpl {

    private final ResourceProductionRepository resourceProductionRepository;

}

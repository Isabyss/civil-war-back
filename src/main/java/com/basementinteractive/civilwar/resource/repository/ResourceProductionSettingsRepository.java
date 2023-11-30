package com.basementinteractive.civilwar.resource.repository;

import com.basementinteractive.civilwar.resource.model.ResourceProductionSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceProductionSettingsRepository extends JpaRepository<ResourceProductionSettings, Long> {

}

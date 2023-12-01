package com.basementinteractive.civilwar.resource.repository;

import com.basementinteractive.civilwar.resource.model.ResourceProductionSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ResourceProductionSettingsRepository extends JpaRepository<ResourceProductionSettings, Long> {

    @Query("SELECT r FROM ResourceProductionSettings r WHERE r.id IN (SELECT MAX(r2.id) FROM ResourceProductionSettings r2 GROUP BY r2.resourceType)")
    Set<ResourceProductionSettings> findDistinctByResourceType();

}

package com.basementinteractive.civilwar.resource.repository;

import com.basementinteractive.civilwar.resource.model.ResourceProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceProductionRepository extends JpaRepository<ResourceProduction, Long> {

}

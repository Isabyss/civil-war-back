package com.basementinteractive.civilwar.resource.repository;

import com.basementinteractive.civilwar.resource.model.ResourceStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceStockRepository extends JpaRepository<ResourceStock, Long> {

}

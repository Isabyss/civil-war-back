package com.basementinteractive.civilwar.resource.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "resource_production_settings")
public class ResourceProductionSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_production_settings_id_gen")
    @SequenceGenerator(name = "resource_production_settings_id_gen", sequenceName = "resource_production_settings_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type", nullable = false, length = 10)
    private ResourceType resourceType;

    @Column(name = "formula", nullable = false)
    private String formula;

}

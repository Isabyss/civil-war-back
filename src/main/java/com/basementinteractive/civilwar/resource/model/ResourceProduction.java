package com.basementinteractive.civilwar.resource.model;

import com.basementinteractive.civilwar.playerbase.model.PlayerBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resource_production")
public class ResourceProduction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_production_id_gen")
    @SequenceGenerator(name = "resource_production_id_gen", sequenceName = "resource_production_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type", nullable = false, length = 10)
    private ResourceType resourceType;

    @Column(name = "upgraded_at", nullable = false)
    private LocalDateTime upgradedAt;

    @Column(name = "computed_at", nullable = false)
    private LocalDateTime computedAt;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "workers_assigned", nullable = false)
    private Integer workersAssigned;

    @Column(name = "zone_bonus", nullable = false)
    private Double zoneBonus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resource_production_settings_id", nullable = false)
    private ResourceProductionSettings resourceProductionSettings;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_base_id", nullable = false)
    private PlayerBase playerBase;

    @PrePersist
    protected void onCreate() {
        upgradedAt = computedAt = LocalDateTime.now();
        level = 1;
        workersAssigned = 0;
    }

}

package com.basementinteractive.civilwar.playerbase.model;

import com.basementinteractive.civilwar.player.model.Player;
import com.basementinteractive.civilwar.resource.model.ResourceProduction;
import com.basementinteractive.civilwar.resource.model.ResourceStock;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player_base")
public class PlayerBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_base_id_gen")
    @SequenceGenerator(name = "player_base_id_gen", sequenceName = "player_base_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "x", nullable = false)
    private Integer x;

    @Column(name = "y", nullable = false)
    private Integer y;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "total_workers", nullable = false)
    private Integer totalWorkers;

    @Column(name = "player_bonus", nullable = false)
    private Double playerBonus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "playerBase")
    private Set<ResourceProduction> resourceProductions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "playerBase")
    private Set<ResourceStock> resourceStocks = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @PrePersist
    protected void onCreate() {
        level = 1;
    }

}

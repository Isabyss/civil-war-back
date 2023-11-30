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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "player_base")
public class PlayerBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_base_id_gen")
    @SequenceGenerator(name = "player_base_id_gen", sequenceName = "player_base_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "x_coordinate", nullable = false)
    private Integer xCoordinate;

    @Column(name = "y_coordinate", nullable = false)
    private Integer yCoordinate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "playerBase")
    private Set<ResourceProduction> resourceProductions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "playerBase")
    private Set<ResourceStock> resourceStocks = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

}

package com.basementinteractive.civilwar.playerbase.repository;

import com.basementinteractive.civilwar.playerbase.model.PlayerBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PlayerBaseRepository extends JpaRepository<PlayerBase, Long> {

    Set<PlayerBase> findPlayerBasesByPlayerId(Long playerId);

}

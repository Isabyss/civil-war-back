package com.basementinteractive.civilwar.playerbase.repository;

import com.basementinteractive.civilwar.playerbase.model.PlayerBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerBaseRepository extends JpaRepository<PlayerBase, Long> {

    List<PlayerBase> findPlayerBasesByPlayerId(Long playerId);

}

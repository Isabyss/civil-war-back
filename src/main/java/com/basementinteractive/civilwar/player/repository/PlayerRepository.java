package com.basementinteractive.civilwar.player.repository;

import com.basementinteractive.civilwar.player.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}

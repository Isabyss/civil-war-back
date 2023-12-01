package com.basementinteractive.civilwar.player.repository;

import com.basementinteractive.civilwar.player.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByUsernameIgnoreCaseOrEmail(String username, String email);

}

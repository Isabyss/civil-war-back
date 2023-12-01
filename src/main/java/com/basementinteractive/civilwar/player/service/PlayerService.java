package com.basementinteractive.civilwar.player.service;

import com.basementinteractive.civilwar.common.model.dto.response.EntityCreatedResponse;
import com.basementinteractive.civilwar.player.model.Player;
import com.basementinteractive.civilwar.player.model.dto.request.PlayerRequest;
import com.basementinteractive.civilwar.player.model.dto.response.PlayerResponse;

import java.util.List;

public interface PlayerService {

    EntityCreatedResponse create(PlayerRequest playerRequest);

    PlayerResponse update(Long id, PlayerRequest playerRequest);

    void delete(Long id);

    PlayerResponse get(Long id);

    Player getOrThrowEx(Long id);

    List<PlayerResponse> getAll();

}

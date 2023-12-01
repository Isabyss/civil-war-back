package com.basementinteractive.civilwar.playerbase.service;

import com.basementinteractive.civilwar.playerbase.model.PlayerBase;
import com.basementinteractive.civilwar.playerbase.model.dto.response.PlayerBaseResponse;

import java.util.Set;

public interface PlayerBaseQueryService {

    PlayerBaseResponse get(Long id);

    Set<PlayerBase> getAllByPlayerId(Long playerId);

}

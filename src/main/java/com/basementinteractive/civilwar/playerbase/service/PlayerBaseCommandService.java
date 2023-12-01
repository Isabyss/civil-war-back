package com.basementinteractive.civilwar.playerbase.service;

import com.basementinteractive.civilwar.common.model.dto.response.EntityCreatedResponse;
import com.basementinteractive.civilwar.playerbase.model.dto.request.PlayerBaseRequest;
import com.basementinteractive.civilwar.playerbase.model.dto.response.PlayerBaseResponse;

public interface PlayerBaseCommandService {

    EntityCreatedResponse create(PlayerBaseRequest playerBaseRequest);

    PlayerBaseResponse update(Long id, PlayerBaseRequest playerBaseRequest);

    void delete(Long id);

}

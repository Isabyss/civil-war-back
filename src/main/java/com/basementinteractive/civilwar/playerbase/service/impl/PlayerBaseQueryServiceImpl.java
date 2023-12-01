package com.basementinteractive.civilwar.playerbase.service.impl;

import com.basementinteractive.civilwar.common.exception.EntityNotFoundException;
import com.basementinteractive.civilwar.common.mappers.EntityToResponseMapper;
import com.basementinteractive.civilwar.playerbase.model.PlayerBase;
import com.basementinteractive.civilwar.playerbase.model.dto.response.PlayerBaseResponse;
import com.basementinteractive.civilwar.playerbase.repository.PlayerBaseRepository;
import com.basementinteractive.civilwar.playerbase.service.PlayerBaseQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PlayerBaseQueryServiceImpl implements PlayerBaseQueryService {

    private final PlayerBaseRepository playerBaseRepository;
    private final EntityToResponseMapper<PlayerBase, PlayerBaseResponse> playerBaseEntityToResponseMapper;

    @Override
    public PlayerBaseResponse get(Long id) {
        return playerBaseEntityToResponseMapper.map(getByIdOrThrowEx(id), PlayerBaseResponse.class);
    }

    @Override
    public Set<PlayerBase> getAllByPlayerId(Long playerId) {
        return playerBaseRepository.findPlayerBasesByPlayerId(playerId);
    }

    private PlayerBase getByIdOrThrowEx(Long id) {
        return playerBaseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("PlayerBase with id " + id + " not found"));
    }

}

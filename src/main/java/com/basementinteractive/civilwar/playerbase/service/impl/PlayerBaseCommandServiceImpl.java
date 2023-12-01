package com.basementinteractive.civilwar.playerbase.service.impl;

import com.basementinteractive.civilwar.common.exception.EntityNotFoundException;
import com.basementinteractive.civilwar.common.model.dto.response.EntityCreatedResponse;
import com.basementinteractive.civilwar.player.service.PlayerService;
import com.basementinteractive.civilwar.playerbase.model.PlayerBase;
import com.basementinteractive.civilwar.playerbase.model.dto.request.PlayerBaseRequest;
import com.basementinteractive.civilwar.playerbase.model.dto.response.PlayerBaseResponse;
import com.basementinteractive.civilwar.playerbase.repository.PlayerBaseRepository;
import com.basementinteractive.civilwar.playerbase.service.PlayerBaseCommandService;
import com.basementinteractive.civilwar.resource.service.ResourceProductionService;
import com.basementinteractive.civilwar.resource.service.ResourceStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.basementinteractive.civilwar.common.constants.EntityConstants.STARTING_BONUS;
import static com.basementinteractive.civilwar.common.constants.EntityConstants.STARTING_NUM_OF_WORKERS_PLAYER_BASE;

@Service
@RequiredArgsConstructor
public class PlayerBaseCommandServiceImpl implements PlayerBaseCommandService {

    private final PlayerBaseRepository playerBaseRepository;
    private final PlayerService playerService;
    private final ResourceProductionService resourceProductionService;
    private final ResourceStockService resourceStockService;

    @Transactional
    @Override
    public EntityCreatedResponse create(PlayerBaseRequest playerBaseRequest) {
        // TODO Implement extracting playerId from auth token
        PlayerBase newPlayerBase = PlayerBase.builder()
                .player(playerService.getOrThrowEx(1L))
                .x(playerBaseRequest.getX())
                .y(playerBaseRequest.getY())
                .totalWorkers(STARTING_NUM_OF_WORKERS_PLAYER_BASE)
                .playerBonus(STARTING_BONUS)
                .build();

        newPlayerBase = playerBaseRepository.save(newPlayerBase);
        initResourcesForBase(newPlayerBase);

        return new EntityCreatedResponse(newPlayerBase.getId());
    }

    @Override
    public PlayerBaseResponse update(Long id, PlayerBaseRequest playerBaseRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {
        playerBaseRepository.delete(getByIdOrThrowEx(id));
    }

    private void initResourcesForBase(PlayerBase playerBase) {
        resourceProductionService.initResourcesProductionForBase(playerBase);
        resourceStockService.initResourcesStockForBase(playerBase);
    }

    private PlayerBase getByIdOrThrowEx(Long id) {
        return playerBaseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("PlayerBase with id " + id + " not found"));
    }

}

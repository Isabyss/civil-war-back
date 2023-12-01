package com.basementinteractive.civilwar.player.service.impl;

import com.basementinteractive.civilwar.common.exception.EntityNotFoundException;
import com.basementinteractive.civilwar.common.mappers.EntityToResponseMapper;
import com.basementinteractive.civilwar.common.mappers.RequestToEntityMapper;
import com.basementinteractive.civilwar.common.model.dto.response.EntityCreatedResponse;
import com.basementinteractive.civilwar.player.model.Player;
import com.basementinteractive.civilwar.player.model.dto.request.PlayerRequest;
import com.basementinteractive.civilwar.player.model.dto.response.PlayerResponse;
import com.basementinteractive.civilwar.player.repository.PlayerRepository;
import com.basementinteractive.civilwar.player.service.PlayerService;
import com.basementinteractive.civilwar.playerbase.service.PlayerBaseQueryService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final RequestToEntityMapper<PlayerRequest, Player> playerRequestToEntityMapper;
    private final EntityToResponseMapper<Player, PlayerResponse> playerEntityToResponseMapper;
    private final PlayerBaseQueryService playerBaseQueryService;

    @Override
    public EntityCreatedResponse create(PlayerRequest playerRequest) {
        checkUsernameAndEmailAvailabilityOrThrowEx(playerRequest.getUsername(), playerRequest.getEmail());

        Player newPlayer = playerRequestToEntityMapper.map(playerRequest, Player.class);
        Long id = playerRepository.save(newPlayer).getId();

        return new EntityCreatedResponse(id);
    }

    @Override
    public PlayerResponse update(Long id, PlayerRequest playerRequest) {
        Player player = getOrThrowEx(id);
        checkUsernameAndEmailAvailabilityOrThrowEx(playerRequest.getUsername(), playerRequest.getEmail());

        Player updatedPlayer = playerRequestToEntityMapper.map(playerRequest, player);

        return playerEntityToResponseMapper.map(playerRepository.save(updatedPlayer), PlayerResponse.class);
    }

    @Override
    public void delete(Long id) {
        playerRepository.delete(getOrThrowEx(id));
    }

    @Override
    public PlayerResponse get(Long id) {
        Player player = getOrThrowEx(id);
        player.setBases(playerBaseQueryService.getAllByPlayerId(id));

        return playerEntityToResponseMapper.map(player, PlayerResponse.class);
    }

    @Override
    public Player getOrThrowEx(Long id) {
        return playerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Player with id " + id + " not found"));
    }

    @Override
    public List<PlayerResponse> getAll() {
        return playerEntityToResponseMapper.mapToList(playerRepository.findAll(), PlayerResponse.class);
    }

    private void checkUsernameAndEmailAvailabilityOrThrowEx(String username, String email) {
        if (playerRepository.findByUsernameIgnoreCaseOrEmail(username, email).isPresent()) {
            throw new EntityExistsException("A player with username " + username + " or email " + email + " already exists");
        }
    }

}

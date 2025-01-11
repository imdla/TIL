package com.example.relationprac.domain.team;

import com.example.relationprac.domain.team.dto.TeamRequestDto;
import com.example.relationprac.domain.team.dto.TeamResponseDto;
import com.example.relationprac.global.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    // CREATE
    @Transactional
    public TeamResponseDto addTeam(TeamRequestDto requestDto) {
        return TeamResponseDto.from(teamRepository.save(requestDto.toEntity()));
    }

    // READ ALL
    public List<TeamResponseDto> findAllTeam() {
        return teamRepository.findAll().stream()
                .map(TeamResponseDto::from)
                .toList();
    }

    // READ ONE
    public TeamResponseDto findById(Long teamId) {
        return TeamResponseDto.from(
                teamRepository.findById(teamId)
                        .orElseThrow(ResourceNotFoundException::new)
        );
    }
}

package com.example.relationprac.domain.team;

import com.example.relationprac.domain.team.dto.TeamRequestDto;
import com.example.relationprac.domain.team.dto.TeamResponseDto;
import com.example.relationprac.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<TeamResponseDto>> addTeam(@RequestBody TeamRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(
                   "팀이 성공적으로 생성되었습니다.",
                   "CREATED",
                   teamService.addTeam(requestDto)
                ));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<TeamResponseDto>>> findAllTeam() {
        return ResponseEntity.ok(ApiResponse.ok(
                teamService.findAllTeam()
        ));
    }

    // READ ONE
    @GetMapping("/{teamId}")
    public ResponseEntity<ApiResponse<TeamResponseDto>> findById(@PathVariable Long teamId) {
        return ResponseEntity.ok(ApiResponse.ok(
                teamService.findById(teamId)
        ));
    }
}

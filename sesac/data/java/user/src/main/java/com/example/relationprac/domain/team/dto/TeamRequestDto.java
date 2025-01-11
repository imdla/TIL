package com.example.relationprac.domain.team.dto;

import com.example.relationprac.domain.team.Team;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
public class TeamRequestDto {

    @NotBlank(message = "팀명은 필수입력입니다.")
    @Length(min = 3, max = 20, message = "팀명은 3자 이상 20자 이하입니다.")
    private String teamName;

    public Team toEntity() {
        return Team.builder()
                .teamName(this.teamName)
                .build();
    }
}

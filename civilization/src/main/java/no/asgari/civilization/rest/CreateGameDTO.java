package no.asgari.civilization.rest;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import no.asgari.civilization.representations.GameType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@JsonRootName(value="CreateGame")
public class CreateGameDTO {
    @NotNull
    private GameType type;
    @NotBlank
    private String username;

    @NotNull
    @Min(1)
    private Integer numOfPlayers;
}

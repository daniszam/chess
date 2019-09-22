package ru.kpfu.itis.darzam.chess.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class ChessBoardPropertiesDTO {

    @NotNull(message = "Обязательное поле")
    private Short height;
    @NotNull(message = "Обязательное поле")
    private Short width;
    @NotNull(message = "Обязательное поле")
    private String startPosition;
    @NotNull(message = "Обязательное поле")
    private String endPosition;
}


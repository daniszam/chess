package ru.kpfu.itis.darzam.chess.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChessBoardPropertiesDTO {

    @NotNull(message = "Обязательное поле")
    @Min(Short.MIN_VALUE)
    @Max(Short.MAX_VALUE)
    private short height;
    @NotNull(message = "Обязательное поле")
    @Min(Short.MIN_VALUE)
    @Max(Short.MAX_VALUE)
    private short width;
    @NotNull(message = "Обязательное поле")
    @Pattern(regexp = "^[A-Ba-b]+\\d+$")
    private String startPosition;
    @NotNull(message = "Обязательное поле")
    @Pattern(regexp = "^[A-ba-b]+\\d+$")
    private String endPosition;
}


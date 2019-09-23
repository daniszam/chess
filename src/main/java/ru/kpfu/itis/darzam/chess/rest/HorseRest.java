package ru.kpfu.itis.darzam.chess.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.darzam.chess.model.ChessBoardPropertiesDTO;
import ru.kpfu.itis.darzam.chess.service.HorseService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/horse/rest")
public class HorseRest {

    private final HorseService horseService;

    @GetMapping("/count")
    public Short getMinMove(@Valid ChessBoardPropertiesDTO chessBoardPropertiesDTO) {
        return horseService.getMinMoveCount(chessBoardPropertiesDTO);
    }
}

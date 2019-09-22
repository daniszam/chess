package ru.kpfu.itis.darzam.chess.service;

import ru.kpfu.itis.darzam.chess.model.ChessBoardPropertiesDTO;

public interface HorseService {

    Integer getMinMoveCount(ChessBoardPropertiesDTO chessBoardPropertiesDTO);
}

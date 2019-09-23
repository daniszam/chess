package ru.kpfu.itis.darzam.chess.service;

import org.apache.poi.ss.util.CellReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.darzam.chess.model.ChessBoardPropertiesDTO;

@Service
public class HorseServiceImpl implements HorseService {
    private Logger logger = LoggerFactory.getLogger(HorseServiceImpl.class);

    public short getMinMoveCount(ChessBoardPropertiesDTO chessBoardPropertiesDTO) throws IllegalArgumentException {
        CellReference endCellReference = new CellReference(chessBoardPropertiesDTO.getEndPosition());
        short xEnd = endCellReference.getCol();
        int yEnd = endCellReference.getRow();

        CellReference startCellReference = new CellReference(chessBoardPropertiesDTO.getStartPosition());
        short xStart = startCellReference.getCol();
        int yStart = startCellReference.getRow();

        short chessBoardHeight = chessBoardPropertiesDTO.getHeight();
        short chessBoardWidth = chessBoardPropertiesDTO.getWidth();
        if (xStart > chessBoardWidth || xEnd > chessBoardWidth
                || yStart > chessBoardHeight || yEnd > chessBoardHeight) {
            logger.debug("invalid data width:%d, height:%d, start position: %s, end position: %s",
                    chessBoardWidth, chessBoardHeight, chessBoardPropertiesDTO.getStartPosition(), chessBoardPropertiesDTO.getEndPosition());
            throw new IllegalArgumentException("invalid data");
        }

        return getMinCountMoveFromStart(xEnd - xStart, yEnd - yStart);
    }

    private static short getMinCountMoveFromStart(int x, int y) {
        // axes symmetry
        x = Math.abs(x);
        y = Math.abs(y);
        // diagonal symmetry
        if (x < y) {
            x = x + y;
            y = x - y;
            x = x - y;
        }
        // 2 corner cases
        if (x == 1 && y == 0)
            return 3;

        if (x == 2 && y == 2)
            return 4;

        // formula
        int delta = x - y;
        double divider = y > delta ? 3 : 4;
        return (short) (delta - 2 * Math.floor(Math.floor((delta - y) / divider)));
    }
}

package ru.kpfu.itis.darzam.chess.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kpfu.itis.darzam.chess.model.ChessBoardPropertiesDTO;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class HorseServiceTest {

    @Autowired
    private HorseService horseService;
    private ChessBoardPropertiesDTO chessBoardPropertiesDTO;

    @TestConfiguration
    static class HorseServiceConfig {

        @Bean
        public HorseService employeeService() {
            return new HorseServiceImpl();
        }
    }

    @Before
    public void setUpData() {
        chessBoardPropertiesDTO = ChessBoardPropertiesDTO.builder()
                .startPosition("B12")
                .endPosition("A18")
                .width((short) 2)
                .height((short) 18)
                .build();
    }

    @Test()
    public void getMinMoveCountValidTest() {
        short moveCount = horseService.getMinMoveCount(chessBoardPropertiesDTO);
        assertEquals(3, moveCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getMinMoveCountInvalidTest() {
        chessBoardPropertiesDTO.setStartPosition("INVALID");
        horseService.getMinMoveCount(chessBoardPropertiesDTO);
    }
}
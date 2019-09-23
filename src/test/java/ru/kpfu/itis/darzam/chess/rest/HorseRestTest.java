package ru.kpfu.itis.darzam.chess.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.kpfu.itis.darzam.chess.model.ChessBoardPropertiesDTO;
import ru.kpfu.itis.darzam.chess.service.HorseService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class HorseRestTest {

    private MockMvc mockMvc;

    @Mock
    private HorseService horseService;

    @InjectMocks
    private HorseRest horseRest;

    private static final String REST_URI = "/horse/rest/count";
    private MultiValueMap<String, String> multiValueMap;

    @Before
    public void setUpRequestParam() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(horseRest)
                .build();

        multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("startPosition", "A1");
        multiValueMap.add("endPosition", "A1");
        multiValueMap.add("width", "5");
        multiValueMap.add("height", "5");
    }

    @Test
    public void getMinMoveTest() throws Exception {
        when(horseService.getMinMoveCount(any(ChessBoardPropertiesDTO.class)))
                .thenReturn((short) 5);

        mockMvc.perform(get(REST_URI).params(multiValueMap))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("5"));
    }

}
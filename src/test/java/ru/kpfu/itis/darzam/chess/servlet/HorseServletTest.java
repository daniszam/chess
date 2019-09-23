package ru.kpfu.itis.darzam.chess.servlet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kpfu.itis.darzam.chess.model.ChessBoardPropertiesDTO;
import ru.kpfu.itis.darzam.chess.service.HorseService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class HorseServletTest {

    @Mock
    private HorseService horseService;
    @InjectMocks
    private HorseServlet horseServlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUpRequestParam() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void getMinMoveTest() throws Exception {
        when(horseService.getMinMoveCount(any(ChessBoardPropertiesDTO.class)))
                .thenReturn((short) 5);

        request.addParameter("startPosition", "A1");
        request.addParameter("endPosition", "A1");
        request.addParameter("width", "5");
        request.addParameter("height", "5");

        horseServlet.doGet(request, response);

        assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE, response.getContentType());
        assertEquals("5", response.getContentAsString());
    }
}
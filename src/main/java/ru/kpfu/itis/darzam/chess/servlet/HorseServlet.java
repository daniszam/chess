package ru.kpfu.itis.darzam.chess.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.kpfu.itis.darzam.chess.model.ChessBoardPropertiesDTO;
import ru.kpfu.itis.darzam.chess.service.HorseService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.kpfu.itis.darzam.chess.response_value.InvalidResponseValue.UNREACHABLE_POSITION;

@WebServlet("/horse/servlet/count")
public class HorseServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(HorseServlet.class);

    private HorseService horseService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int moveCount;
        ChessBoardPropertiesDTO chessBoardPropertiesDTO = ChessBoardPropertiesDTO.builder()
                .endPosition(req.getParameter("endPosition"))
                .startPosition(req.getParameter("startPosition"))
                .width(Short.valueOf(req.getParameter("width")))
                .height(Short.valueOf(req.getParameter("height")))
                .build();

        try {
            moveCount = horseService.getMinMoveCount(chessBoardPropertiesDTO);
        } catch (IllegalArgumentException e) {
            logger.debug("invalid argument", e);
            moveCount = UNREACHABLE_POSITION;
            resp.setStatus(HttpStatus.BAD_REQUEST.value());
        }

        resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        resp.getWriter().print(moveCount);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        if (ctx != null)
            this.horseService = ctx.getBean(HorseService.class);
    }
}

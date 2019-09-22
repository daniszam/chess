package ru.kpfu.itis.darzam.chess.servlet;

import org.springframework.stereotype.Component;
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

@WebServlet("/horse/servlet/count")
public class HorseServlet extends HttpServlet {

    private HorseService horseService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ChessBoardPropertiesDTO chessBoardPropertiesDTO = ChessBoardPropertiesDTO.builder()
                .endPosition(req.getParameter("endPosition"))
                .startPosition(req.getParameter("startPosition"))
                .width(Short.valueOf(req.getParameter("width")))
                .height(Short.valueOf(req.getParameter("height")))
                .build();

        int moveCount = horseService.getMinMoveCount(chessBoardPropertiesDTO);
        resp.setContentType("application/json");
        resp.getWriter().write(moveCount);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        if (ctx != null) {
            this.horseService = ctx.getBean( HorseService.class);
        }
    }
}

package servlet;

import bean.WeatherBean;
import entity.Weather;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class MainServlet extends HttpServlet{

    // Аннотация говорит о том,
    // что данный объект будет инициализирован
    // контейнером Glassfish DI
    @EJB
    private WeatherBean weatherBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Получаем список пользователей
        List<Weather> allWeather = weatherBean.getAll();

        // добавляем полученный список в request,
        // который отправится на jsp
        req.setAttribute("weather", allWeather);

        // отправляем request на jsp
        req.getRequestDispatcher("/list.jsp").forward(req, resp);

    }

}
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

@WebServlet("/add")
public class AddAndEditUserServlet extends HttpServlet {

    @EJB
    private WeatherBean weatherBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        // если параметр null, то пользователь
        // пришел на страницу, чтобы создать нового, иначе
        // будет выполнятся редактирование существующего пользователя
        if(req.getParameter("edit") != null){
            int id = Integer.valueOf(req.getParameter("edit"));
            Weather weather = weatherBean.get(id);

            req.setAttribute("weather", weather);
        }

        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        String date = req.getParameter("date");
        String temperature = req.getParameter("temperature");
        int windSpeed = Integer.valueOf(req.getParameter("windSpeed"));

        // если id есть, то выполняется р едактирование
        // а если нет id, то - это значит, что создается новый пользователь
        if(req.getParameter("id") != ""){
            int id = Integer.valueOf(req.getParameter("id"));
            Weather weather = weatherBean.get(id);
            weather.setWindSpeed(windSpeed);
            weather.setTemperature(temperature);
            weather.setDate(date);

            // обновляем пользователя
            weatherBean.update(weather);
        } else{
            // добавляем нового
            weatherBean.add(new Weather(date, temperature, windSpeed));
        }

        // перенаправляем на сервлет, который выводит все пользователей
        resp.sendRedirect("list");
    }
}
package entity;

import javax.persistence.*;

@Entity(name = "weather")
@NamedQuery(name = "Weather.getAll", query = "SELECT u from weather u")
public class Weather {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "weather_ID")
    private int id;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "temperature", nullable = false)
    private String temperature;

    @Column(name = "windSpeed", nullable = false)
    private int windSpeed;

    public Weather() {
    }

    public Weather(String date, String temperature, int windSpeed) {

        this.date = date;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
    }
    public int getId() {
        return id;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String name) {
        this.date = name;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String lastName) {
        this.temperature = lastName;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int age) {
        this.windSpeed = age;
    }
}
package bean;

import entity.Weather;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class WeatherBean {

    // Будет инициализирован контейнером Glassfish
    // unitName = "DEVMODE" - это имя persistence-unit
    // EntityManager дает возможность выполнять CRUD запросы в БД
    @PersistenceContext(unitName = "LABMODE")
    private EntityManager em;

    // Добавляем User-а В базу данных
    public Weather add(Weather weather){
        return em.merge(weather);
    }

    // Получаем пользователя по id
    public Weather get(int id){
        return em.find(Weather.class, id);
    }

    // обновляем пользователя
    // если User которого мыпытаемся обновить нет,
    // то запишется он как новый
    public void update(Weather weather){
        add(weather);
    }

    // удаляем User по id
    public void delete(int id){
        em.remove(get(id));
    }

    // Получаем все пользователей с БД
    public List<Weather> getAll(){
        TypedQuery<Weather> namedQuery = em.createNamedQuery("Weather.getAll", Weather.class);
        return namedQuery.getResultList();
    }

}
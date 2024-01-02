package com.agrotep.imp.exp.repository;

import com.agrotep.imp.exp.entity.Alert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.agrotep.imp.exp.repository.PersonRepository.*;

@Repository
public class AlertRepository {
    public static final Alert ALERT1 = Alert.builder().id(1L).text("Київ - Краків").creator(SVITLANA)
            .time(LocalDateTime.now().plusDays(1L)).build();
    public static final Alert ALERT2 = Alert.builder().id(2L).text("Харків - Берлін").creator(ANTON)
            .time(LocalDateTime.now().plusHours(1L)).build();
    public static final Alert ALERT3 = Alert.builder().id(3L).text("Грайфсвальд - Берлін").creator(OKSANA)
            .time(LocalDateTime.now().plusDays(3L)).build();
    public static final Alert ALERT4 = Alert.builder().id(4L).text("Гамбург - Львів").creator(OKSANA)
            .time(LocalDateTime.now().plusDays(1L).plusHours(2L)).build();
    public static final Alert ALERT5 = Alert.builder().id(5L).text("Полонне - Павлоград").creator(SVITLANA)
            .time(LocalDateTime.now().plusHours(5L)).build();
    public static final Alert ALERT6 = Alert.builder().id(6L)
            .text("""
            Вільний транспорт у Європі
            
            
            Голандія - рефи, чт-пт
            
            Німеччина - рефи, чт-пт
            
            Швейцарія - реф
            """)
            .creator(ANTON)
            .time(LocalDateTime.now()).build();

    private static final Set<Alert> ALERTS = new HashSet<>(Set.of(ALERT1, ALERT2, ALERT3, ALERT4, ALERT5, ALERT6));

    public List<Alert> findAllOrderByTime() {
        return ALERTS.stream()
                .sorted(Comparator.comparing(Alert::getTime))
                .toList();
    }

    public void save(Alert alert) {
        if (alert.getId() == null) {
            long newId = ALERTS.stream().mapToLong(Alert::getId).max().orElse(0) + 1;
            alert.setId(newId);
        }
        ALERTS.remove(alert);
        ALERTS.add(alert);
    }

    public void delete(Alert alert) {
        ALERTS.remove(alert);
    }
}

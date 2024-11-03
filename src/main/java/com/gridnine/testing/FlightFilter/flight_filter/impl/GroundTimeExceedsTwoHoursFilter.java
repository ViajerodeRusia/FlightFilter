package com.gridnine.testing.FlightFilter.flight_filter.impl;

import com.gridnine.testing.FlightFilter.flight_filter.FlightFilter;
import com.gridnine.testing.FlightFilter.testing.Flight;
import com.gridnine.testing.FlightFilter.testing.Segment;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтр для перелётов, который исключает все перелёты, где общее время,
 * проведённое на земле, превышает два часа.
 * <p>
 * Время на земле определяется как интервал между прилётом одного сегмента
 * и вылетом следующего за ним. Если в перелёте только один сегмент, он
 * автоматически проходит фильтр.
 * </p>
 */
public class GroundTimeExceedsTwoHoursFilter implements FlightFilter {
    /**
     * Фильтрует список перелётов, исключая те, где общее время на земле
     * превышает два часа (120 минут).
     *
     * @param flights список перелётов для фильтрации
     * @return список перелётов, прошедших фильтрацию
     */
    @Override
    public List<Flight> flightFilter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    // Проверяем, есть ли в перелёте больше одного сегмента
                    if (segments.size() < 2) {
                        return true; // Перелет с одним сегментом автоматически проходит фильтр
                    }

                    long totalGroundTimeInMinutes = 0;
                    // Проходим по каждому сегменту, начиная со второго
                    for (int i = 1; i < segments.size(); i++) {
                        // Вычисляем время на земле между двумя сегментами
                        Duration groundTime = Duration.between(
                                segments.get(i - 1).getArrivalDate(),
                                segments.get(i).getDepartureDate()
                        );
                        // Добавляем время на земле в минутах
                        totalGroundTimeInMinutes += groundTime.toMinutes();
                    }
                    // Проверяем, что общее время на земле превышает 2 часов (120 минут)
                    return totalGroundTimeInMinutes > 120;
                })
                .collect(Collectors.toList());
    }
}

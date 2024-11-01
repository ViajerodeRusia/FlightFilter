package com.gridnine.testing.FlightFilter.flight_filter.impl;

import com.gridnine.testing.FlightFilter.flight_filter.FlightFilter;
import com.gridnine.testing.FlightFilter.testing.Flight;
import com.gridnine.testing.FlightFilter.testing.Segment;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class GroundTimeExceedsTwoHours implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
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
                    // Проверяем, что общее время на земле не превышает 2 часов (120 минут)
                    return totalGroundTimeInMinutes <= 120;
                })
                .collect(Collectors.toList());
    }
}

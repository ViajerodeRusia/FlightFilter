package com.gridnine.testing.FlightFilter.flight_filter.impl;

import com.gridnine.testing.FlightFilter.flight_filter.FlightFilter;
import com.gridnine.testing.FlightFilter.testing.Flight;

import java.time.LocalDateTime;
import java.util.List;
/**
 * Фильтр для перелётов, который исключает все перелёты, где хотя бы один сегмент
 * вылетает после текущего момента времени.
 * * <p>
 *  * Этот фильтр проверяет каждый сегмент перелёта и оставляет только те
 *  * перелёты, где все сегменты имеют корректные даты (дата вылета до текущего момента времени).
 *  * </p>
 */
public class DepartureBeforeNow implements FlightFilter {
    /**
     * Фильтрует список перелётов, исключая те, где хотя бы один сегмент
     * вылетает после текущего момента времени.
     *
     * @param flights список перелётов для фильтрации
     * @return список перелётов, прошедших фильтрацию
     */
    @Override
    public List<Flight> flightFilter(List<Flight> flights) {
        LocalDateTime now = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isBefore(now)))
                .toList();
    }
}

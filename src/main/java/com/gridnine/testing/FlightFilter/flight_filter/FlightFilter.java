package com.gridnine.testing.FlightFilter.flight_filter;

import com.gridnine.testing.FlightFilter.testing.Flight;

import java.util.List;

/**
 * Интерфейс для фильтрации перелётов.
 */
public interface FlightFilter {
    /**
     * Фильтрует список перелётов на основе определённых критериев.
     *
     * @param flights список перелётов для фильтрации
     * @return список перелётов, прошедших фильтрацию
     */
    List<Flight> flightFilter(List<Flight> flights);
}

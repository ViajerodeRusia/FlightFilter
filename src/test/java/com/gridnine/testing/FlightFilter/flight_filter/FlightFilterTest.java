package com.gridnine.testing.FlightFilter.flight_filter;

import com.gridnine.testing.FlightFilter.flight_filter.impl.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.FlightFilter.flight_filter.impl.DepartureBeforeNowFilter;
import com.gridnine.testing.FlightFilter.flight_filter.impl.GroundTimeExceedsTwoHoursFilter;
import com.gridnine.testing.FlightFilter.testing.Flight;
import com.gridnine.testing.FlightFilter.testing.FlightBuilder;
import com.gridnine.testing.FlightFilter.testing.Segment;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightFilterTest {
    @Test
    void testDepartureBeforeNowFilter() {
        FlightFilter filter = new DepartureBeforeNowFilter();
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> result = filter.flightFilter(flights);

        // Проверяем, что все перелеты в результате имеют вылет до текущего момента
        assertTrue(result.stream().allMatch(flight ->
                flight.getSegments().stream().allMatch(segment ->
                        segment.getDepartureDate().isBefore(LocalDateTime.now())
                )
        ), "У всех вылетов должно быть отправление до текущего момента.");
    }

    @Test
    void testArrivalBeforeDepartureFilter() {
        FlightFilter filter = new ArrivalBeforeDepartureFilter();
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> result = filter.flightFilter(flights);

        // Проверяем, что все перелеты в результате имеют корректные даты
        assertTrue(result.stream().allMatch(flight ->
                flight.getSegments().stream().allMatch(segment ->
                        segment.getArrivalDate().isAfter(segment.getDepartureDate())
                )
        ), "У всех вылетов должно быть прибытие до отправления.");
    }

    @Test
    void testGroundTimeExceedsTwoHoursFilter() {
        FlightFilter filter = new GroundTimeExceedsTwoHoursFilter();
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> result = filter.flightFilter(flights);

        // Проверяем, что общее время на земле не превышает 2 часа
        assertTrue(result.stream().allMatch(flight -> {
            List<Segment> segments = flight.getSegments();
            long totalGroundTimeInMinutes = 0;
            for (int i = 1; i < segments.size(); i++) {
                Duration groundTime = Duration.between(
                        segments.get(i - 1).getArrivalDate(),
                        segments.get(i).getDepartureDate()
                );
                totalGroundTimeInMinutes += groundTime.toMinutes();
            }
            return totalGroundTimeInMinutes <= 120;
        }), "У всех вылетов должно быть общее время на земле не более двух часов.");
    }
}
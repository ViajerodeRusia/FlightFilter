package com.gridnine.testing.FlightFilter.flight_filter.impl;

import com.gridnine.testing.FlightFilter.flight_filter.FlightFilter;
import com.gridnine.testing.FlightFilter.testing.Flight;

import java.util.List;

public class ArrivalBeforeDepartureFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .toList();
    }
}

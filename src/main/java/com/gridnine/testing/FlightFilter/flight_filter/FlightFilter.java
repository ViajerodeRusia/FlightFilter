package com.gridnine.testing.FlightFilter.flight_filter;

import com.gridnine.testing.FlightFilter.testing.Flight;

import java.util.List;

public interface FlightFilter {
    List<Flight> flightFilter(List<Flight> flights);
}

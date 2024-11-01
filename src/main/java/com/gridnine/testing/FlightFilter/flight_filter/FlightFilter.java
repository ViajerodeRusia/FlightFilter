package com.gridnine.testing.FlightFilter.flight_filter;

import com.gridnine.testing.FlightFilter.testing.Flight;
import com.gridnine.testing.FlightFilter.testing.Segment;

import java.util.List;

public interface FlightFilter {
    List<Flight> filter(List<Flight> flights);
}

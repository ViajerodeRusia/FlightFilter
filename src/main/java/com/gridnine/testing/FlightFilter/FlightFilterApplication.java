package com.gridnine.testing.FlightFilter;

import com.gridnine.testing.FlightFilter.flight_filter.FlightFilter;
import com.gridnine.testing.FlightFilter.flight_filter.impl.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.FlightFilter.flight_filter.impl.DepartureBeforeNowFilter;
import com.gridnine.testing.FlightFilter.flight_filter.impl.GroundTimeExceedsTwoHours;
import com.gridnine.testing.FlightFilter.testing.Flight;
import com.gridnine.testing.FlightFilter.testing.FlightBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FlightFilterApplication {
	public static void main(String[] args) {
		List<Flight> flights = FlightBuilder.createFlights();
		System.out.println("All flights:");
		flights.forEach(System.out::println);

		FlightFilter departureBeforeNow = new DepartureBeforeNowFilter();
		FlightFilter arrivalBeforeDeparture = new ArrivalBeforeDepartureFilter();
		FlightFilter groundTimeExceedsTwoHours = new GroundTimeExceedsTwoHours();

		System.out.println("Flights after departure before now filter:");
		departureBeforeNow.filter(flights).forEach(System.out::println);

		System.out.println("Flights after arrival before departure filter:");
		arrivalBeforeDeparture.filter(flights).forEach(System.out::println);

		System.out.println("Flights after ground time exceeds two hours filter:");
		groundTimeExceedsTwoHours.filter(flights).forEach(System.out::println);
	}
}

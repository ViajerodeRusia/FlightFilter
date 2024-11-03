package com.gridnine.testing.FlightFilter;

import com.gridnine.testing.FlightFilter.flight_filter.FlightFilter;
import com.gridnine.testing.FlightFilter.flight_filter.impl.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.FlightFilter.flight_filter.impl.DepartureBeforeNowFilter;
import com.gridnine.testing.FlightFilter.flight_filter.impl.GroundTimeExceedsTwoHoursFilter;
import com.gridnine.testing.FlightFilter.testing.Flight;
import com.gridnine.testing.FlightFilter.testing.FlightBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FlightFilterApplication {

	public static void main(String[] args) {
		List<Flight> flights = FlightBuilder.createFlights();
		System.out.println("Все вылеты:");
		flights.forEach(System.out::println);
		System.out.println();

		FlightFilter departureBeforeNow = new DepartureBeforeNowFilter();
		FlightFilter arrivalBeforeDeparture = new ArrivalBeforeDepartureFilter();
		FlightFilter groundTimeExceedsTwoHoursFilter = new GroundTimeExceedsTwoHoursFilter();

		System.out.println("Фильтрация вылетов с отправкой до текущего момента:");
		departureBeforeNow.flightFilter(flights).forEach(System.out::println);
		System.out.println();

		System.out.println("Фильтрация вылетов с прибытием до отправления:");
		arrivalBeforeDeparture.flightFilter(flights).forEach(System.out::println);
		System.out.println();

		System.out.println("Фильтрация перелетов с общим временем на земле больше двух часов (120 минут):");
		groundTimeExceedsTwoHoursFilter.flightFilter(flights).forEach(System.out::println);
	}

}

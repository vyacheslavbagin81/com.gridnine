package org.example;

import org.example.filter.CriterionSegment;
import org.example.filter.ServiceFilter;
import org.example.model.Flight;
import org.example.testing.FlightBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ServiceFilter filter = new ServiceFilter(new CriterionSegment());

        List<Flight> flights = FlightBuilder.createFlights();

        for (Flight flight : flights) {
            System.out.println(flight);
        }
        System.out.println("************");

        filter.flightsBeforeTheCurrentTime(flights, LocalDateTime.now());

        System.out.println("************");

        filter.arrivalDateBeforeDepartureDate(flights);

        System.out.println("************");

        filter.theTimeSpentOnEarthExceedsXHours(flights, 2);
    }
}
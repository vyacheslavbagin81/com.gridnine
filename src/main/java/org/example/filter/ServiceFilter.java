package org.example.filter;

import lombok.AllArgsConstructor;
import org.example.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class ServiceFilter {
    private final CriterionSegment criterionSegment;

    /**
     * Убираем вылет до текущего момента времени.
     * @param flightList = список перелетов
     * @param time = время для условия
     */
    public void flightsBeforeTheCurrentTime(List<Flight> flightList,
                                            LocalDateTime time) {
        for (Flight f : flightList) {
            if (criterionSegment.flightsBeforeTheCurrentTime(f.getSegments(), time)) {
                System.out.println("Перелет не соответствует условиям фильтра");
            } else System.out.println(f);
        }
    }


    /**
     * Убираем сегменты с датой прилёта раньше даты вылета.
     * @param flightList = список перелетов
     */
    public void arrivalDateBeforeDepartureDate(List<Flight> flightList) {
        for (Flight f : flightList) {
            if (criterionSegment.arrivalDateBeforeDepartureDate(f.getSegments())) {
                System.out.println("Перелет не соответствует условиям фильтра");
            }else System.out.println(f);
        }
    }

    /**
     * Перелеты, где общее время, проведённое на земле,
     * превышает два часа (время на земле — это интервал между прилётом одного сегмента
     * и вылетом следующего за ним).
     * @param flightList = список перелетов
     * @param x = интересующий интервал времени
     */
public void theTimeSpentOnEarthExceedsXHours(List<Flight> flightList, int x) {
    for (Flight f : flightList) {
        if (criterionSegment.theTimeSpentOnEarthExceedsXHours(f.getSegments(), x)) {
            System.out.println("Перелет не соответствует условиям фильтра");
        }else System.out.println(f);
    }
}

}

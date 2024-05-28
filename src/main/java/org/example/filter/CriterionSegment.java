package org.example.filter;

import lombok.NoArgsConstructor;
import org.example.model.Segment;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
public class CriterionSegment {

    /**
     * Убираем вылет до текущего момента времени.
     * @param segments = список сегментов
     * @param time = время для условия
     * @return = boolean
     */
    public boolean flightsBeforeTheCurrentTime(List<Segment> segments, LocalDateTime time) {
        return segments.get(0).getDepartureDate().isBefore(time);
    }


    /**
     * Убираем сегменты с датой прилёта раньше даты вылета.
     * @param segments = список сегментов
     * @return = boolean
     */
    public boolean arrivalDateBeforeDepartureDate(List<Segment> segments) {
        for (Segment segment : segments) {
            if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Перелеты, где общее время, проведённое на земле,
     * превышает два часа (время на земле — это интервал между прилётом одного сегмента
     * и вылетом следующего за ним).
     * @param segments = список сегментов
     * @param x = интересующий интервал времени
     * @return = boolean
     */
    public boolean theTimeSpentOnEarthExceedsXHours(List<Segment> segments, int x) {
        int minute = x * 60 + 1;
        if (segments.size() > 1) {
            for (int i = 1; i < segments.size(); i++) {
                LocalDateTime srav = segments.get(i).getDepartureDate().minusMinutes(minute);
                if (segments.get(i - 1).getArrivalDate().isBefore(srav)) {
                    return true;
                }
            }
        }
        return false;
    }
}

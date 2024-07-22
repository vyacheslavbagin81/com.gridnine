package org.example.filter;

import org.example.model.Flight;
import org.example.model.Segment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ServiceFilterTest {

    @Mock
    private CriterionSegment criterionSegment;

    private ServiceFilter serviceFilter;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        serviceFilter = new ServiceFilter(criterionSegment);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testFlightsBeforeTheCurrentTime() {
        LocalDateTime time = LocalDateTime.now();
        Flight flight1 = mock(Flight.class);
        Flight flight2 = mock(Flight.class);
        List<Flight> flightList = Arrays.asList(flight1, flight2);

        Segment segment1 = mock(Segment.class);
        Segment segment2 = mock(Segment.class);
        List<Segment> segments1 = Collections.singletonList(segment1);
        List<Segment> segments2 = Collections.singletonList(segment2);

        when(flight1.getSegments()).thenReturn(segments1);
        when(flight2.getSegments()).thenReturn(segments2);

        when(criterionSegment.flightsBeforeTheCurrentTime(segments1, time)).thenReturn(false);
        when(criterionSegment.flightsBeforeTheCurrentTime(segments2, time)).thenReturn(true);

        serviceFilter.flightsBeforeTheCurrentTime(flightList, time);

        verify(criterionSegment).flightsBeforeTheCurrentTime(segments1, time);
        verify(criterionSegment).flightsBeforeTheCurrentTime(segments2, time);

        String expectedOutput = flight1 + System.lineSeparator() +
                "Перелет не соответствует условиям фильтра" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testArrivalDateBeforeDepartureDate() {
        Flight flight1 = mock(Flight.class);
        Flight flight2 = mock(Flight.class);
        List<Flight> flightList = Arrays.asList(flight1, flight2);

        List<Segment> segments1 = Collections.singletonList(mock(Segment.class));
        List<Segment> segments2 = Collections.singletonList(mock(Segment.class));

        when(flight1.getSegments()).thenReturn(segments1);
        when(flight2.getSegments()).thenReturn(segments2);

        when(criterionSegment.arrivalDateBeforeDepartureDate(segments1)).thenReturn(false);
        when(criterionSegment.arrivalDateBeforeDepartureDate(segments2)).thenReturn(true);

        serviceFilter.arrivalDateBeforeDepartureDate(flightList);

        verify(criterionSegment).arrivalDateBeforeDepartureDate(segments1);
        verify(criterionSegment).arrivalDateBeforeDepartureDate(segments2);

        String expectedOutput = flight1 + System.lineSeparator() +
                "Перелет не соответствует условиям фильтра" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testTheTimeSpentOnEarthExceedsXHours() {
        int hours = 2;
        Flight flight1 = mock(Flight.class);
        Flight flight2 = mock(Flight.class);
        List<Flight> flightList = Arrays.asList(flight1, flight2);

        List<Segment> segments1 = Collections.singletonList(mock(Segment.class));
        List<Segment> segments2 = Collections.singletonList(mock(Segment.class));

        when(flight1.getSegments()).thenReturn(segments1);
        when(flight2.getSegments()).thenReturn(segments2);

        when(criterionSegment.theTimeSpentOnEarthExceedsXHours(segments1, hours)).thenReturn(false);
        when(criterionSegment.theTimeSpentOnEarthExceedsXHours(segments2, hours)).thenReturn(true);

        serviceFilter.theTimeSpentOnEarthExceedsXHours(flightList, hours);

        verify(criterionSegment).theTimeSpentOnEarthExceedsXHours(segments1, hours);
        verify(criterionSegment).theTimeSpentOnEarthExceedsXHours(segments2, hours);

        String expectedOutput = flight1 + System.lineSeparator() +
                "Перелет не соответствует условиям фильтра" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}
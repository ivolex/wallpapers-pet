package org.pet.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pet.model.RoomDimensions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculationServiceTest {

    @Mock
    InputReader readerMock;

    @Test
    void totalForOneRoomDimensions(){
        RoomDimensions dim = new RoomDimensions(3, 5, 8);

        CalculationService service = new CalculationService(null);

        assertEquals(161, service.calculate(dim));
    }

    @Test
    void totalSquareFeet(){
        when(readerMock.fetchDimensions()).thenReturn(dimensionsStub());

        CalculationService service = new CalculationService(readerMock);

        assertEquals(79, service.totalSquareFeet());
    }

    @Test
    void totalCubic(){
        when(readerMock.fetchDimensions()).thenReturn(dimensionsStubWithCubic());

        CalculationService service = new CalculationService(readerMock);

        assertEquals(3, service.totalCubic().size());
        assertEquals(57, service.totalCubic().keySet().stream().findFirst().get());
    }

    @Test
    void totalTwinsWithNoTwins(){
        when(readerMock.fetchDimensions()).thenReturn(dimensionsStub());

        CalculationService service = new CalculationService(readerMock);

        assertEquals(0, service.totalTwins().size());
    }

    @Test
    void totalTwinsWithTwins(){
        when(readerMock.fetchDimensions()).thenReturn(dimensionsStubWithCubic());

        CalculationService service = new CalculationService(readerMock);

        assertEquals(3, service.totalTwins().size());
    }

    private List<RoomDimensions> dimensionsStub(){
        return List.of(new RoomDimensions(2, 2, 3),
                new RoomDimensions(1,1, 2),
                new RoomDimensions(3,2, 2));
    }

    private List<RoomDimensions> dimensionsStubWithCubic(){
        return List.of(new RoomDimensions(3, 3, 3),
                new RoomDimensions(3, 3, 3),

                new RoomDimensions(1,1, 2),
                new RoomDimensions(1,1, 2),

                new RoomDimensions(2,2, 2),
                new RoomDimensions(2,2, 2),
                new RoomDimensions(2,2, 2),
                new RoomDimensions(2,2, 2),

                new RoomDimensions(1,1, 1),


                new RoomDimensions(3,2, 2));
    }
}
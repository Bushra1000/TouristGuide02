package com.example.touristguide02.Controller;

import com.example.touristguide02.Service.TouristService;
import com.example.touristguide02.model.TouristAttraction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ExtendedModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TouristControllerTest {

    private TouristService touristService;
    private TouristController touristController;

    @BeforeEach
    void setUp() {
        touristService = mock(TouristService.class);
        touristController = new TouristController(touristService);
    }

    @Test
    void getAllAttractions() {
        List<TouristAttraction> attractions = List.of(
                new TouristAttraction("Brønshøj Torv", "Torv i Brønshøj", List.of("byliv"), "København"),
                new TouristAttraction("Utterslev Mose", "Naturområde", List.of("natur"), "København"),
                new TouristAttraction("Brønshøj Bibliotek", "Lokalt bibliotek", List.of("kultur"), "København"),
                new TouristAttraction("Brønshøj Kirke", "Kirke i Brønshøj", List.of("historie"), "København"),
                new TouristAttraction("Bellahøj Friluftsscene", "Friluftsscene", List.of("kultur"), "København")
        );

        when(touristService.getAllAttractions()).thenReturn(attractions);

        ExtendedModelMap model = new ExtendedModelMap();

        String result = touristController.getAllAtrractions(model);

        assertEquals("attractionList", result);
        assertEquals(attractions, model.getAttribute("attractions"));
    }

    @Test
    void getAttractionByName() {
        TouristAttraction attraction = new TouristAttraction(
                "Brønshøj Torv", "Torv i Brønshøj", List.of("byliv"), "København"
        );

        when(touristService.getAttractionByName("Brønshøj Torv"))
                .thenReturn(attraction);

        var response = touristController.getAttractionByName("Brønshøj Torv");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Brønshøj Torv", response.getBody().getName());
    }

    @Test
    void addAttraction() {
        TouristAttraction attraction = new TouristAttraction(
                "Utterslev Mose", "Naturområde", List.of("natur"), "København"
        );

        when(touristService.addAttraction(attraction)).thenReturn(attraction);

        var response = touristController.addAttraction(attraction);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Utterslev Mose", response.getBody().getName());
    }

    @Test
    void updateAttraction() {
        TouristAttraction attraction = new TouristAttraction(
                "Brønshøj Bibliotek", "Lokalt bibliotek", List.of("kultur"), "København"
        );

        var response = touristController.updateAttraction(attraction);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(touristService).updateAttraction(attraction);
    }

    @Test
    void deleteAttraction() {
        var response = touristController.deleteAttraction("Brønshøj Kirke");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(touristService).deleteAttraction("Brønshøj Kirke");
    }
}
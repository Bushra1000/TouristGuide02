package com.example.touristguide02.Service;

import com.example.touristguide02.model.TouristAttraction;
import com.example.touristguide02.repository.TouristRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TouristServiceTest {

    @Mock
    private TouristRepository touristRepository;

    @InjectMocks
    private TouristService touristService;

    @Test
    void getAllAttractions_returnsListFromRepository() {
        TouristAttraction torv = new TouristAttraction(
                "Brønshøj Torv", "Et torv i Brønshøj",
                List.of("Torv"), "Brønshøj");
        when(touristRepository.getAllattractions()).thenReturn(List.of(torv));

        List<TouristAttraction> result = touristService.getAllAttractions();

        assertEquals(1, result.size());
        assertEquals("Brønshøj Torv", result.get(0).getName());
    }

    @Test
    void addAttraction_callsRepositoryAndReturnsAttraction() {
        TouristAttraction mose = new TouristAttraction(
                "Utterslev Mose", "Et naturområde",
                List.of("Natur", "Gratis"), "Brønshøj");
        when(touristRepository.addAttraction(mose)).thenReturn(mose);

        TouristAttraction result = touristService.addAttraction(mose);

        assertEquals("Utterslev Mose", result.getName());
        verify(touristRepository).addAttraction(mose);
    }

    @Test
    void updateAttraction_callsRepository() {
        TouristAttraction kirke = new TouristAttraction(
                "Brønshøj Kirke", "En kirke i Brønshøj",
                List.of("Kirke"), "Brønshøj");

        touristService.updateAttraction(kirke);

        verify(touristRepository).updateAttraction(kirke);
    }

    @Test
    void getCities_returnsCitiesFromRepository() {
        when(touristRepository.getCities()).thenReturn(List.of("Brønshøj"));

        assertEquals(List.of("Brønshøj"), touristService.getCities());
    }

    @Test
    void getTags_returnsTagsFromRepository() {
        when(touristRepository.getTags()).thenReturn(List.of("Natur", "Gratis"));

        assertEquals(List.of("Natur", "Gratis"), touristService.getTags());
    }
}


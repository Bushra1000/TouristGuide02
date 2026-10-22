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
}


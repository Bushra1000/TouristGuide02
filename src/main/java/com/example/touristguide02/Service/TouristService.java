package com.example.touristguide02.Service;

import com.example.touristguide02.model.TouristAttraction;
import com.example.touristguide02.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


    @Service
    public class TouristService {

        private final TouristRepository touristRepository;

        public TouristService(TouristRepository touristRepository) {
            this.touristRepository = touristRepository;
        }

        public List <TouristAttraction> getAllAttractions() {
            return touristRepository.getAllattractions();
        }

        public TouristAttraction getAttractionByName(String name) {
            return touristRepository.getAttractionsByname(name);
        }

        public TouristAttraction addAttraction(TouristAttraction attraction) {
            return touristRepository.addAttraction(attraction);

        }
        public void updateAttraction( TouristAttraction updateAttraction){
            touristRepository.updateAttraction(updateAttraction);

        }
        public void deleteAttraction(String name){
            touristRepository.deleteAttraction(name);
        }

        public List<String> getCities(){
            return touristRepository.getCities();
        }

        public List<String>getTags(){
            return touristRepository.getTags();
        }
















    }




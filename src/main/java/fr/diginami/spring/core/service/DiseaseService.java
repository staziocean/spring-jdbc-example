package fr.diginami.spring.core.service;

import fr.diginami.spring.core.model.Disease;
import fr.diginami.spring.core.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Transactional
    public void createDiseases(List<Disease> diseases) {
        for (Disease disease : diseases) {
            diseaseRepository.createDisease(disease);
        }
    }
}

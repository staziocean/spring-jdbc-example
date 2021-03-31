package fr.diginami.spring.core;

import fr.diginami.spring.core.model.Disease;
import fr.diginami.spring.core.repository.DiseaseRepository;
import fr.diginami.spring.core.service.DiseaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(App.class);

        DiseaseRepository diseaseRepository = appContext.getBean(DiseaseRepository.class);
        DiseaseService diseaseService = appContext.getBean(DiseaseService.class);

        // Suppression pour pouvoir réexécuer le main
        diseaseRepository.deleteDisease("Diphtérie");
        diseaseRepository.deleteDisease("Grippe porcine");
        diseaseRepository.deleteDisease("Tuberculose");
        diseaseRepository.deleteDisease("Rubéole");
        diseaseRepository.deleteDisease("Rougeole");

        System.out.println("");
        System.out.println("Number of diseases starting with C :");
        System.out.println(diseaseRepository.countDiseaseStartingWith("C"));


        System.out.println("");
        System.out.println("Disease name having lethality > 1");
        System.out.println(diseaseRepository.getDiseaseNameHavingLethalitySuperiorTo(1));


        System.out.println("");
        System.out.println("Diseases having contagion inferior < 50");
        System.out.println(diseaseRepository.getDiseasesHavingContagionInferiorTo(60));


        System.out.println("");
        System.out.println("Delete H1N1 and find all");
        diseaseRepository.deleteDisease("H1N1");
        System.out.println(diseaseRepository.findAll());


        System.out.println("");
        System.out.println("Create new disease and find all");
        diseaseRepository.createDisease(new Disease("H1N1", 12, 10));
        System.out.println(diseaseRepository.findAll());


        System.out.println("");
        System.out.println("Update Tetanos and find all");
        diseaseRepository.updateDisease("Tetanos", new Disease("Tetanos", 15, 4));
        System.out.println(diseaseRepository.findAll());


        System.out.println("");
        System.out.println("Insert multiple diseases to test @Transactional behavior");
        List<Disease> diseaseList = new ArrayList<>();
        diseaseList.add(new Disease("Diphtérie", 49, 14));
        diseaseList.add(new Disease("Grippe porcine", 7, 43));
        diseaseList.add(new Disease("Tuberculose", 30, 95));
        diseaseService.createDiseases(diseaseList);
        System.out.println(diseaseRepository.findAll());

        System.out.println("");
        System.out.println("Insert multiple diseases to test @Transactional behavior");
        List<Disease> otherDiseaseList = new ArrayList<>();
        otherDiseaseList.add(new Disease("Rubéole", 89, 11));
        otherDiseaseList.add(new Disease("Rougeole", 31, 57));
        otherDiseaseList.add(new Disease("Tetanos", 15, 4));
        diseaseService.createDiseases(otherDiseaseList);
        System.out.println(diseaseRepository.findAll());
    }
}

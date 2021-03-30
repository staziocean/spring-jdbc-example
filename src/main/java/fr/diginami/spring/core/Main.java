package fr.diginami.spring.core;

import fr.diginami.spring.core.repository.DiseaseRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(App.class);

        DiseaseRepository diseaseRepository = appContext.getBean(DiseaseRepository.class);

        System.out.println("");
        System.out.println("Number of diseases starting with C :");
        System.out.println(diseaseRepository.countDiseaseStartingWith("C"));


        System.out.println("");
        System.out.println("Disease name having lethality > 1");
        System.out.println(diseaseRepository.getDiseaseNameHavingLethalitySuperiorTo(1));


        System.out.println("");
        System.out.println("Diseases having contagion inferior < 50");
        System.out.println(diseaseRepository.getDiseasesHavingContagionInferiorTo(60));
    }
}

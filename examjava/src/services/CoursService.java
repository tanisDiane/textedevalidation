package services;

import java.util.List;
import repositories.CoursRepository;
import entities.Cours;

public class CoursService {
    private CoursRepository coursRepository;

    public CoursService(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    public void ajouterCours(Cours cours) {
        coursRepository.ajouterCours(cours);
    }

    public List<Cours> listerTousLesCours() {
        return coursRepository.listerTousLesCours();
    }

    public List<Cours> listerCoursParModuleEtProfesseur(int moduleId, int professeurId) {
        return coursRepository.listerCoursParModuleEtProfesseur(moduleId, professeurId);
    }
}

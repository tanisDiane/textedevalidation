package services;

import java.util.List;
import repositories.ProfesseurRepository;
import entities.Professeur;

public class ProfesseurService {
    private ProfesseurRepository professeurRepository;

    public ProfesseurService(ProfesseurRepository professeurRepository) {
        this.professeurRepository = professeurRepository;
    }

    public void ajouterProfesseur(Professeur professeur) {
        professeurRepository.ajouterProfesseur(professeur);
    }

    public List<Professeur> listerProfesseurs() {
        return professeurRepository.listerProfesseurs();
    }
}

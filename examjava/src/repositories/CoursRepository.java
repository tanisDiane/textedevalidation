package repositories;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import entities.Cours;
import entities.Professeur;
import core.Database;

public class CoursRepository extends Database {
    protected final String SQL_INSERT = "INSERT INTO cours (date, heureDebut, heureFin, professeur_id, module_id) VALUES (?, ?, ?, ?, ?)";
    protected final String SQL_FIND_ALL = "SELECT * FROM cours";
    protected final String SQL_FIND_BY_MODULE_AND_PROFESSOR = "SELECT * FROM cours WHERE module_id = ? AND professeur_id = ?";

    public void ajouterCours(Cours cours) {
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setDate(1, Date.valueOf(cours.getDate()));
            statement.setTime(2, Time.valueOf(cours.getHeureDebut()));
            statement.setTime(3, Time.valueOf(cours.getHeureFin()));
            statement.setInt(4, cours.getProfesseur().getId());
            statement.setInt(5, cours.getModule().getId());
            executeUpdate();
            fermerConnexion();
            System.out.println("Cours ajouté avec succès.");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du cours: " + e.getMessage());
        }
    }

    public List<Cours> listerTousLesCours() {
        List<Cours> coursList = new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs = executeSelect();
            while (rs.next()) {
                Cours cours = new Cours();
                // Remplir les détails du cours depuis le ResultSet
                cours.setDate(rs.getDate("date").toLocalDate());
                cours.setHeureDebut(rs.getTime("heureDb").toLocalTime());
                cours.setHeureFin(rs.getTime("heureFin").toLocalTime());
                
                coursList.add(cours);
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des cours: " + e.getMessage());
        }
        return coursList;
    }

    public List<Cours> listerCoursParModuleEtProfesseur(int moduleId, int professeurId) {
        List<Cours> coursList = new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_BY_MODULE_AND_PROFESSOR);
            statement.setInt(1, moduleId);
            statement.setInt(2, professeurId);
            ResultSet rs = executeSelect();
            while (rs.next()) {
                Cours cours = new Cours();
                // Remplir les détails du cours depuis le ResultSet
                coursList.add(cours);
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des cours pour le module et le professeur spécifiés: " + e.getMessage());
        }
        return coursList;
    }
}

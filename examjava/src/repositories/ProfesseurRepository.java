package repositories;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entities.Professeur;
import core.Database;

public class ProfesseurRepository extends Database {
    protected final String SQL_INSERT = "INSERT INTO professeurs (nom, prenom, tel) VALUES (?, ?, ?)";
    protected final String SQL_FIND_ALL = "SELECT * FROM professeurs";

    public void ajouterProfesseur(Professeur professeur) {
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setString(1, professeur.getNom());
            statement.setString(2, professeur.getPrenom());
            statement.setString(3, professeur.getTel());
            statement.executeUpdate(); // Utilisation de executeUpdate() pour une mise à jour
            fermerConnexion();
            System.out.println("Professeur ajouté avec succès.");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du professeur: " + e.getMessage());
        }
    }

    public List<Professeur> listerProfesseurs() {
        List<Professeur> professeurs = new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs = executeSelect();
            while (rs.next()) {
                Professeur professeur = new Professeur();
                professeur.setId(rs.getInt("id"));
                professeur.setNom(rs.getString("nom"));
                professeur.setPrenom(rs.getString("prenom"));
                professeur.setTel(rs.getString("tel"));
                professeurs.add(professeur);
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des professeurs: " + e.getMessage());
        }
        return professeurs;
    }
}

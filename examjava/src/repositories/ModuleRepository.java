package repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entities.Module;
import core.Database;

public class ModuleRepository extends Database {
    protected final String SQL_INSERT = "INSERT INTO modules (nom) VALUES (?)";
    protected final String SQL_FIND_ALL = "SELECT * FROM modules";

    public void ajouterModule(Module module) {
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setString(1, module.getNom());
            executeUpdate();
            fermerConnexion();
            System.out.println("Module ajouté avec succès.");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du module: " + e.getMessage());
        }
    }

    public List<Module> listerModules() {
        List<Module> modules = new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs = executeSelect();
            while (rs.next()) {
                Module module = new Module();
                module.setId(rs.getInt("id"));
                module.setNom(rs.getString("nom"));
                modules.add(module);
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des modules: " + e.getMessage());
        }
        return modules;
    }
}

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import entities.Module;
import entities.Cours;
import services.ModuleService;
import services.CoursService;
import repositories.ModuleRepository;
import repositories.CoursRepository;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static ModuleService moduleService = new ModuleService(new ModuleRepository());
    private static CoursService coursService = new CoursService(new CoursRepository());

    public static void main(String[] args) {
        int choix;
        do {
            System.out.println("\n===================================================");
            System.out.println("==================== MENU =========================");
            System.out.println("===================================================");
            System.out.println("1- Ajouter un Module");
            System.out.println("2- Lister les Modules");
            System.out.println("3- Créer un cours");
            System.out.println("4- Lister tous les cours");
            System.out.println("5- Lister les cours d’un module et d’un professeur");
            System.out.println("0- Quitter");
            System.out.println("===================================================");

            System.out.print("Faites votre choix: ");
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                scanner.nextLine();
                System.out.println("\n===================================================");
                System.out.print("Entrer le nom du module: ");
                String nomModule = scanner.nextLine();
                Module module = new Module(nomModule);
                moduleService.ajouterModule(module);
                System.out.println("Module ajouté avec succès.");
                break;
            

               case 2:
                System.out.println("\n===================================================");
                System.out.println("Liste des Modules:");
                List<Module> modules = moduleService.listerModules();
                if (modules.isEmpty()) {
                    System.out.println("Aucun module trouvé.");
                } else {
                    for (Module module : modules) {
                        System.out.println(module.getId() + ". " + module.getNom());
                    }
                }
                break;


                case 3:
                scanner.nextLine(); 
                System.out.println("\n===================================================");
                System.out.print("Entrer le nom du cours: ");
                String nomCours = scanner.nextLine();

                System.out.print("Entrer la date du cours (YYYY-MM-DD): ");
                String dateCoursStr = scanner.nextLine();
                LocalDate dateCours = LocalDate.parse(dateCoursStr);

                System.out.print("Entrer l'heure de début du cours (HH:MM): ");
                String heureDebutStr = scanner.nextLine();
                LocalTime heureDebut = LocalTime.parse(heureDebutStr);

                System.out.print("Entrer l'heure de fin du cours (HH:MM): ");
                String heureFinStr = scanner.nextLine();
                LocalTime heureFin = LocalTime.parse(heureFinStr);

                System.out.print("Entrer l'ID du professeur du cours: ");
                int idProfesseur = scanner.nextInt();

                System.out.print("Entrer l'ID du module du cours: ");
                int idModule = scanner.nextInt();

                Cours cours = new Cours(nomCours, dateCours, heureDebut, heureFin, idProfesseur, idModule);
                coursService.creerCours(cours);
                System.out.println("Cours créé avec succès.");
                break;

                case 4:
                System.out.println("\n===================================================");
                System.out.println("Liste de tous les cours:");
                List<Cours> tousLesCours = coursService.listerTousLesCours();
                if (tousLesCours.isEmpty()) {
                    System.out.println("Aucun cours trouvé.");
                } else {
                    for (Cours cours : tousLesCours) {
                        System.out.println(cours.toString());
                    }
                }
                break;
        

                case 5:
                System.out.print("Entrer l'ID du module : ");
                int idModule = scanner.nextInt();
                System.out.print("Entrer l'ID du professeur : ");
                int idProfesseur = scanner.nextInt();
                
                List<Cours> coursModuleProf = coursService.listerCoursParModuleEtProfesseur(idModule, idProfesseur);
                if (coursModuleProf.isEmpty()) {
                    System.out.println("Aucun cours trouvé pour ce module et ce professeur.");
                } else {
                    System.out.println("\nListe des cours pour le module " + idModule + " et le professeur " + idProfesseur + " :");
                    for (Cours cours : coursModuleProf) {
                        System.out.println(cours.toString());
                    }
                }
                break;
            
                case 0:
                    System.out.println("===================================================");
                    System.out.println("=                 Au revoir !                      =");
                    System.out.println("===================================================");
                    break;

                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }
        } while (choix != 0);
    }


}

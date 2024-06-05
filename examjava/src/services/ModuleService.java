package services;

import java.util.List;
import repositories.ModuleRepository;
import entities.Module;

public class ModuleService {
    private ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public void ajouterModule(Module module) {
        moduleRepository.ajouterModule(module);
    }

    public List<Module> listerModules() {
        return moduleRepository.listerModules();
    }
}

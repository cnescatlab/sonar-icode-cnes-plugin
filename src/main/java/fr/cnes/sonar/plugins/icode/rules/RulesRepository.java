package fr.cnes.sonar.plugins.icode.rules;

import java.util.List;
import java.util.ArrayList;
import org.sonar.api.server.rule.RulesDefinition.NewRule;

public class RulesRepository {
    private static RulesRepository instance = null;

    private List<NewRule> f77Rules;
    private List<NewRule> f90Rules;

    private RulesRepository() {
        this.f77Rules = new ArrayList<>();
        this.f90Rules = new ArrayList<>();
    }

    public static RulesRepository getInstance() {
        if (instance == null) {
            instance = new RulesRepository();
        }
        return instance;
    }

    public List<NewRule> getF77Rules() {
        return f77Rules;
    }

    public List<NewRule> getF90Rules() {
        return f90Rules;
    }

    public void setF77Rules(List<NewRule> f77Rules) {
        this.f77Rules = f77Rules;
    }

    public void setF90Rules(List<NewRule> f90Rules) {
        this.f90Rules = f90Rules;
    }
}

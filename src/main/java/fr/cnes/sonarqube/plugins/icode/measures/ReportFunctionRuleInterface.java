package fr.cnes.sonarqube.plugins.icode.measures;

/**
 * Expected services for a rule function
 * 
 * @author Cyrille
 *
 */
public interface ReportFunctionRuleInterface extends ReportModuleRuleInterface {

	String getFunction();
	String getLine();
}

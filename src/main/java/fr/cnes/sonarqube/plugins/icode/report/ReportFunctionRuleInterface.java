package fr.cnes.sonarqube.plugins.icode.report;

import fr.cnes.sonarqube.plugins.icode.report.ReportModuleRuleInterface;

/**
 * Expected services for a rule function
 * 
 * @author Cyrille FRANCOIS
 *
 */
public interface ReportFunctionRuleInterface extends ReportModuleRuleInterface, ErrorInterface {

	String getFunction();
	String getLine();
}

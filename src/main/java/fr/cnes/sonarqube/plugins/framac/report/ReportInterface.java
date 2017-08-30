package fr.cnes.sonarqube.plugins.framac.report;

public interface ReportInterface {
	
	ReportModuleRuleInterface getModuleCyclomaticMeasure();

	ReportFunctionRuleInterface[] getCyclomaticMeasureByFunction();

}

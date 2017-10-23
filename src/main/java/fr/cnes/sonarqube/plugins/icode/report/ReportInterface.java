package fr.cnes.sonarqube.plugins.icode.measures;

/**
 * Expected report services for a ICode report
 * 
 * @author Cyrille FRANCOIS
 *
 */
public interface ReportInterface {

	boolean isF77();
	boolean isF90();

	ReportModuleRuleInterface getModuleCyclomaticMeasure();
	ReportFunctionRuleInterface[] getCyclomaticMeasureByFunction();
	
	ReportModuleRuleInterface getModuleLinesOfCodeMeasure();
	ReportFunctionRuleInterface[] getLinesOfCodeMeasureByFunction();
	
	ReportModuleRuleInterface getModuleNestingMeasure();
	ReportFunctionRuleInterface[] getNestingMeasureByFunction();
	
	ReportModuleRuleInterface getModuleRatioCommentMeasure();
	ReportFunctionRuleInterface[] getRatioCommentMeasureByFunction();
	

}

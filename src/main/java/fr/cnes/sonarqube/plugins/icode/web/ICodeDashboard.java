package fr.cnes.sonarqube.plugins.icode.web;

import org.sonar.api.web.page.Context;
import org.sonar.api.web.page.Page;
import org.sonar.api.web.page.Page.Scope;
import org.sonar.api.web.page.PageDefinition;

public class ICodeDashboard implements PageDefinition {

	static final String PAGE_NAME_I_CODE_CNES_METRICS_SUMMARY = "ICode CNES metrics summary";
	static final String PAGE_KEY_ICODE_METRICS_SUMMARY = "icode/icode_metrics_summary";

	protected Page buildMetricsSummaryPage(){
		  return Page.builder(PAGE_KEY_ICODE_METRICS_SUMMARY)
			        .setName(PAGE_NAME_I_CODE_CNES_METRICS_SUMMARY)
			        .setScope(Scope.COMPONENT).build();
	  }
	
	@Override
	  public void define(Context context) {
	    context
	      .addPage(buildMetricsSummaryPage());
	  }


}

package fr.cnes.sonarqube.plugins.icode.web;

import org.sonar.api.web.page.Context;
import org.sonar.api.web.page.Page;
import org.sonar.api.web.page.Page.Scope;
import org.sonar.api.web.page.PageDefinition;

public class ICodeDashboard implements PageDefinition {

	  @Override
	  public void define(Context context) {
	    context
	      .addPage(Page.builder("icode/icode_metrics_summary")
	        .setName("ICode CNES metrics summary")
	        .setScope(Scope.COMPONENT).build());
	  }


}

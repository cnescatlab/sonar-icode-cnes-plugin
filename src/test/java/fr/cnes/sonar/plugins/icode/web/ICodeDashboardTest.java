package fr.cnes.sonar.plugins.icode.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.sonar.api.Plugin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sonar.api.web.page.Page;
import org.sonar.api.web.page.Page.Scope;

import fr.cnes.sonar.plugins.icode.ICodePlugin;

public class ICodeDashboardTest {

	@BeforeClass
	public static void beforeClass(){
		Plugin.Context context = mock(Plugin.Context.class);
		ICodePlugin plugin = new ICodePlugin();
		plugin.define(context);
	}
	
	@Test
	public void given_iCodeDashboard_when_buildMetricsSummaryPage_then_dashboardPage() {
		ICodeDashboard iCodeDashboard = new ICodeDashboard();
		Page actual = iCodeDashboard.buildMetricsSummaryPage();
		assertEquals(ICodeDashboard.PAGE_KEY_ICODE_METRICS_SUMMARY, actual.getKey());
		assertEquals(ICodeDashboard.PAGE_NAME_I_CODE_CNES_METRICS_SUMMARY, actual.getName());
		assertEquals(Scope.COMPONENT, actual.getScope());
	}
	
	@Test
	public void given_iCodeDashboard_when_callDefineWithNullContext_then_onlyOnePage(){
		org.sonar.api.web.page.Context context = new org.sonar.api.web.page.Context();
		ICodeDashboard iCodeDashboard = new ICodeDashboard();
		iCodeDashboard.define(context);
		assertEquals(1, context.getPages().size());
	}

}

package fr.cnes.sonarqube.plugins.icode;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.sonar.api.Plugin;

import fr.cnes.sonarqube.plugins.icode.rules.ICodeRulesDefinition;


public class ICodePluginTest {

	@Test
	public void testExtension() {
		Plugin.Context context = mock(Plugin.Context.class);
		ICodePlugin plugin = new ICodePlugin();
		plugin.define(context);
		
		verify(context).addExtension(ICodeRulesDefinition.class);
	}

}

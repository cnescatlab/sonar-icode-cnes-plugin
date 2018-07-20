/*
 * This file is part of sonar-icode-cnes-plugin.
 *
 * sonar-icode-cnes-plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * sonar-icode-cnes-plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with sonar-icode-cnes-plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.cnes.sonar.plugins.icode.settings;

import org.junit.Assert;
import org.junit.Test;
import org.sonar.api.config.PropertyDefinition;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ICodePluginPropertiesTest {

	@Test
	public void test_plugin_properties_definition() {
		List<PropertyDefinition> actual = ICodePluginProperties.getProperties();
		assertEquals(4, actual.size());
		PropertyDefinition codeSuffix = actual.get(0);
		Assert.assertEquals(ICodePluginProperties.ICODE_NAME, codeSuffix.category());
		assertEquals(ICodePluginProperties.SHELL_SUFFIX_KEY, codeSuffix.key());
		assertEquals(ICodePluginProperties.SHELL_SUFFIX_DEFAULT, codeSuffix.defaultValue());
		PropertyDefinition reportPath = actual.get(3);
		assertEquals(ICodePluginProperties.ICODE_NAME, reportPath.category());
		assertEquals(ICodePluginProperties.REPORT_PATH_KEY, reportPath.key());
		assertEquals(ICodePluginProperties.REPORT_PATH_DEFAULT, reportPath.defaultValue());
	}

}

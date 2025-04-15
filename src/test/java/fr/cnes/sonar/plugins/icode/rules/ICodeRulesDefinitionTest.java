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
package fr.cnes.sonar.plugins.icode.rules;

import org.junit.Assert;
import org.junit.Test;
import org.sonar.api.server.rule.RulesDefinition;

public class ICodeRulesDefinitionTest {

	@Test
	public void test_creation_of_repositories_and_rules() {
		RulesDefinition.Context context = new RulesDefinition.Context();
		Assert.assertEquals(0, context.repositories().size());
		new ICodeRulesDefinition().define(context);
		Assert.assertEquals(2, context.repositories().size());
		Assert.assertEquals(66, context.repository("f77-rules").rules().size());
		Assert.assertEquals(72, context.repository("f90-rules").rules().size());
	}

	@Test
	public void test_creation_of_repositories_and_rules_for_bad_language() {
		RulesDefinition.Context context = new RulesDefinition.Context();
		Assert.assertEquals(0, context.repositories().size());
		new ICodeRulesDefinition(){
			@Override
			public void define(Context context) {
				createFortranRepository(context, "bad", "bad-rules", "bad_file");
			}
		}.define(context);
		Assert.assertEquals(1, context.repositories().size());
		Assert.assertEquals(0, context.repository("bad-rules").rules().size());
	}

}

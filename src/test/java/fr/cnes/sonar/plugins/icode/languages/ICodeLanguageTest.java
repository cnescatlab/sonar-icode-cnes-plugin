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
package fr.cnes.sonar.plugins.icode.languages;

import fr.cnes.sonar.plugins.icode.settings.ICodePluginProperties;
import org.junit.Test;
import org.mockito.Mockito;
import org.sonar.api.config.Configuration;

import static org.junit.Assert.assertArrayEquals;

public class ICodeLanguageTest {

    @Test
    public void given_settings_when_getFileSuffixes_then_settings() {
        String[] expected = new String[]{"*.c", "*.i"};
        Configuration settings = Mockito.mock(org.sonar.api.config.Configuration.class);
        Mockito.when(settings.getStringArray(ICodePluginProperties.CODE_SUFFIX_KEY))
                .thenReturn(new String[]{" " + expected[0] + " ", " " + expected[1] + " "});
        ICodeLanguage icodeLanguage = new ICodeLanguage(settings);
        assertArrayEquals(expected, icodeLanguage.getFileSuffixes());
    }

}

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
import org.junit.Assert;
import org.junit.Test;
import org.sonar.api.config.Configuration;
import org.sonar.api.config.internal.MapSettings;

import static org.junit.Assert.assertArrayEquals;

public class ICodeLanguagesTest {

    @Test
    public void test_given_settings_when_getFileSuffixes_then_settings() {
        Configuration settings = new MapSettings().asConfig();
        ShellLanguage shellLanguage = new ShellLanguage(settings);
        Fortran77Language fortran77Language = new Fortran77Language(settings);
        Fortran90Language fortran90Language = new Fortran90Language(settings);
        String[] expected = new String[]{".sh",".ksh",".bash"};
        assertArrayEquals(expected, shellLanguage.getFileSuffixes());
        expected = new String[]{".f",".f77",".for",".fpp",".ftn",".F",".F77",".FOR",".FPP",".FTN"};
        assertArrayEquals(expected, fortran77Language.getFileSuffixes());
        expected = new String[]{".f90",".F90"};
        assertArrayEquals(expected, fortran90Language.getFileSuffixes());
    }

    @Test
    public void test_strange_properties() {
        final String[] values = {
                "",
                "    ",
                "  , , ,, ,    ,",
                ","
        };

        for(final String value : values) {
            MapSettings map = new MapSettings();
            map.setProperty(ICodePluginProperties.SHELL_SUFFIX_KEY, value);
            Configuration settings = map.asConfig();

            ICodeLanguage l1 = new ShellLanguage(settings);

            Assert.assertEquals(3, l1.getFileSuffixes().length);
            Assert.assertEquals(".sh", l1.getFileSuffixes()[0]);
            Assert.assertEquals(".ksh", l1.getFileSuffixes()[1]);
            Assert.assertEquals(".bash", l1.getFileSuffixes()[2]);
        }
    }

    @Test
    public void test_equals_ans_hashcode() {
        Configuration settings = new MapSettings().asConfig();
        ICodeLanguage l1 = new ShellLanguage(settings);
        ICodeLanguage l2 = new Fortran77Language(settings);

        Assert.assertFalse(l1.equals(l2));
        Assert.assertFalse(l1.equals(new ShellLanguage(settings)));

        Assert.assertNotEquals(l1.hashCode(), l2.hashCode());
    }

}

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
        Fortran77Language fortran77Language = new Fortran77Language(settings);
        Fortran90Language fortran90Language = new Fortran90Language(settings);
        String[] expected = new String[]{".f",".f77",".for",".fpp",".ftn",".F",".F77",".FOR",".FPP",".FTN"};
        assertArrayEquals(expected, fortran77Language.getFileSuffixes());
        expected = new String[]{".f90",".F90"};
        assertArrayEquals(expected, fortran90Language.getFileSuffixes());
    }

    @Test
    public void test_get_file_suffixes_with_strange_language_suffixes() {
        final Configuration settings = new MapSettings()
                .setProperty("toto", ".notstrange,,strange,.,                 ")
                .asConfig();
        final ICodeLanguage strangeLanguage = new ICodeLanguage(settings, "strange", "Strange") {
            @Override
            public String getSuffixKey() {
                return "toto";
            }

            @Override
            public String getDefaultSuffixes() {
                return ".notstrange,,strange,.,                 ";
            }
        };
        String[] expected = new String[]{".notstrange","strange","."};
        String[] actual = strangeLanguage.getFileSuffixes();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void test_all_possibilities_with_filter_empty_strings() {
        String[] expected = new String[]{".notstrange","strange","."};
        String[] actual = ICodeLanguage.filterEmptyStrings(new String[]{".notstrange","","strange",".","       "});
        assertArrayEquals(expected, actual);
    }
}

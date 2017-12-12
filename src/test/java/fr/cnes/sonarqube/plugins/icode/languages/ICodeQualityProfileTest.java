package fr.cnes.sonarqube.plugins.icode.languages;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.sonar.api.profiles.RulesProfile;

public class ICodeQualityProfileTest {

	@Test
	public void test() {
		ICodeQualityProfile icodeQualityProfile = new ICodeQualityProfile();
		RulesProfile actual = icodeQualityProfile.createProfile(null);
		assertEquals(ICodeLanguage.KEY, actual.getLanguage());
		assertEquals(ICodeQualityProfile.I_CODE_RULES_PROFILE_NAME, actual.getName());
	}

}

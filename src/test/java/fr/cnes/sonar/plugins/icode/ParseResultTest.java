package fr.cnes.sonar.plugins.icode;

import fr.cnes.sonar.plugins.icode.model.AnalysisProject;
import fr.cnes.sonar.plugins.icode.model.XmlHandler;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

public class ParseResultTest {
    @Test
    public void parseIcodeOutputTest() {
        InputStream icodeOutput = getClass().getResourceAsStream("/result.res");
        AnalysisProject analysisProject = (AnalysisProject) XmlHandler.unmarshal(icodeOutput, AnalysisProject.class);


        Assert.assertNotNull(analysisProject);
        Assert.assertNotNull(analysisProject.getAnalysisRules().get(0));

        // Try to get Analysis information
        Assert.assertNotNull(analysisProject.getAnalysisInformations().getAuthor());

        // Try to get files information
        Assert.assertEquals(4, analysisProject.getAnalysisFiles().size());
        Assert.assertNotNull(analysisProject.getAnalysisFiles().get(0).getFileName());
        Assert.assertNotNull(analysisProject.getAnalysisFiles().get(0).getLanguage());

        // Try to get rules analyzed
        Assert.assertEquals(573, analysisProject.getAnalysisRules().size());
        Assert.assertNotNull(analysisProject.getAnalysisRules().get(0).getResult());
        Assert.assertNotNull(analysisProject.getAnalysisRules().get(0).getAnalysisRuleId());

        // Try to get results
        Assert.assertNotNull(analysisProject.getAnalysisRules().get(0).getResult().getResultTypePlace());
        Assert.assertNotNull(analysisProject.getAnalysisRules().get(58).getResult().getResultMessage());
        Assert.assertNull(analysisProject.getAnalysisRules().get(0).getResult().getResultMessage());
        Assert.assertNotNull(analysisProject.getAnalysisRules().get(0).getResult().getFileName());
        Assert.assertNotNull(analysisProject.getAnalysisRules().get(0).getResult().getResultValue());
        Assert.assertNotNull(analysisProject.getAnalysisRules().get(0).getResult().getResultLine());
    }
}

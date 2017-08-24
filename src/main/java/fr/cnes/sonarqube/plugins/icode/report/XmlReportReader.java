package fr.cnes.sonarqube.plugins.icode.report;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fr.cnes.sonarqube.plugins.icode.measures.ReportInterface;

/**
 * SAX parser for XML ICode report input file
 * 
 * @author Cyrille FRANCOIS
 *
 */
public class XmlReportReader {

	private static final Logger LOGGER = Loggers.get(XmlReportReader.class);

	/**
	 * Parse a XML report file.
	 * 
	 * @param fileReportPath
	 * @return a object services provider {@link ReportInterface} from the report file
	 * 
	 * @see XmlReportReader#SAXHandler
	 */
	static public ReportInterface parse(Path fileReportPath) {
		AnalysisProject res = new AnalysisProject();

		//  New factory
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		SAXHandler handler = new SAXHandler(res);
		try {
			parser = factory.newSAXParser();
			parser.parse(fileReportPath.toFile(), handler);
		} catch (ParserConfigurationException | SAXException e) {
			LOGGER.error("Parsing error: "+e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.error("Report file error: "+e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.debug("parse("+fileReportPath.getFileName()+")"+handler.getAnalysisProject());
		return handler.getAnalysisProject();
	}
}

/**
 * SAX Handler called  by {@link XmlReportReader}
 * 
 * @author Cyrille FRANCOIS
 *
 */
class SAXHandler extends DefaultHandler {

	public static final String ANALYSIS_PROJECT = "analysisProject";

	public static final String ANALYSIS_RULE = "analysisRule";

	public static final String ANALYSIS_FILE = "analysisFile";

	public static final String ANALYSIS_INFORMATIONS = "analysisInformations";

	private static final Logger LOGGER = Loggers.get(SAXHandler.class);
	
	private AnalysisProject analysisProject = null;

	public SAXHandler(AnalysisProject analysisProject) {
		super();
		this.analysisProject = analysisProject;
	}

	/** object services provider {@link ReportInterface} from the report file */
	public ReportInterface getAnalysisProject() {
		return analysisProject;
	}

	AnalysisRule currentRule = null;
	String content;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {
		case ANALYSIS_RULE:
			currentRule = new AnalysisRule();
			currentRule.analysisRuleId = attributes.getValue(AnalysisRule.ANALYSIS_RULE_ID);
			break;
		case AnalysisRule.RESULT:
			if (currentRule != null) {
				Result currentResult = new Result();
				currentResult.fileName = attributes.getValue(Result.FILE_NAME);
				currentResult.resultId = attributes.getValue(Result.RESULT_ID);
				currentResult.resultLine = attributes.getValue(Result.RESULT_LINE);
				currentResult.resultNamePlace = attributes.getValue(Result.RESULT_NAME_PLACE);
				currentResult.resultTypePlace = attributes.getValue(Result.RESULT_TYPE_PLACE);
				currentResult.resultValue = attributes.getValue(Result.RESULT_VALUE);

				currentRule.result = currentResult;
			} else {
				LOGGER.error("Unexpected result element:" + qName);
			}
			break;
		case Result.RESULT_MESSAGE:
			// No attribut to read !
			break;
		case ANALYSIS_INFORMATIONS:
			analysisProject.analysisInformations = new AnalysisInformations();
			analysisProject.analysisInformations.analysisConfigurationId = attributes
					.getValue(AnalysisInformations.ANALYSIS_CONFIGURATION_ID);
			analysisProject.analysisInformations.analysisDate = attributes.getValue(AnalysisInformations.ANALYSIS_DATE);
			analysisProject.analysisInformations.author = attributes.getValue(AnalysisInformations.AUTHOR);
			break;
		case ANALYSIS_FILE:
			analysisProject.analysisFile = new AnalysisFile();
			analysisProject.analysisFile.fileName = attributes.getValue(AnalysisFile.FILE_NAME);
			analysisProject.analysisFile.language = attributes.getValue(AnalysisFile.LANGUAGE);
			break;
		case ANALYSIS_PROJECT:
			LOGGER.info("Begin report analysis:" + qName);
			analysisProject = new AnalysisProject();
			analysisProject.analysisProjectName = attributes.getValue(AnalysisProject.ANALYSIS_PROJECT_NAME);
			analysisProject.analysisProjectVersion = attributes.getValue(AnalysisProject.ANALYSIS_PROJECT_VERSION);
			analysisProject.listOfAnalysisRule = new ArrayList<AnalysisRule>();
			break;
		default:
			LOGGER.error("Unexpected report element:" + qName);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case ANALYSIS_RULE:
			analysisProject.listOfAnalysisRule.add(currentRule);
			break;
		case Result.RESULT_MESSAGE:
			if (currentRule != null && currentRule.result != null) {
				ResultMessage resultMessage = new ResultMessage();
				resultMessage.content = content;

				currentRule.result.resultMessage = resultMessage;
			} else {
				LOGGER.error("Unexpected result message:" + qName);
			}
			break;
		case ANALYSIS_PROJECT:
			LOGGER.info("End report analysis:" + qName);
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		content = String.copyValueOf(ch, start, length).trim();
	}

}

/**
 * XML ICode result element container
 * 
 * @see SAXHandler#ANALYSIS_RULE
 * 
 * @author Cyrille FRANCOIS
 *
 */
class Result {
	static final String RESULT_MESSAGE = "resultMessage";
	static final String RESULT_ID = "resultId";
	static final String FILE_NAME = "fileName";
	static final String RESULT_LINE = "resultLine";
	static final String RESULT_TYPE_PLACE = "resultTypePlace";
	static final String RESULT_NAME_PLACE = "resultNamePlace";
	static final String RESULT_VALUE = "resultValue";

	String resultId;
	String fileName;
	String resultLine;
	String resultTypePlace;
	String resultNamePlace;
	String resultValue;
	ResultMessage resultMessage;
	
	@Override
	public String toString() {
		return "Result [resultId=" + resultId + ", fileName=" + fileName + ", resultLine=" + resultLine
				+ ", resultTypePlace=" + resultTypePlace + ", resultNamePlace=" + resultNamePlace + ", resultValue="
				+ resultValue + ", resultMessage=" + resultMessage + "]";
	}
}

/**
 * XML ICode resultMessage element container
 * 
 * @see Result#RESULT_MESSAGE
 *  
 * @author Cyrille FRANCOIS
 *
 */
class ResultMessage {
	String content;
	
	@Override
	public String toString() {
		return "ResultMessage [content=" + content + "]";
	}
}

/**
 * XML ICode analyseFile element container
 *
 * @see SAXHandler#ANALYSIS_FILE
 * 
 * @author Cyrille FRANCOIS
 */
class AnalysisFile {

	static final String LANGUAGE = "language";
	static final String FILE_NAME = "fileName";

	String language;
	String fileName;
	
	@Override
	public String toString() {
		return "AnalysisFile [language=" + language + ", fileName=" + fileName + "]";
	}
}

/**
 * XML ICode analysisInformations element container
 *
 * @see SAXHandler#ANALYSIS_INFORMATIONS
 * 
 * @author Cyrille FRANCOIS
 */
class AnalysisInformations {
	static final String ANALYSIS_CONFIGURATION_ID = "analysisConfigurationId";
	static final String ANALYSIS_DATE = "analysisDate";
	static final String AUTHOR = "author";

	String analysisConfigurationId;
	String analysisDate;
	String author;
	
	@Override
	public String toString() {
		return "AnalysisInformations [analysisConfigurationId=" + analysisConfigurationId + ", analysisDate="
				+ analysisDate + ", author=" + author + "]";
	}
}
package fr.cnes.sonarqube.plugins.icode.report;

import fr.cnes.sonarqube.plugins.icode.report.ErrorInterface;

/**
 * Error definition provided by the ICode report analysis
 * 
 * @author Cyrille FRANCOIS
 */
public class ICodeError implements ErrorInterface {
    private final String type;
    private final String description;
    private final String filePath;
    private final String line;

    public ICodeError(final String type, final String description, final String filePath, final String line) {
      this.type = type;
      this.description = description;
      this.filePath = filePath;
      this.line = line;
    }

    public String getType() {
      return type;
    }

    @Override
	public String getDescription() {
      return description;
    }

    public String getFilePath() {
      return filePath;
    }

    public String getLine() {
      return line;
    }

    @Override
    public String toString() {
      StringBuilder s = new StringBuilder();
      s.append(type);
      s.append("|");
      s.append(description);
      s.append("|");
      s.append(filePath);
      s.append("(");
      s.append(line);
      s.append(")");
      return s.toString();
    }
    
    @Override
	public String getRuleKey(){
    	return this.type;
    }
    @Override
	public String getLineDescriptor(){
    	return this.line;
    }
}

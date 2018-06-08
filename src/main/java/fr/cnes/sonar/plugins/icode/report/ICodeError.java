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
package fr.cnes.sonar.plugins.icode.report;

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

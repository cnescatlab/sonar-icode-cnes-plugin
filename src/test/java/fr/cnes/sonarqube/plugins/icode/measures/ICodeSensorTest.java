package fr.cnes.sonarqube.plugins.icode.measures;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.FilePredicates;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputComponent;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.TextRange;
import org.sonar.api.batch.measure.Metric;
import org.sonar.api.batch.rule.Severity;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.batch.sensor.measure.NewMeasure;
import org.sonar.api.rule.RuleKey;

public class ICodeSensorTest {

	@Test
	public void getLineAsIntTest(){
		int expected = 1;
		int actual = ICodeSensor.getLineAsInt("-5", 100);
		assertEquals(expected, actual);
		expected = 100;
		actual = ICodeSensor.getLineAsInt("200", 100);
		assertEquals(expected, actual);
		expected = -1;
		actual = ICodeSensor.getLineAsInt("aa", 100);
		assertEquals(expected, actual);
	}
	
	@Test
	public void given_sensorDescriptor_when_describe_then_callSensorDescriptorName() {
		SensorDescriptor sensorDescriptor = Mockito.mock(SensorDescriptor.class);
		ICodeSensor icodeMetricsSensor = new ICodeSensor();
		icodeMetricsSensor.describe(sensorDescriptor);
		verify(sensorDescriptor).name(ICodeSensor.class.getName());
	}

	@Test
	public void given_NoReportFile_when_execute_then_reportErrors(){
		ICodeSensor iCodeSensor = new ICodeSensor(){
			// Stub plugin settings
			void readPluginSettings(SensorContext context) {
				this.expectedReportInputFileTypes = "*.f";
				this.reportOutExt = ".res";
				this.reportSubdir = "reports";				
			};
		};
		
		// Mock sensor context
		SensorContext sensorContext = Mockito.mock(SensorContext.class);
		FileSystem fs = Mockito.mock(FileSystem.class);
		FilePredicates filePredicates = Mockito.mock(FilePredicates.class);
		FilePredicate filePredicate = Mockito.mock(FilePredicate.class);
		InputFile inputFile = Mockito.mock(InputFile.class);
		Mockito.when(sensorContext.fileSystem()).thenReturn(fs);
		Mockito.when(fs.predicates()).thenReturn(filePredicates);
		
		// Mock input files
		ArrayList<InputFile> filesC = new ArrayList<InputFile>();
		filesC.add(inputFile);
		Mockito.when(filePredicates.matchesPathPatterns(
				new String[]{iCodeSensor.expectedReportInputFileTypes})).thenReturn(filePredicate);
		Mockito.when(fs.inputFiles(filePredicate)).thenReturn(filesC);
		Mockito.when(fs.inputFiles(null)).thenReturn(filesC);

		File file = new File("./src/test/resources/test.f");
		Mockito.when(inputFile.absolutePath()).thenReturn(file.getAbsolutePath());
		Mockito.when(inputFile.file()).thenReturn(file);
		
		// Stub newMeasure
		NewMeasure<Serializable> newMeasure = new NewMeasure(){

			@Override
			public NewMeasure forMetric(Metric arg0) {
				// TODO Auto-generated method stub
				return this;
			}

			@Override
			public NewMeasure on(InputComponent arg0) {
				// TODO Auto-generated method stub
				return this;
			}

			@Override
			public void save() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public NewMeasure withValue(Serializable arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
		};
		Mockito.when(sensorContext.<Serializable>newMeasure()).thenReturn(newMeasure);
		
		// Stub issue
		NewIssue newIssue = new NewIssue() {
			NewIssueLocation newIssueLocation = new NewIssueLocation() {
				
				@Override
				public NewIssueLocation on(InputComponent arg0) {
					// TODO Auto-generated method stub
					return this;
				}
				
				@Override
				public NewIssueLocation message(String arg0) {
					// TODO Auto-generated method stub
					return this;
				}
				
				@Override
				public NewIssueLocation at(TextRange arg0) {
					// TODO Auto-generated method stub
					return this;
				}
			};
			@Override
			public void save() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public NewIssue overrideSeverity(Severity arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public NewIssueLocation newLocation() {
				
				return newIssueLocation;
			}
			
			@Override
			public NewIssue gap(Double arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewIssue forRule(RuleKey arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewIssue effortToFix(Double arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewIssue at(NewIssueLocation arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewIssue addLocation(NewIssueLocation arg0) {
				newIssueLocation = arg0;
				return this;
			}
			
			@Override
			public NewIssue addFlow(Iterable<NewIssueLocation> arg0) {
				// TODO Auto-generated method stub
				return this;
			}
		};
		Mockito.when(sensorContext.newIssue()).thenReturn(newIssue);

		// call sensor
		iCodeSensor.execute(sensorContext);
	}
	
	@Test
	public void given_EmptyReportFile_when_execute_then_reportErrors(){
		ICodeSensor iCodeSensor = new ICodeSensor(){
			// Stub plugin settings
			void readPluginSettings(SensorContext context) {
				this.expectedReportInputFileTypes = "*.f";
				this.reportOutExt = ".res";
				this.reportSubdir = "reports";				
			};
		};
		
		// Mock sensor context
		SensorContext sensorContext = Mockito.mock(SensorContext.class);
		FileSystem fs = Mockito.mock(FileSystem.class);
		FilePredicates filePredicates = Mockito.mock(FilePredicates.class);
		FilePredicate filePredicate = Mockito.mock(FilePredicate.class);
		InputFile inputFile = Mockito.mock(InputFile.class);
		Mockito.when(sensorContext.fileSystem()).thenReturn(fs);
		Mockito.when(fs.predicates()).thenReturn(filePredicates);
		
		// Mock input files
		ArrayList<InputFile> filesC = new ArrayList<InputFile>();
		filesC.add(inputFile);
		Mockito.when(filePredicates.matchesPathPatterns(
				new String[]{iCodeSensor.expectedReportInputFileTypes})).thenReturn(filePredicate);
		Mockito.when(fs.inputFiles(filePredicate)).thenReturn(filesC);
		Mockito.when(fs.inputFiles(null)).thenReturn(filesC);

		File file = new File("./src/test/resources/testEmptyReport.f");
		Mockito.when(inputFile.absolutePath()).thenReturn(file.getAbsolutePath());
		Mockito.when(inputFile.file()).thenReturn(file);
		
		// Stub newMeasure
		NewMeasure<Serializable> newMeasure = new NewMeasure<Serializable>() {
			
			@Override
			public NewMeasure<Serializable> withValue(Serializable arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public void save() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public NewMeasure<Serializable> on(InputComponent arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewMeasure<Serializable> forMetric(Metric<Serializable> arg0) {
				// TODO Auto-generated method stub
				return this;
			}
		};
		Mockito.when(sensorContext.<Serializable>newMeasure()).thenReturn(newMeasure);

		// call sensor
		iCodeSensor.execute(sensorContext);
	}

	@Test
	public void given_reportFile_when_execute_then_reportIssues(){
		ICodeSensor iCodeSensor = new ICodeSensor(){
			// Stub plugin settings
			void readPluginSettings(SensorContext context) {
				this.expectedReportInputFileTypes = "*.f";
				this.reportOutExt = ".res";
				this.reportSubdir = "reports";				
			};
		};
		
		// Mock sensor context
		SensorContext sensorContext = Mockito.mock(SensorContext.class);
		FileSystem fs = Mockito.mock(FileSystem.class);
		FilePredicates filePredicates = Mockito.mock(FilePredicates.class);
		FilePredicate filePredicate = Mockito.mock(FilePredicate.class);
		InputFile inputFile = Mockito.mock(InputFile.class);
		Mockito.when(sensorContext.fileSystem()).thenReturn(fs);
		Mockito.when(fs.predicates()).thenReturn(filePredicates);
		
		// Mock input files
		ArrayList<InputFile> filesC = new ArrayList<InputFile>();
		filesC.add(inputFile);
		Mockito.when(filePredicates.matchesPathPatterns(
				new String[]{iCodeSensor.expectedReportInputFileTypes})).thenReturn(filePredicate);
		Mockito.when(fs.inputFiles(filePredicate)).thenReturn(filesC);
		Mockito.when(fs.inputFiles(null)).thenReturn(filesC);

		File file = new File("./src/test/resources/test.f");
		Mockito.when(inputFile.absolutePath()).thenReturn(file.getAbsolutePath());
		Mockito.when(inputFile.file()).thenReturn(file);
		
		// Stub newMeasure
		NewMeasure<Serializable> newMeasure = new NewMeasure(){

			@Override
			public NewMeasure forMetric(Metric arg0) {
				// TODO Auto-generated method stub
				return this;
			}

			@Override
			public NewMeasure on(InputComponent arg0) {
				// TODO Auto-generated method stub
				return this;
			}

			@Override
			public void save() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public NewMeasure withValue(Serializable arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
		};
		Mockito.when(sensorContext.<Serializable>newMeasure()).thenReturn(newMeasure);
		
		// Stub issue
		NewIssue newIssue = new NewIssue() {
			NewIssueLocation newIssueLocation = new NewIssueLocation() {
				
				@Override
				public NewIssueLocation on(InputComponent arg0) {
					// TODO Auto-generated method stub
					return this;
				}
				
				@Override
				public NewIssueLocation message(String arg0) {
					// TODO Auto-generated method stub
					return this;
				}
				
				@Override
				public NewIssueLocation at(TextRange arg0) {
					// TODO Auto-generated method stub
					return this;
				}
			};
			@Override
			public void save() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public NewIssue overrideSeverity(Severity arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public NewIssueLocation newLocation() {
				
				return newIssueLocation;
			}
			
			@Override
			public NewIssue gap(Double arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewIssue forRule(RuleKey arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewIssue effortToFix(Double arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewIssue at(NewIssueLocation arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewIssue addLocation(NewIssueLocation arg0) {
				newIssueLocation = arg0;
				return this;
			}
			
			@Override
			public NewIssue addFlow(Iterable<NewIssueLocation> arg0) {
				// TODO Auto-generated method stub
				return this;
			}
		};
		Mockito.when(sensorContext.newIssue()).thenReturn(newIssue);

		// call sensor
		iCodeSensor.execute(sensorContext);
	}


	@Test
	public void given_reportFileSHELL_when_execute_then_reportIssues(){
		ICodeSensor iCodeSensor = new ICodeSensor(){
			// Stub plugin settings
			void readPluginSettings(SensorContext context) {
				this.expectedReportInputFileTypes = "*.sh";
				this.reportOutExt = ".res";
				this.reportSubdir = "reports";				
			};
		};
		
		// Mock sensor context
		SensorContext sensorContext = Mockito.mock(SensorContext.class);
		FileSystem fs = Mockito.mock(FileSystem.class);
		FilePredicates filePredicates = Mockito.mock(FilePredicates.class);
		FilePredicate filePredicate = Mockito.mock(FilePredicate.class);
		InputFile inputFile = Mockito.mock(InputFile.class);
		Mockito.when(sensorContext.fileSystem()).thenReturn(fs);
		Mockito.when(fs.predicates()).thenReturn(filePredicates);
		
		// Mock input files
		ArrayList<InputFile> filesC = new ArrayList<InputFile>();
		filesC.add(inputFile);
		Mockito.when(filePredicates.matchesPathPatterns(
				new String[]{iCodeSensor.expectedReportInputFileTypes})).thenReturn(filePredicate);
		Mockito.when(fs.inputFiles(filePredicate)).thenReturn(filesC);
		Mockito.when(fs.inputFiles(null)).thenReturn(filesC);

		File file = new File("./src/test/resources/test.sh");
		Mockito.when(inputFile.absolutePath()).thenReturn(file.getAbsolutePath());
		Mockito.when(inputFile.file()).thenReturn(file);
		
		// Stub newMeasure
		NewMeasure<Serializable> newMeasure = new NewMeasure(){

			@Override
			public NewMeasure forMetric(Metric arg0) {
				// TODO Auto-generated method stub
				return this;
			}

			@Override
			public NewMeasure on(InputComponent arg0) {
				// TODO Auto-generated method stub
				return this;
			}

			@Override
			public void save() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public NewMeasure withValue(Serializable arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
		};
		Mockito.when(sensorContext.<Serializable>newMeasure()).thenReturn(newMeasure);
		
		// Stub issue
		NewIssue newIssue = new NewIssue() {
			NewIssueLocation newIssueLocation = new NewIssueLocation() {
				
				@Override
				public NewIssueLocation on(InputComponent arg0) {
					// TODO Auto-generated method stub
					return this;
				}
				
				@Override
				public NewIssueLocation message(String arg0) {
					// TODO Auto-generated method stub
					return this;
				}
				
				@Override
				public NewIssueLocation at(TextRange arg0) {
					// TODO Auto-generated method stub
					return this;
				}
			};
			@Override
			public void save() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public NewIssue overrideSeverity(Severity arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public NewIssueLocation newLocation() {
				
				return newIssueLocation;
			}
			
			@Override
			public NewIssue gap(Double arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewIssue forRule(RuleKey arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewIssue effortToFix(Double arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewIssue at(NewIssueLocation arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewIssue addLocation(NewIssueLocation arg0) {
				newIssueLocation = arg0;
				return this;
			}
			
			@Override
			public NewIssue addFlow(Iterable<NewIssueLocation> arg0) {
				// TODO Auto-generated method stub
				return this;
			}
		};
		Mockito.when(sensorContext.newIssue()).thenReturn(newIssue);

		// call sensor
		iCodeSensor.execute(sensorContext);
	}

	

	@Test
	public void given_reportFileF90_when_execute_then_reportIssues(){
		ICodeSensor iCodeSensor = new ICodeSensor(){
			// Stub plugin settings
			void readPluginSettings(SensorContext context) {
				this.expectedReportInputFileTypes = "*.f90";
				this.reportOutExt = ".res";
				this.reportSubdir = "reports";				
			};
		};
		
		// Mock sensor context
		SensorContext sensorContext = Mockito.mock(SensorContext.class);
		FileSystem fs = Mockito.mock(FileSystem.class);
		FilePredicates filePredicates = Mockito.mock(FilePredicates.class);
		FilePredicate filePredicate = Mockito.mock(FilePredicate.class);
		InputFile inputFile = Mockito.mock(InputFile.class);
		Mockito.when(sensorContext.fileSystem()).thenReturn(fs);
		Mockito.when(fs.predicates()).thenReturn(filePredicates);
		
		// Mock input files
		ArrayList<InputFile> filesC = new ArrayList<InputFile>();
		filesC.add(inputFile);
		Mockito.when(filePredicates.matchesPathPatterns(
				new String[]{iCodeSensor.expectedReportInputFileTypes})).thenReturn(filePredicate);
		Mockito.when(fs.inputFiles(filePredicate)).thenReturn(filesC);
		Mockito.when(fs.inputFiles(null)).thenReturn(filesC);

		File file = new File("./src/test/resources/test.f90");
		Mockito.when(inputFile.absolutePath()).thenReturn(file.getAbsolutePath());
		Mockito.when(inputFile.file()).thenReturn(file);
		
		// Stub newMeasure
		NewMeasure<Serializable> newMeasure = new NewMeasure(){

			@Override
			public NewMeasure forMetric(Metric arg0) {
				// TODO Auto-generated method stub
				return this;
			}

			@Override
			public NewMeasure on(InputComponent arg0) {
				// TODO Auto-generated method stub
				return this;
			}

			@Override
			public void save() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public NewMeasure withValue(Serializable arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
		};
		Mockito.when(sensorContext.<Serializable>newMeasure()).thenReturn(newMeasure);
		
		// Stub issue
		NewIssue newIssue = new NewIssue() {
			NewIssueLocation newIssueLocation = new NewIssueLocation() {
				
				@Override
				public NewIssueLocation on(InputComponent arg0) {
					// TODO Auto-generated method stub
					return this;
				}
				
				@Override
				public NewIssueLocation message(String arg0) {
					// TODO Auto-generated method stub
					return this;
				}
				
				@Override
				public NewIssueLocation at(TextRange arg0) {
					// TODO Auto-generated method stub
					return this;
				}
			};
			@Override
			public void save() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public NewIssue overrideSeverity(Severity arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public NewIssueLocation newLocation() {
				
				return newIssueLocation;
			}
			
			@Override
			public NewIssue gap(Double arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewIssue forRule(RuleKey arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewIssue effortToFix(Double arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewIssue at(NewIssueLocation arg0) {
				// TODO Auto-generated method stub
				return this;
			}
			
			@Override
			public NewIssue addLocation(NewIssueLocation arg0) {
				newIssueLocation = arg0;
				return this;
			}
			
			@Override
			public NewIssue addFlow(Iterable<NewIssueLocation> arg0) {
				// TODO Auto-generated method stub
				return this;
			}
		};
		Mockito.when(sensorContext.newIssue()).thenReturn(newIssue);

		// call sensor
		iCodeSensor.execute(sensorContext);
	}

}

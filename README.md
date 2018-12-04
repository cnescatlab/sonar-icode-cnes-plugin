# sonar-icode-cnes-plugin
[![Build Status](https://travis-ci.org/lequal/sonar-icode-cnes-plugin.svg?branch=master)](https://travis-ci.org/lequal/sonar-icode-cnes-plugin)
[![SonarQube Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=fr.cnes.sonarqube.plugins%3Asonaricode&metric=alert_status)](https://sonarcloud.io/dashboard?id=fr.cnes.sonarqube.plugins%3Asonaricode)
[![SonarQube Bugs](https://sonarcloud.io/api/project_badges/measure?project=fr.cnes.sonarqube.plugins%3Asonaricode&metric=bugs)](https://sonarcloud.io/project/issues?id=fr.cnes.sonarqube.plugins%3Asonaricode&resolved=false&types=BUG)
[![SonarQube Coverage](https://sonarcloud.io/api/project_badges/measure?project=fr.cnes.sonarqube.plugins%3Asonaricode&metric=coverage)](https://sonarcloud.io/component_measures?id=fr.cnes.sonarqube.plugins%3Asonaricode&metric=Coverage)
[![SonarQube Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=fr.cnes.sonarqube.plugins%3Asonaricode&metric=sqale_index)](https://sonarcloud.io/component_measures?id=fr.cnes.sonarqube.plugins%3Asonaricode&metric=sqale_index)

SonarQube plugin for the code analysis tool: i-Code CNES.

SonarQube is an open platform to manage code quality. This plugin adds the ability to import i-Code results for Fortran (77 & 90) & Shell.

This plugin is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.

You can get i-Code CNES on GitHub: [lequal/i-CodeCNES](https://github.com/lequal/i-CodeCNES).

### Quickstart
- Setup a SonarQube instance.
- Install i-Code command line application as described in [official documentation](https://github.com/lequal/i-CodeCNES/blob/master/documentation/i-Code%20CNES%20-%20Installation%20Manual%20-%20EN.pdf).
- Install `sonaricode-*.jar` in `<SONARQUBE_HOME>/extensions/plugins/`.
- Run i-Code manually or configure auto-launch in plugin configuration.
- Run an analysis with *sonar-scanner*, *maven*, *gradle*, *msbuild*, etc.

#### Get i-Code help
Use `icode -h` to get the following help about i-Code:
````
usage: icode [<FILE> [...]] [-c <arg>] [-e] [-f <arg>] [-h] [-l] [-o <arg>] [-p <arg>] [-q <arg>] [-r] [-x <arg>]
Analyze Shell, F77 & F90 code to find defects & bugs.

 -c,--checked-languages <arg>        Comma separated list of languages checked during analysis. All by default.
 -e,--exporters                      Display all available exporters.
 -f,--export-format <arg>            Set the format for result file. Default format is XML.
 -h,--help                           Display this message.
 -l,--languages                      Display all available languages.
 -o,--output <arg>                   Set the name for result file. Results are displayed in standard output by default.
 -p,--export-parameters <arg>        Comma separated list of parameters for the export. Format is:
                                     key1=value1,key2=value2,key3=value3. Default values depend on the chosen export plugin.
 -q,--list-export-parameters <arg>   Display all available parameters for the given export.
 -r,--rules                          Display all available rules.
 -x,--excluded-rules <arg>           Comma separated list of rules id to exclude from analysis. None by default.

Please report issues at https://github.com/lequal/i-CodeCNES/issues
````

#### Run i-Code manually
If you need help to run i-Code please refer to the [official user manual](https://github.com/lequal/i-CodeCNES/blob/master/documentation/i-Code%20CNES%20-%20User%20Manual.pdf) or [i-Code issue tracker](https://github.com/lequal/i-CodeCNES/issues).

#### Run i-Code through sonaricode plugin
In order to run i-Code automatically when running *sonar-scanner*, following SoanrQube properties have to be given:
- `sonar.icode.launch`: Activate autolaunch for i-Code if `true`. Default: `false`.
- `sonar.icode.path`: Define i-Code CNES executable path to auto-launch it on analysis. Default: `${HOME}/icode-cnes/icode.exe`.

#### Other plugin's properties
- `sonar.icode.shell.file.suffixes`: List of suffixes for Shell files to analyze. Default: `.sh,.ksh,.bash`.
- `sonar.icode.f77.file.suffixes`: List of suffixes for F77 files to analyze. Default: `.f,.f77,.for,.fpp,.ftn,.F,.F77,.FOR,.FPP,.FTN`.
- `sonar.icode.f90.file.suffixes`: List of suffixes for F90 files to analyze. Default: `.f90,.F90`.
- `sonar.icode.reports.path`: Path to the i-Code reports. Multiple path can be provided. Default: `result.res`.

### Features
- Fortran 77 analysis
- Fortran 90 analysis
- Shell analysis

### How to contribute
If you experienced a problem with the plugin please open an issue. Inside this issue please explain us how to reproduce this issue and paste the log. 

If you want to do a PR, please put inside of it the reason of this pull request. If this pull request fix an issue please insert the number of the issue or explain inside of the PR how to reproduce this issue.

### Feedback and Support
Contact : L-lequal@cnes.fr

Bugs and Feature requests: https://github.com/lequal/sonar-icode-cnes-plugin/issues

### License
Licensed under the [GNU General Public License, Version 3.0](https://www.gnu.org/licenses/gpl.txt)

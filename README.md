# sonar-icode-cnes-plugin
[![Build Status](https://travis-ci.org/lequal/sonar-icode-cnes-plugin.svg?branch=master)](https://travis-ci.org/lequal/sonar-icode-cnes-plugin)
[![SonarQube Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=fr.cnes.sonar.plugins%3Asonar-icode-cnes-plugin&metric=alert_status)](https://sonarcloud.io/dashboard?id=fr.cnes.sonar.plugins%3Asonar-icode-cnes-plugin)
[![SonarQube Bugs](https://sonarcloud.io/api/project_badges/measure?project=fr.cnes.sonar.plugins%3Asonar-icode-cnes-plugin&metric=bugs)](https://sonarcloud.io/project/issues?id=fr.cnes.sonar.plugins%3Asonar-icode-cnes-plugin&resolved=false&types=BUG)
[![SonarQube Coverage](https://sonarcloud.io/api/project_badges/measure?project=fr.cnes.sonar.plugins%3Asonar-icode-cnes-plugin&metric=coverage)](https://sonarcloud.io/component_measures?id=fr.cnes.sonar.plugins%3Asonar-icode-cnes-plugin&metric=Coverage)
[![SonarQube Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=fr.cnes.sonar.plugins%3Asonar-icode-cnes-plugin&metric=sqale_index)](https://sonarcloud.io/component_measures?id=fr.cnes.sonar.plugins%3Asonar-icode-cnes-plugin&metric=sqale_index)

SonarQube plugin for the code analysis tool: i-Code CNES.

SonarQube is an open platform to manage code quality. This plugin adds the ability to check Fortran (77 & 90) & Shell with i-Code or import pre-existing results of i-Code.

This plugin is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.

You can get i-Code CNES on GitHub: [lequal/i-CodeCNES](https://github.com/lequal/i-CodeCNES).

### Quickstart
- Setup a SonarQube instance.
- **[Optional]** Install i-Code command line application as described in [official documentation](https://github.com/lequal/i-CodeCNES/wiki/Installation-Manual).
- Install `sonaricode-*.jar` in `<SONARQUBE_HOME>/extensions/plugins/`.
- **[Optional]** Run i-Code manually or configure auto-launch in plugin configuration.
- Run an analysis with *sonar-scanner*, *maven*, *gradle*, *msbuild*, etc.

#### Run i-Code automatically
This SonarQube plugin is now able to run automaticcaly an embedded version of i-Code. If you do not specify properties to run i-Code [manually](#run-i-Code-manually) or [from a specific version](#Run-a-specific-i-Code-version-through-sonaricode-plugin), embedded version of i-Code will be executed.

Here is the compatibility matrix of the plugin:

| sonaricode version | embedded i-Code version | supported SonarQube version |
|:------------------:|:-----------------------:|:---------------------------:|
|       < 2.0.0      |           none          |          6.7.x              |
|        2.0.0       |          4.0.0          |        7.9 -> 8.1           |
|        2.0.1       |          4.0.0          |        7.9 -> 8.1           |

#### Run i-Code manually
If you need help to run i-Code please refer to the [official user manual](https://github.com/lequal/i-CodeCNES/wiki/User-Manual) or [i-Code issue tracker](https://github.com/lequal/i-CodeCNES/issues).

#### Run a specific i-Code version through sonaricode plugin
If embedded version of i-Code does not match your need, you can set the execution of another installed version of i-Code through the following properties:
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
- Import i-Code results

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

### How to contribute
If you experienced a problem with the plugin please open an issue. Inside this issue please explain us how to reproduce this issue and paste the log. 

If you want to do a PR, please put inside of it the reason of this pull request. If this pull request fix an issue please insert the number of the issue or explain inside of the PR how to reproduce this issue.

### Feedback and Support
Contact : L-lequal@cnes.fr

Bugs and Feature requests: https://github.com/lequal/sonar-icode-cnes-plugin/issues

### License
Licensed under the [GNU General Public License, Version 3.0](https://www.gnu.org/licenses/gpl.txt)

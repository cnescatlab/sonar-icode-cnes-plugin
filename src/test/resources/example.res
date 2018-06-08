<?xml version="1.0" encoding="UTF-8"?>
<analysisProject analysisProjectName="Unknown" analysisProjectVersion="1.0.0">
  <analysisInformations analysisConfigurationId="analysis1" analysisDate="2018-03-07" author="i-Code CNES Analyzer" />
  <analysisFile language="fr.cnes.analysis.tools.languages.shell" fileName="D:\Utilisateurs\waldmao\Documents\i-Code\01_VALIDATION_WORKSPACE\APSQ-011\activatesyncbackupnominal.sh" />
  <analysisRule analysisRuleId="COM.FLOW.FileExistence">
    <result resultId="1" fileName="D:\Utilisateurs\waldmao\Documents\i-Code\01_VALIDATION_WORKSPACE\APSQ-011\activatesyncbackupnominal.sh" resultLine="42" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The existence of the file fakesyncbackupnominal.sh has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FileExistence">
    <result resultId="2" fileName="D:\Utilisateurs\waldmao\Documents\i-Code\01_VALIDATION_WORKSPACE\APSQ-011\activatesyncbackupnominal.sh" resultLine="43" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The existence of the file syncbackupnominal.sh has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.DESIGN.Options">
    <result resultId="3" fileName="D:\Utilisateurs\waldmao\Documents\i-Code\01_VALIDATION_WORKSPACE\APSQ-011\activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is mandatory to use getopts and getopt and to provide the –h, -help, –v and -version options at least.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.ERR.Help">
    <result resultId="4" fileName="D:\Utilisateurs\waldmao\Documents\i-Code\01_VALIDATION_WORKSPACE\APSQ-011\activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The help options (-h &amp; --help) must be implemented in getopt &amp; getopts commands.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.FLOW.CheckUser">
    <result resultId="5" fileName="D:\Utilisateurs\waldmao\Documents\i-Code\01_VALIDATION_WORKSPACE\APSQ-011\activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The user has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.INST.GetOpts">
    <result resultId="6" fileName="D:\Utilisateurs\waldmao\Documents\i-Code\01_VALIDATION_WORKSPACE\APSQ-011\activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is mandatory to use getopts &amp; getopt in the script.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.INST.Variables">
    <result resultId="7" fileName="D:\Utilisateurs\waldmao\Documents\i-Code\01_VALIDATION_WORKSPACE\APSQ-011\activatesyncbackupnominal.sh" resultLine="32" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The variable $STARTTIME is not correctly used (must be used with the ${ } or " " notation )</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.ComplexitySimplified">
    <result resultId="8" fileName="D:\Utilisateurs\waldmao\Documents\i-Code\01_VALIDATION_WORKSPACE\APSQ-011\activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="class" resultValue="7.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.ComplexitySimplified">
    <result resultId="9" fileName="D:\Utilisateurs\waldmao\Documents\i-Code\01_VALIDATION_WORKSPACE\APSQ-011\activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="7.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.LineOfCode">
    <result resultId="10" fileName="D:\Utilisateurs\waldmao\Documents\i-Code\01_VALIDATION_WORKSPACE\APSQ-011\activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="class" resultValue="17.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.LineOfCode">
    <result resultId="11" fileName="D:\Utilisateurs\waldmao\Documents\i-Code\01_VALIDATION_WORKSPACE\APSQ-011\activatesyncbackupnominal.sh" resultLine="1" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="17.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.Nesting">
    <result resultId="12" fileName="D:\Utilisateurs\waldmao\Documents\i-Code\01_VALIDATION_WORKSPACE\APSQ-011\activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="class" resultValue="2.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.Nesting">
    <result resultId="13" fileName="D:\Utilisateurs\waldmao\Documents\i-Code\01_VALIDATION_WORKSPACE\APSQ-011\activatesyncbackupnominal.sh" resultLine="1" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="2.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.RatioComment">
    <result resultId="14" fileName="D:\Utilisateurs\waldmao\Documents\i-Code\01_VALIDATION_WORKSPACE\APSQ-011\activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="class" resultValue="60.465115" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.RatioComment">
    <result resultId="15" fileName="D:\Utilisateurs\waldmao\Documents\i-Code\01_VALIDATION_WORKSPACE\APSQ-011\activatesyncbackupnominal.sh" resultLine="1" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="60.465115" />
  </analysisRule>
</analysisProject>

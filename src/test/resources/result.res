<?xml version="1.0" encoding="UTF-8"?>
<analysisProject analysisProjectName="Unknown" analysisProjectVersion="1.0.0">
  <analysisInformations analysisConfigurationId="analysis1" analysisDate="2019-09-11" author="i-Code CNES Analyzer" />
  <analysisFile language="fr.cnes.analysis.tools.languages.f77" fileName="brdf.f" />
  <analysisFile language="fr.cnes.analysis.tools.languages.f77" fileName="programme_test.f" />
  <analysisFile language="fr.cnes.analysis.tools.languages.f90" fileName="cps_acsol2.F90" />
  <analysisFile language="fr.cnes.analysis.tools.languages.f90" fileName="cps_math_tchcal.F90" />
  <analysisFile language="fr.cnes.analysis.tools.languages.shell" fileName="activatesyncTMArchive.sh" />
  <analysisFile language="fr.cnes.analysis.tools.languages.shell" fileName="activatesyncbackupnominal.sh" />
  <analysisFile language="fr.cnes.analysis.tools.languages.shell" fileName="cat-scan-script.sh" />
  <analysisRule analysisRuleId="F77.MET.ComplexitySimplified">
    <result resultId="1" fileName="brdf.f" resultLine="13" resultTypePlace="method" resultNamePlace="FUNCTION  BDREF" resultValue="7.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.ComplexitySimplified">
    <result resultId="2" fileName="brdf.f" resultLine="237" resultTypePlace="method" resultNamePlace="FUNCTION  hapkebrdf" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.ComplexitySimplified">
    <result resultId="3" fileName="brdf.f" resultLine="328" resultTypePlace="method" resultNamePlace="FUNCTION  rahmanbrdf" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.ComplexitySimplified">
    <result resultId="4" fileName="brdf.f" resultLine="397" resultTypePlace="method" resultNamePlace="FUNCTION  roujeanbrdf" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.ComplexitySimplified">
    <result resultId="5" fileName="brdf.f" resultLine="462" resultTypePlace="method" resultNamePlace="SUBROUTINE  FINDPOSI" resultValue="7.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.ComplexitySimplified">
    <result resultId="6" fileName="brdf.f" resultLine="520" resultTypePlace="method" resultNamePlace="FUNCTION  LINTERP" resultValue="2.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.ComplexitySimplified">
    <result resultId="7" fileName="brdf.f" resultLine="0" resultTypePlace="class" resultValue="NaN" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.ComplexitySimplified">
    <result resultId="8" fileName="programme_test.f" resultLine="12" resultTypePlace="method" resultNamePlace="PROGRAM  TEST" resultValue="26.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.ComplexitySimplified">
    <result resultId="9" fileName="programme_test.f" resultLine="291" resultTypePlace="method" resultNamePlace="FUNCTION  SIN" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.ComplexitySimplified">
    <result resultId="10" fileName="programme_test.f" resultLine="304" resultTypePlace="method" resultNamePlace="SUBROUTINE  SUB" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.ComplexitySimplified">
    <result resultId="11" fileName="programme_test.f" resultLine="326" resultTypePlace="method" resultNamePlace="FUNCTION  NO_PARAM" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.ComplexitySimplified">
    <result resultId="12" fileName="programme_test.f" resultLine="0" resultTypePlace="class" resultValue="NaN" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.Nesting">
    <result resultId="13" fileName="brdf.f" resultLine="1" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="2.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.Nesting">
    <result resultId="14" fileName="brdf.f" resultLine="462" resultTypePlace="method" resultNamePlace="SUBROUTINE  FINDPOSI" resultValue="3.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.Nesting">
    <result resultId="15" fileName="brdf.f" resultLine="0" resultTypePlace="class" resultValue="NaN" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.Nesting">
    <result resultId="16" fileName="programme_test.f" resultLine="12" resultTypePlace="method" resultNamePlace="PROGRAM  TEST" resultValue="2.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.Nesting">
    <result resultId="17" fileName="programme_test.f" resultLine="291" resultTypePlace="method" resultNamePlace="FUNCTION  SIN" resultValue="0.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.Nesting">
    <result resultId="18" fileName="programme_test.f" resultLine="304" resultTypePlace="method" resultNamePlace="SUBROUTINE  SUB" resultValue="0.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.Nesting">
    <result resultId="19" fileName="programme_test.f" resultLine="326" resultTypePlace="method" resultNamePlace="FUNCTION  NO_PARAM" resultValue="0.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.Nesting">
    <result resultId="20" fileName="programme_test.f" resultLine="0" resultTypePlace="class" resultValue="NaN" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfCode">
    <result resultId="21" fileName="brdf.f" resultLine="13" resultTypePlace="method" resultNamePlace="FUNCTION  BDREF" resultValue="88.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfCode">
    <result resultId="22" fileName="brdf.f" resultLine="237" resultTypePlace="method" resultNamePlace="FUNCTION  hapkebrdf" resultValue="18.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfCode">
    <result resultId="23" fileName="brdf.f" resultLine="328" resultTypePlace="method" resultNamePlace="FUNCTION  rahmanbrdf" resultValue="19.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfCode">
    <result resultId="24" fileName="brdf.f" resultLine="397" resultTypePlace="method" resultNamePlace="FUNCTION  roujeanbrdf" resultValue="19.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfCode">
    <result resultId="25" fileName="brdf.f" resultLine="462" resultTypePlace="method" resultNamePlace="SUBROUTINE  FINDPOSI" resultValue="33.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfCode">
    <result resultId="26" fileName="brdf.f" resultLine="520" resultTypePlace="method" resultNamePlace="FUNCTION  LINTERP" resultValue="10.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfCode">
    <result resultId="27" fileName="brdf.f" resultLine="0" resultTypePlace="class" resultValue="187.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfCode">
    <result resultId="28" fileName="programme_test.f" resultLine="12" resultTypePlace="method" resultNamePlace="PROGRAM  TEST" resultValue="154.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfCode">
    <result resultId="29" fileName="programme_test.f" resultLine="291" resultTypePlace="method" resultNamePlace="FUNCTION  SIN" resultValue="5.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfCode">
    <result resultId="30" fileName="programme_test.f" resultLine="304" resultTypePlace="method" resultNamePlace="SUBROUTINE  SUB" resultValue="9.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfCode">
    <result resultId="31" fileName="programme_test.f" resultLine="326" resultTypePlace="method" resultNamePlace="FUNCTION  NO_PARAM" resultValue="3.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfCode">
    <result resultId="32" fileName="programme_test.f" resultLine="0" resultTypePlace="class" resultValue="172.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.RatioComment">
    <result resultId="33" fileName="brdf.f" resultLine="13" resultTypePlace="method" resultNamePlace="FUNCTION BDREF" resultValue="55.026455" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.RatioComment">
    <result resultId="34" fileName="brdf.f" resultLine="237" resultTypePlace="method" resultNamePlace="function" resultValue="80.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.RatioComment">
    <result resultId="35" fileName="brdf.f" resultLine="328" resultTypePlace="method" resultNamePlace="FUNCTION rahmanbrdf" resultValue="69.09091" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.RatioComment">
    <result resultId="36" fileName="brdf.f" resultLine="397" resultTypePlace="method" resultNamePlace="FUNCTION roujeanbrdf" resultValue="67.92453" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.RatioComment">
    <result resultId="37" fileName="brdf.f" resultLine="462" resultTypePlace="method" resultNamePlace="SUBROUTINE FINDPOSI" resultValue="39.215687" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.RatioComment">
    <result resultId="38" fileName="brdf.f" resultLine="520" resultTypePlace="method" resultNamePlace="FUNCTION LINTERP" resultValue="57.894737" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.RatioComment">
    <result resultId="39" fileName="brdf.f" resultLine="0" resultTypePlace="class" resultValue="NaN" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.RatioComment">
    <result resultId="40" fileName="programme_test.f" resultLine="12" resultTypePlace="method" resultNamePlace="PROGRAM TEST" resultValue="28.638496" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.RatioComment">
    <result resultId="41" fileName="programme_test.f" resultLine="291" resultTypePlace="method" resultNamePlace="FUNCTION SIN" resultValue="NaN" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.RatioComment">
    <result resultId="42" fileName="programme_test.f" resultLine="304" resultTypePlace="method" resultNamePlace="SUBROUTINE SUB" resultValue="36.363636" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.RatioComment">
    <result resultId="43" fileName="programme_test.f" resultLine="326" resultTypePlace="method" resultNamePlace="FUNCTION NO_PARAM" resultValue="NaN" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.RatioComment">
    <result resultId="44" fileName="programme_test.f" resultLine="0" resultTypePlace="class" resultValue="NaN" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfComment">
    <result resultId="45" fileName="brdf.f" resultLine="13" resultTypePlace="method" resultNamePlace="FUNCTION BDREF" resultValue="104.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfComment">
    <result resultId="46" fileName="brdf.f" resultLine="237" resultTypePlace="method" resultNamePlace="function" resultValue="60.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfComment">
    <result resultId="47" fileName="brdf.f" resultLine="328" resultTypePlace="method" resultNamePlace="FUNCTION rahmanbrdf" resultValue="38.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfComment">
    <result resultId="48" fileName="brdf.f" resultLine="397" resultTypePlace="method" resultNamePlace="FUNCTION roujeanbrdf" resultValue="36.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfComment">
    <result resultId="49" fileName="brdf.f" resultLine="462" resultTypePlace="method" resultNamePlace="SUBROUTINE FINDPOSI" resultValue="20.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfComment">
    <result resultId="50" fileName="brdf.f" resultLine="520" resultTypePlace="method" resultNamePlace="FUNCTION LINTERP" resultValue="11.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfComment">
    <result resultId="51" fileName="brdf.f" resultLine="0" resultTypePlace="class" resultValue="284.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfComment">
    <result resultId="52" fileName="programme_test.f" resultLine="12" resultTypePlace="method" resultNamePlace="PROGRAM TEST" resultValue="61.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfComment">
    <result resultId="53" fileName="programme_test.f" resultLine="291" resultTypePlace="method" resultNamePlace="FUNCTION SIN" resultValue="2.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfComment">
    <result resultId="54" fileName="programme_test.f" resultLine="304" resultTypePlace="method" resultNamePlace="SUBROUTINE SUB" resultValue="4.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfComment">
    <result resultId="55" fileName="programme_test.f" resultLine="326" resultTypePlace="method" resultNamePlace="FUNCTION NO_PARAM" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F77.MET.LineOfComment">
    <result resultId="56" fileName="programme_test.f" resultLine="0" resultTypePlace="class" resultValue="75.0" />
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.FloatCompare">
    <result resultId="57" fileName="brdf.f" resultLine="535" resultTypePlace="method" resultNamePlace="function The">
      <resultMessage>It's not allowed to compare float variables (X1) with equality.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.Invariant">
    <result resultId="58" fileName="brdf.f" resultLine="115" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>The variable brdftype must be defined as constant.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.Invariant">
    <result resultId="59" fileName="brdf.f" resultLine="483" resultTypePlace="method" resultNamePlace="SUBROUTINE FINDPOSI">
      <resultMessage>The variable A must be defined as constant.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.Invariant">
    <result resultId="60" fileName="brdf.f" resultLine="483" resultTypePlace="method" resultNamePlace="SUBROUTINE FINDPOSI">
      <resultMessage>The variable B must be defined as constant.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.Invariant">
    <result resultId="61" fileName="programme_test.f" resultLine="75" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The variable C must be defined as constant.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.Invariant">
    <result resultId="62" fileName="programme_test.f" resultLine="76" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The variable D must be defined as constant.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.Invariant">
    <result resultId="63" fileName="programme_test.f" resultLine="84" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The variable X must be defined as constant.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.NotUsed">
    <result resultId="64" fileName="brdf.f" resultLine="107" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>The variable minnaertbrdf is declared and not used.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.NotUsed">
    <result resultId="65" fileName="brdf.f" resultLine="296" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>The variable h is declared and not used.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.NotUsed">
    <result resultId="66" fileName="brdf.f" resultLine="367" resultTypePlace="method" resultNamePlace="FUNCTION rahmanbrdf">
      <resultMessage>The variable dphir is declared and not used.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.NotUsed">
    <result resultId="67" fileName="brdf.f" resultLine="436" resultTypePlace="method" resultNamePlace="FUNCTION roujeanbrdf">
      <resultMessage>The variable phir is declared and not used.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.NotUsed">
    <result resultId="68" fileName="brdf.f" resultLine="436" resultTypePlace="method" resultNamePlace="FUNCTION roujeanbrdf">
      <resultMessage>The variable tmp is declared and not used.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.NotUsed">
    <result resultId="69" fileName="programme_test.f" resultLine="26" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The variable useless is declared and not used.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DESIGN.Alloc">
    <result resultId="70" fileName="programme_test.f" resultLine="227" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The resource named 12 has not been allocated and deallocate in the same algorithmic level.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.BooleanExpression">
    <result resultId="71" fileName="brdf.f" resultLine="151" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>Using more than five conditions in an expression is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckUser">
    <result resultId="72" fileName="programme_test.f" resultLine="12" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The user identity is not verified in the main program.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.Exit">
    <result resultId="73" fileName="programme_test.f" resultLine="319" resultTypePlace="method" resultNamePlace="SUBROUTINE SUB">
      <resultMessage>There is more than one exit in the function.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.ExitLoop">
    <result resultId="74" fileName="programme_test.f" resultLine="182" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>There is more than one exit in the loop.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FileExistence">
    <result resultId="75" fileName="programme_test.f" resultLine="223" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The existences of the file file must be checked with the instruction INQUIRE before being opened or created.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FileExistence">
    <result resultId="76" fileName="programme_test.f" resultLine="227" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The existences of the file file must be checked with the instruction INQUIRE before being opened or created.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="77" fileName="programme_test.f" resultLine="223" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>It is not allowed to specify the file with this name. Use a variable instead.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="78" fileName="programme_test.f" resultLine="227" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>It is not allowed to specify the file with this name. Use a variable instead.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="79" fileName="brdf.f" resultLine="126" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="80" fileName="brdf.f" resultLine="152" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="81" fileName="brdf.f" resultLine="189" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="82" fileName="brdf.f" resultLine="302" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="83" fileName="brdf.f" resultLine="302" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="84" fileName="brdf.f" resultLine="302" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="85" fileName="brdf.f" resultLine="307" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="86" fileName="brdf.f" resultLine="307" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="87" fileName="brdf.f" resultLine="308" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="88" fileName="brdf.f" resultLine="308" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="89" fileName="brdf.f" resultLine="314" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="90" fileName="brdf.f" resultLine="316" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="91" fileName="brdf.f" resultLine="316" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="92" fileName="brdf.f" resultLine="317" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="93" fileName="brdf.f" resultLine="317" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="94" fileName="brdf.f" resultLine="318" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="95" fileName="brdf.f" resultLine="318" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="96" fileName="brdf.f" resultLine="378" resultTypePlace="method" resultNamePlace="FUNCTION rahmanbrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="97" fileName="brdf.f" resultLine="379" resultTypePlace="method" resultNamePlace="FUNCTION rahmanbrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="98" fileName="brdf.f" resultLine="379" resultTypePlace="method" resultNamePlace="FUNCTION rahmanbrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="99" fileName="brdf.f" resultLine="379" resultTypePlace="method" resultNamePlace="FUNCTION rahmanbrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="100" fileName="brdf.f" resultLine="384" resultTypePlace="method" resultNamePlace="FUNCTION rahmanbrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="101" fileName="brdf.f" resultLine="387" resultTypePlace="method" resultNamePlace="FUNCTION rahmanbrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="102" fileName="brdf.f" resultLine="388" resultTypePlace="method" resultNamePlace="FUNCTION rahmanbrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="103" fileName="brdf.f" resultLine="446" resultTypePlace="method" resultNamePlace="FUNCTION roujeanbrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="104" fileName="brdf.f" resultLine="449" resultTypePlace="method" resultNamePlace="FUNCTION roujeanbrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="105" fileName="brdf.f" resultLine="449" resultTypePlace="method" resultNamePlace="FUNCTION roujeanbrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="106" fileName="brdf.f" resultLine="449" resultTypePlace="method" resultNamePlace="FUNCTION roujeanbrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="107" fileName="brdf.f" resultLine="450" resultTypePlace="method" resultNamePlace="FUNCTION roujeanbrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="108" fileName="brdf.f" resultLine="452" resultTypePlace="method" resultNamePlace="FUNCTION roujeanbrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="109" fileName="brdf.f" resultLine="452" resultTypePlace="method" resultNamePlace="FUNCTION roujeanbrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="110" fileName="brdf.f" resultLine="452" resultTypePlace="method" resultNamePlace="FUNCTION roujeanbrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="111" fileName="brdf.f" resultLine="454" resultTypePlace="method" resultNamePlace="FUNCTION roujeanbrdf">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="112" fileName="brdf.f" resultLine="496" resultTypePlace="method" resultNamePlace="SUBROUTINE FINDPOSI">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="113" fileName="brdf.f" resultLine="497" resultTypePlace="method" resultNamePlace="SUBROUTINE FINDPOSI">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="114" fileName="brdf.f" resultLine="536" resultTypePlace="method" resultNamePlace="FUNCTION LINTERP">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="115" fileName="brdf.f" resultLine="536" resultTypePlace="method" resultNamePlace="FUNCTION LINTERP">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="116" fileName="programme_test.f" resultLine="202" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="117" fileName="programme_test.f" resultLine="205" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="118" fileName="programme_test.f" resultLine="215" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="119" fileName="programme_test.f" resultLine="237" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.GoTo">
    <result resultId="120" fileName="programme_test.f" resultLine="169" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The keyword GOTO is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.GoTo">
    <result resultId="121" fileName="programme_test.f" resultLine="173" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The keyword GOTO is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.GoTo">
    <result resultId="122" fileName="programme_test.f" resultLine="182" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The keyword GOTO is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.NAME.Homonymy">
    <result resultId="123" fileName="brdf.f" resultLine="237" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Variable names should be unique. The variable FUNCTION is already defined in this file.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.NAME.Homonymy">
    <result resultId="124" fileName="brdf.f" resultLine="328" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Variable names should be unique. The variable FUNCTION is already defined in this file.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.NAME.Homonymy">
    <result resultId="125" fileName="brdf.f" resultLine="520" resultTypePlace="method" resultNamePlace="SUBROUTINE findposi">
      <resultMessage>Variable names should be unique. The variable FUNCTION is already defined in this file.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PROJECT.Header">
    <result resultId="126" fileName="brdf.f" resultLine="237" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>This module/function should have a header with a brief description.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PROJECT.Header">
    <result resultId="127" fileName="brdf.f" resultLine="328" resultTypePlace="method" resultNamePlace="FUNCTION rahmanbrdf">
      <resultMessage>This module/function should have a header with a brief description.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PROJECT.Header">
    <result resultId="128" fileName="brdf.f" resultLine="397" resultTypePlace="method" resultNamePlace="FUNCTION roujeanbrdf">
      <resultMessage>This module/function should have a header with a brief description.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PROJECT.Header">
    <result resultId="129" fileName="brdf.f" resultLine="462" resultTypePlace="method" resultNamePlace="SUBROUTINE FINDPOSI">
      <resultMessage>This module/function should have a header with a brief description.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PROJECT.Header">
    <result resultId="130" fileName="brdf.f" resultLine="520" resultTypePlace="method" resultNamePlace="FUNCTION LINTERP">
      <resultMessage>This module/function should have a header with a brief description.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PROJECT.Header">
    <result resultId="131" fileName="programme_test.f" resultLine="1" resultTypePlace="method" resultNamePlace="No file header (file name not found).">
      <resultMessage>This module/function should have a header with a brief description.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PROJECT.Header">
    <result resultId="132" fileName="programme_test.f" resultLine="304" resultTypePlace="method" resultNamePlace="SUBROUTINE SUB">
      <resultMessage>This module/function should have a header with a brief description.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PROJECT.Header">
    <result resultId="133" fileName="programme_test.f" resultLine="326" resultTypePlace="method" resultNamePlace="FUNCTION NO_PARAM">
      <resultMessage>This module/function should have a header with a brief description.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="134" fileName="brdf.f" resultLine="133" resultTypePlace="method" resultNamePlace="REAL*8 FUNCTION BDREF">
      <resultMessage>The code is not indented.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="135" fileName="programme_test.f" resultLine="275" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The code is not indented.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="136" fileName="programme_test.f" resultLine="281" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The code is not indented.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.LengthLine">
    <result resultId="137" fileName="programme_test.f" resultLine="293" resultTypePlace="method" resultNamePlace="MAIN PROGRAM TEST SIN">
      <resultMessage>There are more than 100 characters in this line.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.LengthLine">
    <result resultId="138" fileName="programme_test.f" resultLine="294" resultTypePlace="method" resultNamePlace="MAIN PROGRAM TEST SIN">
      <resultMessage>There are more than 100 characters in this line.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.LengthLine">
    <result resultId="139" fileName="programme_test.f" resultLine="313" resultTypePlace="method" resultNamePlace="MAIN PROGRAM TEST SIN SUB">
      <resultMessage>There are more than 100 characters in this line.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.TYPE.Expression">
    <result resultId="140" fileName="programme_test.f" resultLine="202" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>Mixed type REAL with INTEGER</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.TYPE.Expression">
    <result resultId="141" fileName="programme_test.f" resultLine="215" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>Mixed type DOUBLE PRECISION with REAL</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.TYPE.Expression">
    <result resultId="142" fileName="programme_test.f" resultLine="237" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>Mixed type REAL with INTEGER</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.BLOC.Common">
    <result resultId="143" fileName="programme_test.f" resultLine="68" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>Unnamed COMMON is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.BLOC.Else">
    <result resultId="144" fileName="brdf.f" resultLine="229" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>The IF instruction shall finish with an ELSE after the last ELSE IF.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.BLOC.Else">
    <result resultId="145" fileName="brdf.f" resultLine="509" resultTypePlace="method" resultNamePlace="SUBROUTINE FINDPOSI">
      <resultMessage>The IF instruction shall finish with an ELSE after the last ELSE IF.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.BLOC.Else">
    <result resultId="146" fileName="programme_test.f" resultLine="255" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The IF instruction shall finish with an ELSE after the last ELSE IF.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.BLOC.Loop">
    <result resultId="147" fileName="programme_test.f" resultLine="264" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>Loops shall have distinct ends.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.Array">
    <result resultId="148" fileName="brdf.f" resultLine="483" resultTypePlace="method" resultNamePlace="SUBROUTINE FINDPOSI">
      <resultMessage>The dimension of the array A is not well declared. A comment who justifies the use of * is needed before.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.Common">
    <result resultId="149" fileName="brdf.f" resultLine="120" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>The INCLUDE instruction shall be used to reference the needed common bloc.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.Common">
    <result resultId="150" fileName="programme_test.f" resultLine="68" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The INCLUDE instruction shall be used to reference the needed common bloc.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.Common">
    <result resultId="151" fileName="programme_test.f" resultLine="69" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The INCLUDE instruction shall be used to reference the needed common bloc.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.Double">
    <result resultId="152" fileName="programme_test.f" resultLine="64" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The double precision variable CONST is not correctly initialized. It misses the character D in its declaration.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.Double">
    <result resultId="153" fileName="programme_test.f" resultLine="103" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The double precision variable OPER3 is not correctly initialized. It misses the character D in its declaration.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.Double">
    <result resultId="154" fileName="programme_test.f" resultLine="104" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The double precision variable RESUL is not correctly initialized. It misses the character D in its declaration.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.Initialisation">
    <result resultId="155" fileName="programme_test.f" resultLine="89" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The variable I shall be initialized with DATA or BLOCK DATA before its use.I</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.Initialisation">
    <result resultId="156" fileName="programme_test.f" resultLine="91" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The variable J shall be initialized with DATA or BLOCK DATA before its use.J</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.Initialisation">
    <result resultId="157" fileName="programme_test.f" resultLine="101" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The variable OPER1 shall be initialized with DATA or BLOCK DATA before its use.OPER1</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.Initialisation">
    <result resultId="158" fileName="programme_test.f" resultLine="103" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The variable OPER3 shall be initialized with DATA or BLOCK DATA before its use.OPER3</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.Initialisation">
    <result resultId="159" fileName="programme_test.f" resultLine="153" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The variable A shall be initialized with DATA or BLOCK DATA before its use.A</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="160" fileName="programme_test.f" resultLine="134" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="161" fileName="programme_test.f" resultLine="140" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="162" fileName="programme_test.f" resultLine="141" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="163" fileName="programme_test.f" resultLine="147" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="164" fileName="programme_test.f" resultLine="160" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="165" fileName="programme_test.f" resultLine="164" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="166" fileName="programme_test.f" resultLine="176" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="167" fileName="programme_test.f" resultLine="177" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="168" fileName="programme_test.f" resultLine="199" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="169" fileName="programme_test.f" resultLine="206" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="170" fileName="programme_test.f" resultLine="211" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="171" fileName="programme_test.f" resultLine="219" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="172" fileName="programme_test.f" resultLine="233" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="173" fileName="programme_test.f" resultLine="234" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="174" fileName="programme_test.f" resultLine="244" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="175" fileName="programme_test.f" resultLine="245" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="176" fileName="programme_test.f" resultLine="246" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="177" fileName="programme_test.f" resultLine="250" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="178" fileName="programme_test.f" resultLine="252" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="179" fileName="programme_test.f" resultLine="254" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.IO">
    <result resultId="180" fileName="programme_test.f" resultLine="281" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The use of * with logical units is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.LoopDO">
    <result resultId="181" fileName="programme_test.f" resultLine="258" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The control variable in a loop shall be an integer.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.DATA.Parameter">
    <result resultId="182" fileName="programme_test.f" resultLine="278" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>Error in the following parameters: 3 variable belongs to parameter types forbidden when calling a function: a constant, an expression to be evaluated, a call to another function</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.ERR.OpenRead">
    <result resultId="183" fileName="programme_test.f" resultLine="223" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The status of OPEN/READ shall be tested with the parameter IOSTAT.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.ERR.OpenRead">
    <result resultId="184" fileName="programme_test.f" resultLine="227" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The status of OPEN/READ shall be tested with the parameter IOSTAT.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.INST.Dimension">
    <result resultId="185" fileName="programme_test.f" resultLine="32" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The instruction DIMENSION is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.INST.Equivalence">
    <result resultId="186" fileName="programme_test.f" resultLine="77" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The instruction EQUIVALENCE is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.INST.Function">
    <result resultId="187" fileName="brdf.f" resultLine="314" resultTypePlace="method" resultNamePlace="function">
      <resultMessage>It misses the type declaration in FUNCTION header.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.INST.Function">
    <result resultId="188" fileName="programme_test.f" resultLine="291" resultTypePlace="method" resultNamePlace="FUNCTION">
      <resultMessage>It misses the type declaration in FUNCTION header.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.INST.Function">
    <result resultId="189" fileName="programme_test.f" resultLine="326" resultTypePlace="method" resultNamePlace="FUNCTION">
      <resultMessage>It misses the type declaration in FUNCTION header.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.INST.If">
    <result resultId="190" fileName="programme_test.f" resultLine="243" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The arithmetic if is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.INST.Pause">
    <result resultId="191" fileName="programme_test.f" resultLine="269" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The instruction PAUSE is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.INST.Return">
    <result resultId="192" fileName="brdf.f" resultLine="514" resultTypePlace="method" resultNamePlace="SUBROUTINE FINDPOSI">
      <resultMessage>The instruction RETURN(i) is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.INST.Return">
    <result resultId="193" fileName="programme_test.f" resultLine="318" resultTypePlace="method" resultNamePlace="SUBROUTINE SUB">
      <resultMessage>The instruction RETURN(i) is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.INST.Return">
    <result resultId="194" fileName="programme_test.f" resultLine="319" resultTypePlace="method" resultNamePlace="SUBROUTINE SUB">
      <resultMessage>The instruction RETURN(i) is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.NAME.GenericIntrinsic">
    <result resultId="195" fileName="brdf.f" resultLine="144" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It should be used the generic name of the intrinsic function instead of DABS</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.NAME.GenericIntrinsic">
    <result resultId="196" fileName="brdf.f" resultLine="303" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It should be used the generic name of the intrinsic function instead of DACOS</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.NAME.GenericIntrinsic">
    <result resultId="197" fileName="brdf.f" resultLine="315" resultTypePlace="method" resultNamePlace="function">
      <resultMessage>It should be used the generic name of the intrinsic function instead of DSQRT</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.NAME.Intrinsic">
    <result resultId="198" fileName="programme_test.f" resultLine="291" resultTypePlace="method" resultNamePlace="FUNCTION">
      <resultMessage>It is not allowed to use the name of an intrinsic function.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.NAME.KeyWords">
    <result resultId="199" fileName="programme_test.f" resultLine="56" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The variable END is a keyword in Fortran77 language.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.REF.IO">
    <result resultId="200" fileName="programme_test.f" resultLine="275" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The logical entities shall be declared using a symbolic name.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.REF.Open">
    <result resultId="201" fileName="programme_test.f" resultLine="223" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The instruction OPEN shall be called with the parameters FILE, STATUS and POSITION.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.REF.Open">
    <result resultId="202" fileName="programme_test.f" resultLine="227" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>The instruction OPEN shall be called with the parameters FILE, STATUS and POSITION.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.REF.Parameter">
    <result resultId="203" fileName="programme_test.f" resultLine="278" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>It is not allowed to provide as a parameter the variables of an accessible bloc COMMON. The variable A is used in a wrong way.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.REF.Parameter">
    <result resultId="204" fileName="programme_test.f" resultLine="278" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>It is not allowed to provide as a parameter the variables of an accessible bloc COMMON. The variable A is used in a wrong way.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="205" fileName="brdf.f" resultLine="13" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>REAL*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="206" fileName="brdf.f" resultLine="104" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>REAL*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="207" fileName="brdf.f" resultLine="106" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="208" fileName="brdf.f" resultLine="107" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="209" fileName="brdf.f" resultLine="108" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="210" fileName="brdf.f" resultLine="110" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="211" fileName="brdf.f" resultLine="111" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="212" fileName="brdf.f" resultLine="112" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="213" fileName="brdf.f" resultLine="116" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="214" fileName="brdf.f" resultLine="117" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="215" fileName="brdf.f" resultLine="118" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="216" fileName="brdf.f" resultLine="237" resultTypePlace="method" resultNamePlace="FUNCTION BDREF">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="217" fileName="brdf.f" resultLine="295" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>REAL*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="218" fileName="brdf.f" resultLine="296" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="219" fileName="brdf.f" resultLine="297" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="220" fileName="brdf.f" resultLine="328" resultTypePlace="method" resultNamePlace="FUNCTION hapkebrdf">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="221" fileName="brdf.f" resultLine="366" resultTypePlace="method" resultNamePlace="FUNCTION rahmanbrdf">
      <resultMessage>REAL*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="222" fileName="brdf.f" resultLine="367" resultTypePlace="method" resultNamePlace="FUNCTION rahmanbrdf">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="223" fileName="brdf.f" resultLine="397" resultTypePlace="method" resultNamePlace="FUNCTION rahmanbrdf">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="224" fileName="brdf.f" resultLine="435" resultTypePlace="method" resultNamePlace="FUNCTION roujeanbrdf">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="225" fileName="brdf.f" resultLine="436" resultTypePlace="method" resultNamePlace="FUNCTION roujeanbrdf">
      <resultMessage>real*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="226" fileName="brdf.f" resultLine="483" resultTypePlace="method" resultNamePlace="SUBROUTINE FINDPOSI">
      <resultMessage>REAL*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="227" fileName="brdf.f" resultLine="520" resultTypePlace="method" resultNamePlace="SUBROUTINE FINDPOSI">
      <resultMessage>REAL*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="228" fileName="brdf.f" resultLine="532" resultTypePlace="method" resultNamePlace="FUNCTION LINTERP">
      <resultMessage>REAL*8 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="229" fileName="programme_test.f" resultLine="59" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>INTEGER*4 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Basic">
    <result resultId="230" fileName="programme_test.f" resultLine="60" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>REAL*4 is not a basic type. Basic types are INTEGER, REAL, DOUBLE PRECISION, COMPLEX, LOGICAL and CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Hollerith">
    <result resultId="231" fileName="programme_test.f" resultLine="113" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>Type Hollerith is not allowed. 16Habcdefghijklmnopshall be a CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F77.TYPE.Hollerith">
    <result resultId="232" fileName="programme_test.f" resultLine="113" resultTypePlace="method" resultNamePlace="PROGRAM TEST">
      <resultMessage>Type Hollerith is not allowed. 16Hqrstuvwxyzshall be a CHARACTER.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.ComplexitySimplified">
    <result resultId="233" fileName="cps_acsol2.F90" resultLine="1" resultTypePlace="method" resultNamePlace="module  cps_acsol2" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.ComplexitySimplified">
    <result resultId="234" fileName="cps_acsol2.F90" resultLine="126" resultTypePlace="method" resultNamePlace="function  cpsi_getCleAcsol2" resultValue="3.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.ComplexitySimplified">
    <result resultId="235" fileName="cps_acsol2.F90" resultLine="204" resultTypePlace="method" resultNamePlace="subroutine  cpsi_analyserCleAcsol2" resultValue="4.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.ComplexitySimplified">
    <result resultId="236" fileName="cps_acsol2.F90" resultLine="288" resultTypePlace="method" resultNamePlace="subroutine  cps_lireAcsol2" resultValue="15.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.ComplexitySimplified">
    <result resultId="237" fileName="cps_acsol2.F90" resultLine="481" resultTypePlace="method" resultNamePlace="subroutine  cps_lireEtConvertirAcsol2" resultValue="8.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.ComplexitySimplified">
    <result resultId="238" fileName="cps_acsol2.F90" resultLine="634" resultTypePlace="method" resultNamePlace="subroutine  cpsi_lire_unficAcsol2_MADONA" resultValue="8.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.ComplexitySimplified">
    <result resultId="239" fileName="cps_acsol2.F90" resultLine="834" resultTypePlace="method" resultNamePlace="subroutine  cpsi_cpter_lignes_Acsol2_MADONA" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.ComplexitySimplified">
    <result resultId="240" fileName="cps_acsol2.F90" resultLine="0" resultTypePlace="class" resultValue="NaN" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.ComplexitySimplified">
    <result resultId="241" fileName="cps_math_tchcal.F90" resultLine="39" resultTypePlace="method" resultNamePlace="subroutine  cps_math_tchcal" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.ComplexitySimplified">
    <result resultId="242" fileName="cps_math_tchcal.F90" resultLine="0" resultTypePlace="class" resultValue="NaN" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.Nesting">
    <result resultId="243" fileName="cps_acsol2.F90" resultLine="1" resultTypePlace="method" resultNamePlace="module  cps_acsol2" resultValue="0.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.Nesting">
    <result resultId="244" fileName="cps_acsol2.F90" resultLine="126" resultTypePlace="method" resultNamePlace="function  cpsi_getCleAcsol2" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.Nesting">
    <result resultId="245" fileName="cps_acsol2.F90" resultLine="204" resultTypePlace="method" resultNamePlace="subroutine  cpsi_analyserCleAcsol2" resultValue="2.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.Nesting">
    <result resultId="246" fileName="cps_acsol2.F90" resultLine="288" resultTypePlace="method" resultNamePlace="subroutine  cps_lireAcsol2" resultValue="2.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.Nesting">
    <result resultId="247" fileName="cps_acsol2.F90" resultLine="481" resultTypePlace="method" resultNamePlace="subroutine  cps_lireEtConvertirAcsol2" resultValue="2.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.Nesting">
    <result resultId="248" fileName="cps_acsol2.F90" resultLine="634" resultTypePlace="method" resultNamePlace="subroutine  cpsi_lire_unficAcsol2_MADONA" resultValue="2.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.Nesting">
    <result resultId="249" fileName="cps_acsol2.F90" resultLine="834" resultTypePlace="method" resultNamePlace="subroutine  cpsi_cpter_lignes_Acsol2_MADONA" resultValue="0.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.Nesting">
    <result resultId="250" fileName="cps_acsol2.F90" resultLine="0" resultTypePlace="class" resultValue="NaN" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.Nesting">
    <result resultId="251" fileName="cps_math_tchcal.F90" resultLine="39" resultTypePlace="method" resultNamePlace="subroutine  cps_math_tchcal" resultValue="0.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.Nesting">
    <result resultId="252" fileName="cps_math_tchcal.F90" resultLine="0" resultTypePlace="class" resultValue="NaN" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfCode">
    <result resultId="253" fileName="cps_acsol2.F90" resultLine="1" resultTypePlace="method" resultNamePlace="module  cps_acsol2" resultValue="122.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfCode">
    <result resultId="254" fileName="cps_acsol2.F90" resultLine="126" resultTypePlace="method" resultNamePlace="function  cpsi_getCleAcsol2" resultValue="77.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfCode">
    <result resultId="255" fileName="cps_acsol2.F90" resultLine="204" resultTypePlace="method" resultNamePlace="subroutine  cpsi_analyserCleAcsol2" resultValue="80.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfCode">
    <result resultId="256" fileName="cps_acsol2.F90" resultLine="288" resultTypePlace="method" resultNamePlace="subroutine  cps_lireAcsol2" resultValue="190.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfCode">
    <result resultId="257" fileName="cps_acsol2.F90" resultLine="481" resultTypePlace="method" resultNamePlace="subroutine  cps_lireEtConvertirAcsol2" resultValue="151.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfCode">
    <result resultId="258" fileName="cps_acsol2.F90" resultLine="634" resultTypePlace="method" resultNamePlace="subroutine  cpsi_lire_unficAcsol2_MADONA" resultValue="199.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfCode">
    <result resultId="259" fileName="cps_acsol2.F90" resultLine="834" resultTypePlace="method" resultNamePlace="subroutine  cpsi_cpter_lignes_Acsol2_MADONA" resultValue="124.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfCode">
    <result resultId="260" fileName="cps_acsol2.F90" resultLine="0" resultTypePlace="class" resultValue="958.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfCode">
    <result resultId="261" fileName="cps_math_tchcal.F90" resultLine="39" resultTypePlace="method" resultNamePlace="subroutine  cps_math_tchcal" resultValue="255.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfCode">
    <result resultId="262" fileName="cps_math_tchcal.F90" resultLine="0" resultTypePlace="class" resultValue="294.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.RatioComment">
    <result resultId="263" fileName="cps_acsol2.F90" resultLine="1" resultTypePlace="method" resultNamePlace="module cps_acsol2" resultValue="93.04348" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.RatioComment">
    <result resultId="264" fileName="cps_acsol2.F90" resultLine="126" resultTypePlace="method" resultNamePlace="function cpsi_getCleAcsol2" resultValue="67.16418" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.RatioComment">
    <result resultId="265" fileName="cps_acsol2.F90" resultLine="204" resultTypePlace="method" resultNamePlace="subroutine cpsi_analyserCleAcsol2" resultValue="67.14285" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.RatioComment">
    <result resultId="266" fileName="cps_acsol2.F90" resultLine="288" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2" resultValue="38.181816" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.RatioComment">
    <result resultId="267" fileName="cps_acsol2.F90" resultLine="481" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2" resultValue="58.59375" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.RatioComment">
    <result resultId="268" fileName="cps_acsol2.F90" resultLine="634" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA" resultValue="53.254436" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.RatioComment">
    <result resultId="269" fileName="cps_acsol2.F90" resultLine="834" resultTypePlace="method" resultNamePlace="subroutine cpsi_cpter_lignes_Acsol2_MADONA" resultValue="65.38461" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.RatioComment">
    <result resultId="270" fileName="cps_acsol2.F90" resultLine="0" resultTypePlace="class" resultValue="NaN" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.RatioComment">
    <result resultId="271" fileName="cps_math_tchcal.F90" resultLine="39" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal" resultValue="65.15837" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.RatioComment">
    <result resultId="272" fileName="cps_math_tchcal.F90" resultLine="0" resultTypePlace="class" resultValue="NaN" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfComment">
    <result resultId="273" fileName="cps_acsol2.F90" resultLine="1" resultTypePlace="method" resultNamePlace="module cps_acsol2" resultValue="107.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfComment">
    <result resultId="274" fileName="cps_acsol2.F90" resultLine="126" resultTypePlace="method" resultNamePlace="function cpsi_getCleAcsol2" resultValue="45.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfComment">
    <result resultId="275" fileName="cps_acsol2.F90" resultLine="204" resultTypePlace="method" resultNamePlace="subroutine cpsi_analyserCleAcsol2" resultValue="47.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfComment">
    <result resultId="276" fileName="cps_acsol2.F90" resultLine="288" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2" resultValue="63.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfComment">
    <result resultId="277" fileName="cps_acsol2.F90" resultLine="481" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2" resultValue="75.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfComment">
    <result resultId="278" fileName="cps_acsol2.F90" resultLine="634" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA" resultValue="90.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfComment">
    <result resultId="279" fileName="cps_acsol2.F90" resultLine="834" resultTypePlace="method" resultNamePlace="subroutine cpsi_cpter_lignes_Acsol2_MADONA" resultValue="68.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfComment">
    <result resultId="280" fileName="cps_acsol2.F90" resultLine="0" resultTypePlace="class" resultValue="498.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfComment">
    <result resultId="281" fileName="cps_math_tchcal.F90" resultLine="39" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal" resultValue="144.0" />
  </analysisRule>
  <analysisRule analysisRuleId="F90.MET.LineOfComment">
    <result resultId="282" fileName="cps_math_tchcal.F90" resultLine="0" resultTypePlace="class" resultValue="174.0" />
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.Initialisation">
    <result resultId="283" fileName="cps_math_tchcal.F90" resultLine="202" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The variable ncmax is used before being initialized.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.Invariant">
    <result resultId="284" fileName="cps_acsol2.F90" resultLine="114" resultTypePlace="method" resultNamePlace="module cps_acsol2">
      <resultMessage>The variable SVN_VER must be defined as constant.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.Invariant">
    <result resultId="285" fileName="cps_math_tchcal.F90" resultLine="34" resultTypePlace="method" resultNamePlace="module cps_math_tchcal_mod">
      <resultMessage>The variable SVN_VER must be defined as constant.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.Invariant">
    <result resultId="286" fileName="cps_math_tchcal.F90" resultLine="170" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The variable ncmax must be defined as constant.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.Invariant">
    <result resultId="287" fileName="cps_math_tchcal.F90" resultLine="179" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The variable ntrav must be defined as constant.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.Invariant">
    <result resultId="288" fileName="cps_math_tchcal.F90" resultLine="187" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The variable msg must be defined as constant.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.NotUsed">
    <result resultId="289" fileName="cps_acsol2.F90" resultLine="114" resultTypePlace="method" resultNamePlace="module cps_acsol2">
      <resultMessage>The variable svn_ver is declared and not used.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.NotUsed">
    <result resultId="290" fileName="cps_math_tchcal.F90" resultLine="34" resultTypePlace="method" resultNamePlace="module cps_math_tchcal_mod">
      <resultMessage>The variable svn_ver is declared and not used.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DESIGN.Alloc">
    <result resultId="291" fileName="cps_acsol2.F90" resultLine="355" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>The resource named tab_lignes has not been allocated and deallocate in the same algorithmic level.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DESIGN.Alloc">
    <result resultId="292" fileName="cps_acsol2.F90" resultLine="418" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>The resource named tab_lignes has not been allocated and deallocate in the same algorithmic level.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DESIGN.Alloc">
    <result resultId="293" fileName="cps_acsol2.F90" resultLine="564" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>The resource named tab_date has not been allocated and deallocate in the same algorithmic level.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DESIGN.Alloc">
    <result resultId="294" fileName="cps_acsol2.F90" resultLine="565" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>The resource named tab_data_flux has not been allocated and deallocate in the same algorithmic level.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DESIGN.Alloc">
    <result resultId="295" fileName="cps_acsol2.F90" resultLine="566" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>The resource named tab_data_ia has not been allocated and deallocate in the same algorithmic level.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DESIGN.Alloc">
    <result resultId="296" fileName="cps_acsol2.F90" resultLine="573" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>The resource named tab_date has not been allocated and deallocate in the same algorithmic level.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DESIGN.Alloc">
    <result resultId="297" fileName="cps_acsol2.F90" resultLine="574" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>The resource named tab_data_flux has not been allocated and deallocate in the same algorithmic level.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DESIGN.Alloc">
    <result resultId="298" fileName="cps_acsol2.F90" resultLine="575" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>The resource named tab_data_ia has not been allocated and deallocate in the same algorithmic level.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="299" fileName="cps_acsol2.F90" resultLine="750" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="300" fileName="cps_acsol2.F90" resultLine="756" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="301" fileName="cps_acsol2.F90" resultLine="771" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="302" fileName="cps_acsol2.F90" resultLine="778" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="303" fileName="cps_acsol2.F90" resultLine="779" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="304" fileName="cps_acsol2.F90" resultLine="781" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="305" fileName="cps_acsol2.F90" resultLine="782" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="306" fileName="cps_acsol2.F90" resultLine="783" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="307" fileName="cps_acsol2.F90" resultLine="784" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="308" fileName="cps_acsol2.F90" resultLine="785" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="309" fileName="cps_acsol2.F90" resultLine="786" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="310" fileName="cps_acsol2.F90" resultLine="787" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="311" fileName="cps_acsol2.F90" resultLine="788" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="312" fileName="cps_acsol2.F90" resultLine="789" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="313" fileName="cps_acsol2.F90" resultLine="790" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="314" fileName="cps_acsol2.F90" resultLine="794" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="315" fileName="cps_acsol2.F90" resultLine="795" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="316" fileName="cps_acsol2.F90" resultLine="797" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="317" fileName="cps_acsol2.F90" resultLine="798" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="318" fileName="cps_acsol2.F90" resultLine="799" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="319" fileName="cps_acsol2.F90" resultLine="800" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="320" fileName="cps_acsol2.F90" resultLine="801" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="321" fileName="cps_acsol2.F90" resultLine="802" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="322" fileName="cps_acsol2.F90" resultLine="803" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="323" fileName="cps_acsol2.F90" resultLine="804" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="324" fileName="cps_acsol2.F90" resultLine="805" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="325" fileName="cps_acsol2.F90" resultLine="813" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="326" fileName="cps_acsol2.F90" resultLine="830" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="327" fileName="cps_acsol2.F90" resultLine="924" resultTypePlace="method" resultNamePlace="subroutine cpsi_cpter_lignes_Acsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="328" fileName="cps_acsol2.F90" resultLine="929" resultTypePlace="method" resultNamePlace="subroutine cpsi_cpter_lignes_Acsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.CheckCodeReturn">
    <result resultId="329" fileName="cps_acsol2.F90" resultLine="953" resultTypePlace="method" resultNamePlace="subroutine cpsi_cpter_lignes_Acsol2_MADONA">
      <resultMessage>The return code of the function ret is not checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.Exit">
    <result resultId="330" fileName="cps_acsol2.F90" resultLine="277" resultTypePlace="method" resultNamePlace="subroutine cpsi_analyserCleAcsol2">
      <resultMessage>There is more than one exit in the function.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.Exit">
    <result resultId="331" fileName="cps_acsol2.F90" resultLine="385" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>There is more than one exit in the function.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.Exit">
    <result resultId="332" fileName="cps_acsol2.F90" resultLine="622" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>There is more than one exit in the function.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.ExitLoop">
    <result resultId="333" fileName="cps_acsol2.F90" resultLine="758" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>There is more than one exit in the loop.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.ExitLoop">
    <result resultId="334" fileName="cps_acsol2.F90" resultLine="768" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>There is more than one exit in the loop.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.ExitLoop">
    <result resultId="335" fileName="cps_acsol2.F90" resultLine="931" resultTypePlace="method" resultNamePlace="subroutine cpsi_cpter_lignes_Acsol2_MADONA">
      <resultMessage>There is more than one exit in the loop.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.ExitLoop">
    <result resultId="336" fileName="cps_math_tchcal.F90" resultLine="241" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>There is more than one exit in the loop.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="337" fileName="cps_acsol2.F90" resultLine="400" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="338" fileName="cps_acsol2.F90" resultLine="409" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="339" fileName="cps_acsol2.F90" resultLine="439" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="340" fileName="cps_acsol2.F90" resultLine="753" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="341" fileName="cps_acsol2.F90" resultLine="768" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="342" fileName="cps_acsol2.F90" resultLine="941" resultTypePlace="method" resultNamePlace="subroutine cpsi_cpter_lignes_Acsol2_MADONA">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="343" fileName="cps_math_tchcal.F90" resultLine="226" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="344" fileName="cps_math_tchcal.F90" resultLine="234" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="345" fileName="cps_math_tchcal.F90" resultLine="258" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="346" fileName="cps_math_tchcal.F90" resultLine="262" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="347" fileName="cps_math_tchcal.F90" resultLine="263" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="348" fileName="cps_math_tchcal.F90" resultLine="267" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="349" fileName="cps_math_tchcal.F90" resultLine="268" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.Brace">
    <result resultId="350" fileName="cps_math_tchcal.F90" resultLine="278" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>Parentheses are needed for readability.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.CodeComment">
    <result resultId="351" fileName="cps_math_tchcal.F90" resultLine="160" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>Commented code is not allowed. It shall be suppressed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.GoTo">
    <result resultId="352" fileName="cps_math_tchcal.F90" resultLine="206" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The keyword GOTO is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.GoTo">
    <result resultId="353" fileName="cps_math_tchcal.F90" resultLine="213" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The keyword GOTO is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.GoTo">
    <result resultId="354" fileName="cps_math_tchcal.F90" resultLine="241" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The keyword GOTO is not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.LoopCondition">
    <result resultId="355" fileName="cps_acsol2.F90" resultLine="395" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>A loop condition shall not be written with equality or difference (==,!=).</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.LoopCondition">
    <result resultId="356" fileName="cps_acsol2.F90" resultLine="434" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>A loop condition shall not be written with equality or difference (==,!=).</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.LoopCondition">
    <result resultId="357" fileName="cps_acsol2.F90" resultLine="752" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>A loop condition shall not be written with equality or difference (==,!=).</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.INST.LoopCondition">
    <result resultId="358" fileName="cps_acsol2.F90" resultLine="926" resultTypePlace="method" resultNamePlace="subroutine cpsi_cpter_lignes_Acsol2_MADONA">
      <resultMessage>A loop condition shall not be written with equality or difference (==,!=).</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="359" fileName="cps_acsol2.F90" resultLine="124" resultTypePlace="method" resultNamePlace="module cps_acsol2">
      <resultMessage>The code is not indented.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="360" fileName="cps_math_tchcal.F90" resultLine="37" resultTypePlace="method" resultNamePlace="module cps_math_tchcal_mod">
      <resultMessage>The code is not indented.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="361" fileName="cps_math_tchcal.F90" resultLine="203" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The code is not indented.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="362" fileName="cps_math_tchcal.F90" resultLine="204" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The code is not indented.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="363" fileName="cps_math_tchcal.F90" resultLine="206" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The code is not indented.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="364" fileName="cps_math_tchcal.F90" resultLine="214" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The code is not indented.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="365" fileName="cps_math_tchcal.F90" resultLine="283" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The code is not indented.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.LengthLine">
    <result resultId="366" fileName="cps_acsol2.F90" resultLine="104" resultTypePlace="method" resultNamePlace="module cps_acsol2">
      <resultMessage>There are more than 100 characters in this line.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.LengthLine">
    <result resultId="367" fileName="cps_acsol2.F90" resultLine="440" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>There are more than 100 characters in this line.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.LengthLine">
    <result resultId="368" fileName="cps_math_tchcal.F90" resultLine="34" resultTypePlace="method" resultNamePlace="module cps_math_tchcal_mod">
      <resultMessage>There are more than 100 characters in this line.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.LengthLine">
    <result resultId="369" fileName="cps_math_tchcal.F90" resultLine="218" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>There are more than 100 characters in this line.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PROJECT.Header">
    <result resultId="370" fileName="cps_math_tchcal.F90" resultLine="1" resultTypePlace="method" resultNamePlace="module cps_math_tchcal_mod">
      <resultMessage>This module/function should have a header with a brief description.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DATA.Constant">
    <result resultId="371" fileName="cps_acsol2.F90" resultLine="253" resultTypePlace="method" resultNamePlace="subroutine cpsi_analyserCleAcsol2">
      <resultMessage>The constants shall be declared and initialized in a module.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DATA.Constant">
    <result resultId="372" fileName="cps_acsol2.F90" resultLine="344" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>The constants shall be declared and initialized in a module.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DATA.Constant">
    <result resultId="373" fileName="cps_acsol2.F90" resultLine="347" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>The constants shall be declared and initialized in a module.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DATA.Constant">
    <result resultId="374" fileName="cps_math_tchcal.F90" resultLine="179" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The constants shall be declared and initialized in a module.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DATA.Declaration">
    <result resultId="375" fileName="cps_acsol2.F90" resultLine="264" resultTypePlace="method" resultNamePlace="subroutine cpsi_analyserCleAcsol2">
      <resultMessage>The variable pm_reel must be declared.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DATA.Declaration">
    <result resultId="376" fileName="cps_acsol2.F90" resultLine="373" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>The variable ifdef must be declared.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DATA.Declaration">
    <result resultId="377" fileName="cps_acsol2.F90" resultLine="373" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>The variable gfortran__ must be declared.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DATA.Declaration">
    <result resultId="378" fileName="cps_acsol2.F90" resultLine="747" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The variable acc_ok must be declared.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DATA.Declaration">
    <result resultId="379" fileName="cps_acsol2.F90" resultLine="752" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>The variable boucle_struct must be declared.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DATA.Declaration">
    <result resultId="380" fileName="cps_math_tchcal.F90" resultLine="172" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The variable fct must be declared.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DATA.Declaration">
    <result resultId="381" fileName="cps_math_tchcal.F90" resultLine="206" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The variable go must be declared.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DATA.Declaration">
    <result resultId="382" fileName="cps_math_tchcal.F90" resultLine="206" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The variable to must be declared.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DATA.Declaration">
    <result resultId="383" fileName="cps_math_tchcal.F90" resultLine="220" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The variable d0 must be declared.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DESIGN.IO">
    <result resultId="384" fileName="cps_acsol2.F90" resultLine="374" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>The value of the logic unity should be a integer or a variable initialised directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DESIGN.IO">
    <result resultId="385" fileName="cps_acsol2.F90" resultLine="377" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>The value of the logic unity should be a integer or a variable initialised directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DESIGN.IO">
    <result resultId="386" fileName="cps_acsol2.F90" resultLine="422" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>The value of the logic unity should be a integer or a variable initialised directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.DESIGN.IO">
    <result resultId="387" fileName="cps_acsol2.F90" resultLine="424" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>The value of the logic unity should be a integer or a variable initialised directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.ERR.Allocate">
    <result resultId="388" fileName="cps_acsol2.F90" resultLine="355" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>The status of the ALLOCATE or DEALLOCATE instruction is not checked</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.ERR.Allocate">
    <result resultId="389" fileName="cps_acsol2.F90" resultLine="418" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>The status of the ALLOCATE or DEALLOCATE instruction is not checked</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.ERR.Allocate">
    <result resultId="390" fileName="cps_acsol2.F90" resultLine="564" resultTypePlace="method" resultNamePlace="Module">
      <resultMessage>The status of the ALLOCATE or DEALLOCATE instruction is not checked</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.ERR.Allocate">
    <result resultId="391" fileName="cps_acsol2.F90" resultLine="566" resultTypePlace="method" resultNamePlace="Module">
      <resultMessage>The status of the ALLOCATE or DEALLOCATE instruction is not checked</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.ERR.Allocate">
    <result resultId="392" fileName="cps_acsol2.F90" resultLine="573" resultTypePlace="method" resultNamePlace="Module">
      <resultMessage>The status of the ALLOCATE or DEALLOCATE instruction is not checked</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.ERR.Allocate">
    <result resultId="393" fileName="cps_acsol2.F90" resultLine="574" resultTypePlace="method" resultNamePlace="Module">
      <resultMessage>The status of the ALLOCATE or DEALLOCATE instruction is not checked</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.ERR.Allocate">
    <result resultId="394" fileName="cps_acsol2.F90" resultLine="575" resultTypePlace="method" resultNamePlace="Module">
      <resultMessage>The status of the ALLOCATE or DEALLOCATE instruction is not checked</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.ERR.OpenRead">
    <result resultId="395" fileName="cps_acsol2.F90" resultLine="374" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>The return of IOSTAT is no checked in the OPEN instruction.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.ERR.OpenRead">
    <result resultId="396" fileName="cps_acsol2.F90" resultLine="390" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>There is no parameter IOSTAT in the READ instruction.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.ERR.OpenRead">
    <result resultId="397" fileName="cps_acsol2.F90" resultLine="422" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>There is no parameter IOSTAT in the OPEN instruction.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.ERR.OpenRead">
    <result resultId="398" fileName="cps_acsol2.F90" resultLine="424" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>There is no parameter IOSTAT in the OPEN instruction.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.ERR.OpenRead">
    <result resultId="399" fileName="cps_acsol2.F90" resultLine="428" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>There is no parameter IOSTAT in the READ instruction.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Associated">
    <result resultId="400" fileName="cps_acsol2.F90" resultLine="355" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>The pointer (tab_lignes) is not set to null before the use of the instruction ASSOCIATED.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Associated">
    <result resultId="401" fileName="cps_acsol2.F90" resultLine="355" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>The pointer (tab_lignes) is not set to null before the use of the instruction ASSOCIATED.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Associated">
    <result resultId="402" fileName="cps_acsol2.F90" resultLine="564" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>The pointer (tab_date) is not set to null before the use of the instruction ASSOCIATED.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Associated">
    <result resultId="403" fileName="cps_acsol2.F90" resultLine="564" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>The pointer (tab_date) is not set to null before the use of the instruction ASSOCIATED.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Associated">
    <result resultId="404" fileName="cps_acsol2.F90" resultLine="565" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>The pointer (tab_data_flux) is not set to null before the use of the instruction ASSOCIATED.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Associated">
    <result resultId="405" fileName="cps_acsol2.F90" resultLine="565" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>The pointer (tab_data_flux) is not set to null before the use of the instruction ASSOCIATED.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Associated">
    <result resultId="406" fileName="cps_acsol2.F90" resultLine="566" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>The pointer (tab_data_ia) is not set to null before the use of the instruction ASSOCIATED.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Associated">
    <result resultId="407" fileName="cps_acsol2.F90" resultLine="566" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>The pointer (tab_data_ia) is not set to null before the use of the instruction ASSOCIATED.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.If">
    <result resultId="408" fileName="cps_acsol2.F90" resultLine="355" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>Logical IF (without THEN and ENDIF) is only allowed with EXIT, CYCLE, GOTO, RETURN statements.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.If">
    <result resultId="409" fileName="cps_acsol2.F90" resultLine="564" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>Logical IF (without THEN and ENDIF) is only allowed with EXIT, CYCLE, GOTO, RETURN statements.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.If">
    <result resultId="410" fileName="cps_acsol2.F90" resultLine="565" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>Logical IF (without THEN and ENDIF) is only allowed with EXIT, CYCLE, GOTO, RETURN statements.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.If">
    <result resultId="411" fileName="cps_acsol2.F90" resultLine="566" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>Logical IF (without THEN and ENDIF) is only allowed with EXIT, CYCLE, GOTO, RETURN statements.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Intent">
    <result resultId="412" fileName="cps_math_tchcal.F90" resultLine="168" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>It misses the attribute INTENT for the parameter tdeb</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Intent">
    <result resultId="413" fileName="cps_math_tchcal.F90" resultLine="168" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>It misses the attribute INTENT for the parameter tfin</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Intent">
    <result resultId="414" fileName="cps_math_tchcal.F90" resultLine="168" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>It misses the attribute INTENT for the parameter rtab</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Intent">
    <result resultId="415" fileName="cps_math_tchcal.F90" resultLine="168" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>It misses the attribute INTENT for the parameter ctch</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Intent">
    <result resultId="416" fileName="cps_math_tchcal.F90" resultLine="170" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>It misses the attribute INTENT for the parameter ndeg</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Intent">
    <result resultId="417" fileName="cps_math_tchcal.F90" resultLine="170" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>It misses the attribute INTENT for the parameter nparam</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Intent">
    <result resultId="418" fileName="cps_math_tchcal.F90" resultLine="170" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>It misses the attribute INTENT for the parameter ncmax</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Intent">
    <result resultId="419" fileName="cps_math_tchcal.F90" resultLine="170" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>It misses the attribute INTENT for the parameter ier</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Nullify">
    <result resultId="420" fileName="cps_acsol2.F90" resultLine="355" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>It misses the instruction NULLIFY after the DEALLOCATION of tab_lignes</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Nullify">
    <result resultId="421" fileName="cps_acsol2.F90" resultLine="564" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>It misses the instruction NULLIFY after the DEALLOCATION of tab_date</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Nullify">
    <result resultId="422" fileName="cps_acsol2.F90" resultLine="565" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>It misses the instruction NULLIFY after the DEALLOCATION of tab_data_flux</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Nullify">
    <result resultId="423" fileName="cps_acsol2.F90" resultLine="566" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>It misses the instruction NULLIFY after the DEALLOCATION of tab_data_ia</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Operator">
    <result resultId="424" fileName="cps_acsol2.F90" resultLine="188" resultTypePlace="method" resultNamePlace="function cpsi_getCleAcsol2">
      <resultMessage>The symbolic notation (==, /=, &lt;=, &lt;, &gt;=, &gt;) must be used instead of (.EQ., .NE., .LT., .LE., .GT., .GE.). Error in .eq.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Operator">
    <result resultId="425" fileName="cps_acsol2.F90" resultLine="192" resultTypePlace="method" resultNamePlace="function cpsi_getCleAcsol2">
      <resultMessage>The symbolic notation (==, /=, &lt;=, &lt;, &gt;=, &gt;) must be used instead of (.EQ., .NE., .LT., .LE., .GT., .GE.). Error in .eq.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Operator">
    <result resultId="426" fileName="cps_acsol2.F90" resultLine="366" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>The symbolic notation (==, /=, &lt;=, &lt;, &gt;=, &gt;) must be used instead of (.EQ., .NE., .LT., .LE., .GT., .GE.). Error in .lt.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Operator">
    <result resultId="427" fileName="cps_math_tchcal.F90" resultLine="202" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The symbolic notation (==, /=, &lt;=, &lt;, &gt;=, &gt;) must be used instead of (.EQ., .NE., .LT., .LE., .GT., .GE.). Error in .lt.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Operator">
    <result resultId="428" fileName="cps_math_tchcal.F90" resultLine="209" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The symbolic notation (==, /=, &lt;=, &lt;, &gt;=, &gt;) must be used instead of (.EQ., .NE., .LT., .LE., .GT., .GE.). Error in .gt.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.INST.Operator">
    <result resultId="429" fileName="cps_math_tchcal.F90" resultLine="236" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>The symbolic notation (==, /=, &lt;=, &lt;, &gt;=, &gt;) must be used instead of (.EQ., .NE., .LT., .LE., .GT., .GE.). Error in .ne.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.NAME.KeyWords">
    <result resultId="430" fileName="cps_acsol2.F90" resultLine="178" resultTypePlace="method" resultNamePlace="function cpsi_getCleAcsol2">
      <resultMessage>The variable min is a keyword in Fortran90 language.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.NAME.KeyWords">
    <result resultId="431" fileName="cps_acsol2.F90" resultLine="256" resultTypePlace="method" resultNamePlace="subroutine cpsi_analyserCleAcsol2">
      <resultMessage>The variable min is a keyword in Fortran90 language.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.REF.Open">
    <result resultId="432" fileName="cps_acsol2.F90" resultLine="375" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>It misses one or more parameters in OPEN instruction. Mandato-ry parameters are FILE, STATUS, IOSTAT, POSITION.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.REF.Open">
    <result resultId="433" fileName="cps_acsol2.F90" resultLine="378" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>It misses one or more parameters in OPEN instruction. Mandato-ry parameters are FILE, STATUS, IOSTAT, POSITION.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.REF.Open">
    <result resultId="434" fileName="cps_acsol2.F90" resultLine="422" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>It misses one or more parameters in OPEN instruction. Mandato-ry parameters are FILE, STATUS, IOSTAT, POSITION.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.REF.Open">
    <result resultId="435" fileName="cps_acsol2.F90" resultLine="424" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>It misses one or more parameters in OPEN instruction. Mandato-ry parameters are FILE, STATUS, IOSTAT, POSITION.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="436" fileName="cps_acsol2.F90" resultLine="118" resultTypePlace="method" resultNamePlace="module cps_acsol2">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of julsol, indflu, indgeo, iaamoy, iapmoy, iaampr</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="437" fileName="cps_acsol2.F90" resultLine="119" resultTypePlace="method" resultNamePlace="module cps_acsol2">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of iaa, iap, iaapr</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="438" fileName="cps_acsol2.F90" resultLine="121" resultTypePlace="method" resultNamePlace="module cps_acsol2">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of tmp</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="439" fileName="cps_acsol2.F90" resultLine="178" resultTypePlace="method" resultNamePlace="function cpsi_getCleAcsol2">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of jj, mm, aaaa, h, min</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="440" fileName="cps_acsol2.F90" resultLine="256" resultTypePlace="method" resultNamePlace="subroutine cpsi_analyserCleAcsol2">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of jj, mm, aaaa, h, min</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="441" fileName="cps_acsol2.F90" resultLine="258" resultTypePlace="method" resultNamePlace="subroutine cpsi_analyserCleAcsol2">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of retour</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="442" fileName="cps_acsol2.F90" resultLine="335" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of nb_lignes</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="443" fileName="cps_acsol2.F90" resultLine="340" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of ierr, ierr2</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="444" fileName="cps_acsol2.F90" resultLine="341" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of i</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="445" fileName="cps_acsol2.F90" resultLine="342" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of unit_read, ligne</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="446" fileName="cps_acsol2.F90" resultLine="350" resultTypePlace="method" resultNamePlace="subroutine cps_lireAcsol2">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of indice_ligne_nok</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="447" fileName="cps_acsol2.F90" resultLine="553" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of tab_data_ia</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="448" fileName="cps_acsol2.F90" resultLine="554" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of nb_data</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="449" fileName="cps_acsol2.F90" resultLine="560" resultTypePlace="method" resultNamePlace="subroutine cps_lireEtConvertirAcsol2">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of ligne, nb_lignes</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="450" fileName="cps_acsol2.F90" resultLine="710" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of indice_depart</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="451" fileName="cps_acsol2.F90" resultLine="711" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of nb_data</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="452" fileName="cps_acsol2.F90" resultLine="714" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of tab_data_ia</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="453" fileName="cps_acsol2.F90" resultLine="719" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of acces</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="454" fileName="cps_acsol2.F90" resultLine="720" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of ret, ii</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="455" fileName="cps_acsol2.F90" resultLine="722" resultTypePlace="method" resultNamePlace="subroutine cpsi_lire_unficAcsol2_MADONA">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of nature</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="456" fileName="cps_acsol2.F90" resultLine="895" resultTypePlace="method" resultNamePlace="subroutine cpsi_cpter_lignes_Acsol2_MADONA">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of nb_data</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="457" fileName="cps_acsol2.F90" resultLine="900" resultTypePlace="method" resultNamePlace="subroutine cpsi_cpter_lignes_Acsol2_MADONA">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of acces</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="458" fileName="cps_acsol2.F90" resultLine="901" resultTypePlace="method" resultNamePlace="subroutine cpsi_cpter_lignes_Acsol2_MADONA">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of ret, ii</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="459" fileName="cps_acsol2.F90" resultLine="903" resultTypePlace="method" resultNamePlace="subroutine cpsi_cpter_lignes_Acsol2_MADONA">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of nature</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="460" fileName="cps_math_tchcal.F90" resultLine="170" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of ndeg, nparam, ncmax, ier</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="461" fileName="cps_math_tchcal.F90" resultLine="179" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of ntrav</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="462" fileName="cps_math_tchcal.F90" resultLine="183" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of ik</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="463" fileName="cps_math_tchcal.F90" resultLine="184" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of i, ind, ku</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="464" fileName="cps_math_tchcal.F90" resultLine="185" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of ntot, nn</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="F90.TYPE.Integer">
    <result resultId="465" fileName="cps_math_tchcal.F90" resultLine="186" resultTypePlace="method" resultNamePlace="subroutine cps_math_tchcal">
      <resultMessage>It misses the declaration SELECTED_INT_KIND in the initialisation of ierr</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.ComplexitySimplified">
    <result resultId="466" fileName="activatesyncTMArchive.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="7.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.ComplexitySimplified">
    <result resultId="467" fileName="activatesyncTMArchive.sh" resultLine="0" resultTypePlace="class" resultValue="7.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.ComplexitySimplified">
    <result resultId="468" fileName="activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="7.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.ComplexitySimplified">
    <result resultId="469" fileName="activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="class" resultValue="7.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.ComplexitySimplified">
    <result resultId="470" fileName="cat-scan-script.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.ComplexitySimplified">
    <result resultId="471" fileName="cat-scan-script.sh" resultLine="0" resultTypePlace="class" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.Nesting">
    <result resultId="472" fileName="activatesyncTMArchive.sh" resultLine="1" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="2.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.Nesting">
    <result resultId="473" fileName="activatesyncTMArchive.sh" resultLine="0" resultTypePlace="class" resultValue="2.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.Nesting">
    <result resultId="474" fileName="activatesyncbackupnominal.sh" resultLine="1" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="2.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.Nesting">
    <result resultId="475" fileName="activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="class" resultValue="2.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.Nesting">
    <result resultId="476" fileName="cat-scan-script.sh" resultLine="1" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="0.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.Nesting">
    <result resultId="477" fileName="cat-scan-script.sh" resultLine="0" resultTypePlace="class" resultValue="0.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.LineOfCode">
    <result resultId="478" fileName="activatesyncTMArchive.sh" resultLine="1" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="17.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.LineOfCode">
    <result resultId="479" fileName="activatesyncTMArchive.sh" resultLine="0" resultTypePlace="class" resultValue="17.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.LineOfCode">
    <result resultId="480" fileName="activatesyncbackupnominal.sh" resultLine="1" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="17.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.LineOfCode">
    <result resultId="481" fileName="activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="class" resultValue="17.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.LineOfCode">
    <result resultId="482" fileName="cat-scan-script.sh" resultLine="1" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.LineOfCode">
    <result resultId="483" fileName="cat-scan-script.sh" resultLine="0" resultTypePlace="class" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.RatioComment">
    <result resultId="484" fileName="activatesyncTMArchive.sh" resultLine="1" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="60.465115" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.RatioComment">
    <result resultId="485" fileName="activatesyncTMArchive.sh" resultLine="0" resultTypePlace="class" resultValue="60.465115" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.RatioComment">
    <result resultId="486" fileName="activatesyncbackupnominal.sh" resultLine="1" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="60.465115" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.RatioComment">
    <result resultId="487" fileName="activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="class" resultValue="60.465115" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.RatioComment">
    <result resultId="488" fileName="cat-scan-script.sh" resultLine="1" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="NaN" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.RatioComment">
    <result resultId="489" fileName="cat-scan-script.sh" resultLine="0" resultTypePlace="class" resultValue="NaN" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.LineOfComment">
    <result resultId="490" fileName="activatesyncTMArchive.sh" resultLine="1" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="26.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.LineOfComment">
    <result resultId="491" fileName="activatesyncTMArchive.sh" resultLine="0" resultTypePlace="class" resultValue="26.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.LineOfComment">
    <result resultId="492" fileName="activatesyncbackupnominal.sh" resultLine="1" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="26.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.LineOfComment">
    <result resultId="493" fileName="activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="class" resultValue="26.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.LineOfComment">
    <result resultId="494" fileName="cat-scan-script.sh" resultLine="1" resultTypePlace="method" resultNamePlace="MAIN PROGRAM" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="SH.MET.LineOfComment">
    <result resultId="495" fileName="cat-scan-script.sh" resultLine="0" resultTypePlace="class" resultValue="1.0" />
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.Invariant">
    <result resultId="496" fileName="activatesyncTMArchive.sh" resultLine="29" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The variable STARTTIME should be declared constant</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.Invariant">
    <result resultId="497" fileName="activatesyncbackupnominal.sh" resultLine="29" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The variable STARTTIME should be declared constant</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.NotUsed">
    <result resultId="498" fileName="cat-scan-script.sh" resultLine="3" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The variable $settings is declared and not used.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.DATA.NotUsed">
    <result resultId="499" fileName="cat-scan-script.sh" resultLine="3" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The variable $projectBaseDir is declared and not used.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FileExistence">
    <result resultId="500" fileName="activatesyncTMArchive.sh" resultLine="42" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The existence of the file fakesyncTMArchive.sh has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FileExistence">
    <result resultId="501" fileName="activatesyncTMArchive.sh" resultLine="43" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The existence of the file syncTMArchive.sh has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FileExistence">
    <result resultId="502" fileName="activatesyncbackupnominal.sh" resultLine="42" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The existence of the file fakesyncbackupnominal.sh has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FileExistence">
    <result resultId="503" fileName="activatesyncbackupnominal.sh" resultLine="43" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The existence of the file syncbackupnominal.sh has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FileExistence">
    <result resultId="504" fileName="cat-scan-script.sh" resultLine="3" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The existence of the file .cat-sonar-scanner.log has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="505" fileName="activatesyncTMArchive.sh" resultLine="29" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is forbidden to use a file name such as date directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="506" fileName="activatesyncTMArchive.sh" resultLine="34" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is forbidden to use a file name such as ./fakesyncTMArchive.sh directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="507" fileName="activatesyncTMArchive.sh" resultLine="35" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is forbidden to use a file name such as ./truesyncTMArchive.sh directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="508" fileName="activatesyncTMArchive.sh" resultLine="41" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is forbidden to use a file name such as ./truesyncTMArchive.sh directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="509" fileName="activatesyncTMArchive.sh" resultLine="42" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is forbidden to use a file name such as fakesyncTMArchive.sh directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="510" fileName="activatesyncTMArchive.sh" resultLine="43" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is forbidden to use a file name such as syncTMArchive.sh directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="511" fileName="activatesyncbackupnominal.sh" resultLine="29" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is forbidden to use a file name such as date directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="512" fileName="activatesyncbackupnominal.sh" resultLine="34" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is forbidden to use a file name such as ./fakesyncbackupnominal.sh directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="513" fileName="activatesyncbackupnominal.sh" resultLine="35" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is forbidden to use a file name such as ./truesyncbackupnominal.sh directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="514" fileName="activatesyncbackupnominal.sh" resultLine="41" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is forbidden to use a file name such as ./truesyncbackupnominal.sh directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="515" fileName="activatesyncbackupnominal.sh" resultLine="42" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is forbidden to use a file name such as fakesyncbackupnominal.sh directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="516" fileName="activatesyncbackupnominal.sh" resultLine="43" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is forbidden to use a file name such as syncbackupnominal.sh directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="517" fileName="cat-scan-script.sh" resultLine="3" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is forbidden to use a file name such as /media/sf_Shared/icode/sonar-project.properties directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="518" fileName="cat-scan-script.sh" resultLine="3" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is forbidden to use a file name such as /media/sf_Shared/icode directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.FLOW.FilePath">
    <result resultId="519" fileName="cat-scan-script.sh" resultLine="3" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is forbidden to use a file name such as .cat-sonar-scanner.log directly.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="520" fileName="activatesyncTMArchive.sh" resultLine="38" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="521" fileName="activatesyncTMArchive.sh" resultLine="38" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="522" fileName="activatesyncTMArchive.sh" resultLine="38" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>This line is not indented in comparison with the last one.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="523" fileName="activatesyncTMArchive.sh" resultLine="41" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="524" fileName="activatesyncTMArchive.sh" resultLine="41" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>This line is not indented in comparison with the last one.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="525" fileName="activatesyncTMArchive.sh" resultLine="42" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="526" fileName="activatesyncTMArchive.sh" resultLine="42" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="527" fileName="activatesyncTMArchive.sh" resultLine="43" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="528" fileName="activatesyncTMArchive.sh" resultLine="43" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="529" fileName="activatesyncTMArchive.sh" resultLine="44" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="530" fileName="activatesyncTMArchive.sh" resultLine="44" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="531" fileName="activatesyncTMArchive.sh" resultLine="45" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="532" fileName="activatesyncTMArchive.sh" resultLine="45" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>This line is not aligned with its corresponding structure.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="533" fileName="activatesyncTMArchive.sh" resultLine="47" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="534" fileName="activatesyncTMArchive.sh" resultLine="47" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>This line is not indented in comparison with the last one.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="535" fileName="activatesyncbackupnominal.sh" resultLine="38" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="536" fileName="activatesyncbackupnominal.sh" resultLine="38" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="537" fileName="activatesyncbackupnominal.sh" resultLine="38" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>This line is not indented in comparison with the last one.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="538" fileName="activatesyncbackupnominal.sh" resultLine="41" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="539" fileName="activatesyncbackupnominal.sh" resultLine="41" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>This line is not indented in comparison with the last one.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="540" fileName="activatesyncbackupnominal.sh" resultLine="42" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="541" fileName="activatesyncbackupnominal.sh" resultLine="42" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="542" fileName="activatesyncbackupnominal.sh" resultLine="43" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="543" fileName="activatesyncbackupnominal.sh" resultLine="43" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="544" fileName="activatesyncbackupnominal.sh" resultLine="44" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="545" fileName="activatesyncbackupnominal.sh" resultLine="44" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="546" fileName="activatesyncbackupnominal.sh" resultLine="45" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="547" fileName="activatesyncbackupnominal.sh" resultLine="45" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>This line is not aligned with its corresponding structure.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="548" fileName="activatesyncbackupnominal.sh" resultLine="47" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>Tabulations are not allowed.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.Indent">
    <result resultId="549" fileName="activatesyncbackupnominal.sh" resultLine="47" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>This line is not indented in comparison with the last one.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.LengthLine">
    <result resultId="550" fileName="activatesyncTMArchive.sh" resultLine="36" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>There are more than 100 characters in this line.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.LengthLine">
    <result resultId="551" fileName="activatesyncTMArchive.sh" resultLine="46" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>There are more than 100 characters in this line.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.LengthLine">
    <result resultId="552" fileName="activatesyncbackupnominal.sh" resultLine="36" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>There are more than 100 characters in this line.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="COM.PRES.LengthLine">
    <result resultId="553" fileName="activatesyncbackupnominal.sh" resultLine="46" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>There are more than 100 characters in this line.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.DESIGN.Options">
    <result resultId="554" fileName="activatesyncTMArchive.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is mandatory to use getopts and getopt and to provide the –h, -help, –v and -version options at least.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.DESIGN.Options">
    <result resultId="555" fileName="activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is mandatory to use getopts and getopt and to provide the –h, -help, –v and -version options at least.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.DESIGN.Options">
    <result resultId="556" fileName="cat-scan-script.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is mandatory to use getopts and getopt and to provide the –h, -help, –v and -version options at least.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.ERR.Help">
    <result resultId="557" fileName="activatesyncTMArchive.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The help options (-h &amp; --help) must be implemented in getopt &amp; getopts commands.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.ERR.Help">
    <result resultId="558" fileName="activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The help options (-h &amp; --help) must be implemented in getopt &amp; getopts commands.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.ERR.Help">
    <result resultId="559" fileName="cat-scan-script.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The help options (-h &amp; --help) must be implemented in getopt &amp; getopts commands.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.FLOW.CheckCodeReturn">
    <result resultId="560" fileName="activatesyncTMArchive.sh" resultLine="42" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The return status of function mv has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.FLOW.CheckCodeReturn">
    <result resultId="561" fileName="activatesyncTMArchive.sh" resultLine="43" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The return status of function mv has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.FLOW.CheckCodeReturn">
    <result resultId="562" fileName="activatesyncbackupnominal.sh" resultLine="42" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The return status of function mv has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.FLOW.CheckCodeReturn">
    <result resultId="563" fileName="activatesyncbackupnominal.sh" resultLine="43" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The return status of function mv has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.FLOW.CheckCodeReturn">
    <result resultId="564" fileName="cat-scan-script.sh" resultLine="2" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The return status of function cd has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.FLOW.CheckCodeReturn">
    <result resultId="565" fileName="cat-scan-script.sh" resultLine="3" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The return status of function cat has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.FLOW.CheckUser">
    <result resultId="566" fileName="activatesyncTMArchive.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The user has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.FLOW.CheckUser">
    <result resultId="567" fileName="activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The user has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.FLOW.CheckUser">
    <result resultId="568" fileName="cat-scan-script.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The user has not been checked.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.INST.GetOpts">
    <result resultId="569" fileName="activatesyncTMArchive.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is mandatory to use getopts &amp; getopt in the script.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.INST.GetOpts">
    <result resultId="570" fileName="activatesyncbackupnominal.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is mandatory to use getopts &amp; getopt in the script.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.INST.GetOpts">
    <result resultId="571" fileName="cat-scan-script.sh" resultLine="0" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>It is mandatory to use getopts &amp; getopt in the script.</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.INST.Variables">
    <result resultId="572" fileName="activatesyncTMArchive.sh" resultLine="32" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The variable $STARTTIME is not correctly used (must be used with the ${ } or " " notation )</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.INST.Variables">
    <result resultId="573" fileName="activatesyncbackupnominal.sh" resultLine="32" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The variable $STARTTIME is not correctly used (must be used with the ${ } or " " notation )</resultMessage>
    </result>
  </analysisRule>
  <analysisRule analysisRuleId="SH.IO.Redirect">
    <result resultId="574" fileName="cat-scan-script.sh" resultLine="3" resultTypePlace="method" resultNamePlace="MAIN PROGRAM">
      <resultMessage>The non-standard redirection /media/sf_Shared/icode &amp;&gt; .cat-sonar-scanner.log must be preceded by a comment.</resultMessage>
    </result>
  </analysisRule>
</analysisProject>

# Search ICode metrics tests 
> grep "F77.MET" globalTestValidated_*.txt
globalTestValidated_1.txt:F77.MET.Line clanhb.f 1
globalTestValidated_1.txt:F77.MET.Line clantb.f 1
globalTestValidated_1.txt:F77.MET.Line clarft.f 1
globalTestValidated_3.txt:F77.MET.Line slansp.f 2
globalTestValidated_4.txt:F77.MET.Line zlarft.f 1

# Run ICode analysis
../icode/icode -f xml -output clanhb.res.xml f77_1/clanhb.f
../icode/icode -f xml -output clantb.res.xml f77_1/clantb.f
../icode/icode -f xml -output clarft.res.xml f77_1/clarft.f 
../icode/icode -f xml -output slansp.res.xml f77_3/slansp.f
../icode/icode -f xml -output zlarft.res.xml f77_4/zlarft.f 

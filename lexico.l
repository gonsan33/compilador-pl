package alex;

%%
%char
%line
%column
%class AnalizadorLexicoTiny
%public
%cup
%unicode

%{
  private ALexOperations ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
digito = [0-9]

separador = [ \t\r\b\n]
comentario = \"[^\"]*\"


numeroEntero = [\+\-]?{digito}{digito}*
operadorSuma = \+
operadorResta = \-
operadorMultiplicacion = \*
operadorDivision = \/
parentesisApertura = \(
parentesisCierre = \)
corcheteApertura = \[
corcheteCierre = \] 
llaveApertura = \{
llaveCierre = \}
dosPuntos = \: 
functionDef = fn 
varDef = var 
coma  = ,
puntoComa = \;

operadorIgual = \=\=
operadorMayorIgual = \>\= 
operadorMayor = \>
operadorMenorIgual = \<\= 
operadorMenor = \<
operadorAmpersand = \&
operadorDolar = \$
operadorAnd = and
operadorOr = or
operadorNot = not
operadorMod = mod 
operadorAsignacion = \=



reservadoIf = if 
reservadoElse = else 
reservadoFor = for 
reservadoWhile = while 
reservadoReturn = return 
reservadoNew = new
reservadoDelete = delete
reservadoBool = bool
reservadoInt = int 
reservadoType = type  
reservadoStruct = struct 
reservadoTrue = true 
reservadoFalse = false 

reservadoPrint  = print
reservadoScan = scan

identificador = ({letra}| _) ({letra}| _ |{digito})*






%%
{separador}               {}
{comentario}              {}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMultiplicacion}  {return ops.unidadMul();}
{operadorDivision}        {return ops.unidadDiv();}
{numeroEntero}            {return ops.unidadEnt();}
{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCierre();} 
{corcheteApertura}        {return ops.unidadCAp();}
{corcheteCierre}          {return ops.unidadCCierre();} 
{llaveApertura}          {return ops.unidadLLAp();} 
{llaveCierre}          {return ops.unidadLLCierre();} 
{dosPuntos}               {return ops.unidadDosPuntos();} 
{functionDef}             {return ops.unidadFnDef();} 
{varDef}                  {return ops.unidadVarDef();} 
{operadorIgual}                   {return ops.unidadIgual();} 
{coma}                    {return ops.unidadComa();}
{puntoComa}               {return ops.unidadPuntoComa();}
{operadorMayorIgual}      {return ops.unidadMayorIgual();}
{operadorMayor}           {return ops.unidadMayor();}
{operadorMenorIgual}      {return ops.unidadMenorIgual();}
{operadorMenor}           {return ops.unidadMenor();}
{operadorAmpersand}       {return ops.unidadAmpersand();}
{operadorDolar}           {return ops.unidadDolar();}
{operadorAnd}             {return ops.unidadAnd();}
{operadorOr}             {return ops.unidadOr();}
{operadorNot}             {return ops.unidadNot();}
{operadorMod}             {return ops.unidadMod();}
{operadorAsignacion}             {return ops.unidadAsign();}
{reservadoIf}             {return ops.unidadIf();}
{reservadoElse}             {return ops.unidadElse();}
{reservadoFor}             {return ops.unidadFor();}

{reservadoWhile}             {return ops.unidadWhile();}


{reservadoReturn}             {return ops.unidadReturn();}

{reservadoNew } {return ops.unidadNew();}
{reservadoDelete } {return ops.unidadDelete();}
{reservadoBool} {return ops.unidadBool();}
{reservadoInt } {return ops.unidadInt();}
{reservadoType } {return ops.unidadType();}
{reservadoStruct} {return ops.unidadStruct();}
{reservadoTrue } {return ops.unidadTrue();}
{reservadoFalse} {return ops.unidadFalse();}

{reservadoPrint } {return ops.unidadPrint();}
{reservadoScan}   {return ops.unidadScan();}

{identificador}           {return ops.unidadId();}

[^]                       {ops.error();}  
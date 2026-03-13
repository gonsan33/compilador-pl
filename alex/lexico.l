package alex;

%%
%char
%line
%column
%class AnalizadorLexicoTiny
%type  UnidadLexica
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
comentario = "[^"]*"


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
operadorIgual = \=\=
coma  = ,
puntoComa = \;
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

identificador = ({letra}| _) ({letra}| _ |{digito})*






%%
{separador}               {}
{comentario}              {}
{identificador}           {return ops.unidadId();}
{numeroEntero}            {return ops.unidadEnt();}
{numeroReal}              {return ops.unidadReal();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMultiplicacion}  {return ops.unidadMul();}
{operadorDivision}        {return ops.unidadDiv();}
{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCierre();} 
{corcheteApertura}        {return ops.unidadCAp();}
{corcheteCierre}          {return ops.unidadCCierre();} 
{dosPuntos}               {return ops.unidadDosPuntos();} 
{functionDef}             {return ops.unidadFnDef();} 
{varDef}                  {return ops.unidadVarDef();} 
{igual}                   {return ops.unidadIgual();} 
{coma}                    {return ops.unidadComa();}
{puntoComa}               {return ops.unidadPuntoComa();}
{operadorAmpersand}       {return ops.unidadAmpersand();}
{operadorDolar}           {return ops.unidadDolar();}
{operadorAnd}             {return ops.unidadAnd();}



{operadorOr}             {return ops.unidadAnd();}

{operadorNot}             {return ops.unidadAnd();}

{operadorMod}             {return ops.unidadAnd();}


{operadorAsignacion}             {return ops.unidadAnd();}


{reservadoIf}             {return ops.unidadAnd();}

{reservadoElse}             {return ops.unidadAnd();}

{reservadoFor}             {return ops.unidadAnd();}

{reservadoWhile}             {return ops.unidadAnd();}


{reservadoReturn}             {return ops.unidadAnd();}

{reservadoNew } {return ops.unidadAnd();}
{reservadoDelete } {return ops.unidadAnd();}
{reservadoBool} {return ops.unidadAnd();}
{reservadoInt } {return ops.unidadAnd();}
{reservadoType } {return ops.unidadAnd();}
{reservadoStruct} {return ops.unidadAnd();}
{reservadoTrue } {return ops.unidadAnd();}
{reservadoFalse} {return ops.unidadAnd();}

[^]                       {ops.error();}  

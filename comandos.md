# Comandos importantes


## Sintactico
Para el sintactico hacer por sistema:
```
java -cp cup.jar java_cup.Main -parser AnalizadorSintacticoTiny -symbols ClaseLexica -nopositions Tiny.cup

```

Luego borras de la carpeta asint el clase lexica y el analizadorsintactico y los sustituyes. Luego hacemos:

```
javac -cp ".;cup.jar" */*.java

```

Y por ultimo:

```
java -cp ".;cup.jar" asint.Main prueba_sintactico_javaescrig.txt

```

## Léxico
Vamos ahora a ver lo que hay que hacer con el lexico:
 Comenzamos haciendo:

 ```
 java -cp jflex.jar jflex.Main lexico.l
 ```
 Nos generara un AnalizadorLexicoTiny que debremos sustituir en la carpeta alex por el antiguo (Si no os va mirad q los .java tengan la cabecera de su paquete): 

 ```
 javac -cp ".;cup.jar" alex/*.java asint/*.java
 ```
 Ahora, si quieres comprobar que el lexico va bn, haz: 

 ```
java -cp ".;cup.jar" alex.Main prueba_lexico.txt
 ```

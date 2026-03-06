# PLG Práctica - Documentación

**Autores:** *Gonzalo Sánchez Montesinos, Daniel Bordeianu Dascal  y Víctor Gómez Torres*

**Nombre del lenguaje:** JavaEscrig

En este documento, concretaremos cómo será la “sintaxis” de nuestro lenguaje, especificando las diferentes funcionalidades con las que va a contar. Todas las instrucciones terminarán en punto y coma. ;

Para declarar las variables lo haremos de esta manera, no sería obligatorio inicializar la variable.

## Declaraciones de variables y de funciones 
### Declaraciones de variables
```jsx
var identificador : tipo;
```

Las asignaciones las haremos con el operador `=`  y no son obligatorias en la declaración.

Vamos a incluir los siguientes tipos: Números enteros, que los llamaremos `int`, booleanos, que los llamaremos `bool`.

```jsx
//Algún ejemplo muy sencillo
var a : int;
var b : bool;
var c : int = 4; ""Con valor inicial
var miBooleano : bool = false ;
```

Nuestros bloques empezarán siempre con `{` y acabarán con `}` , y se permitirán bloques anidados. Trabajaremos en un contexto dinámico, es decir, se permite reutilizar identificadores. 

Los comentarios se escriben como  `“comentario”` y pueden ser multilínea. 
Vamos a poner un ejemplo “tonto”:

```jsx
var x : int = 2;
var y : int = 3;

y = x ;"y vale 2"

var x : int = 6;

y = x; "y vale 6"
```
### Bloques y ámbito de las declaraciones 
El siguiente ejemplo indica que se pueden emplear bloques dentro del código, el ámbito de las variables (shadowing): 
```jsx
var x : int = 5;

{
	var y : int = 3;
	y = x; "y vale 5"
	var x : int =3;
	y = x; "y vale 3, intuitivamente"
}
```

### Declaraciones de funciones
Para las funciones pondremos primero la palabra reservada fn, seguido del nombre de la función y , entre paréntesis, sus argumentos. Si devolviese algo también declaramos al final de esa línea su tipo justo detrás de dos puntos.

**Funciones que devuelven algo**: en principio, se pueden devolver elementos de cualquier tipo. 

```jsx
fn identificadorFuncion(argumentos) : Tipo {...} 
```

**Funciones que no devuelven nada**: 

```jsx
fn identificadorFuncion(argumentos) {...} 
```

La forma de pasar los argumentos por parámetro es la siguiente: 

```jsx
fn identificadorFuncion( argPorValor : Tipo, argPorReferencia: &Tipo) : Tipo {...} 
```

Para devolver algo, usamos la palabra `return`.

### Punteros 

Permitimos punteros, que se declaran de la siguiente manera: 

```jsx
var identificador: $Tipo; "puntero a una variable del tipo Tipo"
"Opcionalmente, se puede inicializar"
```

Para acceder al dato apuntado por el puntero se utiliza el operador y obtener la dirección de memoria de una variable: 

- `&`: acceder a la dirección de memoria de una variable
- `$`: acceder al valor de una dirección memoria

```jsx
var x: int = 5 ;
var miPuntero: $int = &x ;
var y: int = $miPuntero; "y vale 5"
```
#### Operadores new y delete

```jsx
var miPuntero: $int = new int; 

var puntero :$Tipo = new Tipo; 
```

## Definición de nuevos tipos, tipos estructurados. Tratamiento de Arrays
Para declarar los registros y definir tipos usaremos: 

### Definición de nuevos tipos
```jsx
type nombreTipo : DescripciónTipo; 

type nuevoEntero: int; 
```
### Definición de tipos estructurados
Para definir un tipo estructurado, se procede como sigue: 
```jsx
type struct_t : struct (x : int, y : int );

var miStruct: struct_t; "Variable del tipo declarado"
```
La definición de un nuevo tipo no es un alias, por lo que la variable miStruct y la siguiente variable **NO** tienen el mismo tipo: 
```jsx
var nuevoStruct: struct (x : int, y : int );
```

Se permite definir cualquier nuevo tipo: 
```jsx
type compuesto : struct (x : struct_t, y : int, z: struct_t );
```

### Arrays 

Se definen dando sus dimensiones y el tipo de sus datos. En concreto, para declarar un array se da el tipo y la **lista de dimensiones**. 

```jsx
var arrayUniDim : int [12] ;
var arrayMultiDim : int [12][23] ;
```

Acceso a elementos de un array: 

```jsx
arrayUniDim[0] ;
arrayMultiDim[0][0];
```

## Operadores

Los tipos ya los hemos definido antes, se permiten tipos de usuario tal y como se ha puesto en el ejemplo anterior.

para los operadores vamos a distinguir entre los unarios y los binarios:

### Operadores binarios

- Operador +: Se aplica sobre el tipo int. Coge dos números y los suma. Es asociativo por la izquierda.
- Operador *: Se aplica sobre el tipo int. Coge dos números y los multiplica. Es asociativo por la izquierda.
- Operador - : Se aplica sobre el tipo int. Coge dos números y los resta. Es asociativo por la izquierda.
- Operador /: Se aplica sobre el tipo int. Coge dos números y los divide, haciendo división entera. Es asociativo por la izquierda. OJO: El segundo argumento no puede ser 0.
- Operador mod: Se aplica sobre el tipo int. Coge dos números y hace primero módulo segundo. Es asociativo por la izquierda.
- Operador or : Se aplica sobre el tipo bool. Coge dos booleanos y les hace or. Es asociativo por la izquierda.
- Operador and : Se aplica sobre el tipo bool. Coge dos booleanos y les hace and. Es asociativo por la izquierda.
- Operador == (comparación).

### Operadores unarios

- Operador -: Se aplica sobre el tipo int. Asociativo.
- Operador not: se aplica sobre el tipo bool. Asociativo.
- Operador $: Se aplica sobre un tipo puntero, da el dato apuntado. Asociativo.
- Operador &: se aplica sobre una variable (identificador), da su dirección. No asocia, es un error.

Prioridades habituales. 




# Conjunto de Instrucciones

Las asignaciones ya están explicadas. 

## Condicionales

Se obliga a poner llaves. 

Una rama: 

```jsx
if (boolean_expr) { 
	""Cuerpo del if 
}  
```

Dos ramas: 

```jsx
if (boolean_expr) {
	""cuerpo del if 
} else { 
	""cuerpo del esle 

} 
```

Si nos da tiempo, queremos introducir también los switch-case, pero sólo si nos da tiempo. La sintaxis sería como la de C++, pero sin necesidad de utilizar break; Solo se ejecuta el código de la rama del switch que toque. 

## Bucle while

Se obliga a poner llaves. En principio, solo implementaremos el While.

```jsx
while(boolean_expr) {

}
```
## Bucle for
```jsx
for(declaración de variable opcional ; condición opcional; paso del bucle opcional) {}

for(;;){}

for(var x : int = 3 ;  x < 26; x = x+1 ) {}
```
## Lectura/escritura

Lo haremos usando las funciones print() y scan(). x puede ser de tipo booleano o entero. 

```jsx
print(x) ;
scan(x);
```

# Ejemplos de código y ejemplos de expresiones.

En este ejemplo se muestra cómo tras introducir una lista de enteros por consola, se suman todos los números de la lista y se muestra el valor de la suma.

```jsx

var n: int;
var suma : int = 0;
var i: int = 0;

scan(n);

var lista : int [100]; "arrays de hasta 100 elementos para este caso"

while (not (i== n)) {
    var actual : int;
    scan(actual);
    lista[i] = actual;
    
    suma = suma + lista[i];
    i= i + 1;
}
print(suma);

```

Una función que calcula el factorial y un programa que la llama

```jsx
fn factorial(n : int) : int {
    if (n = 0) {
        return 1;
    } else {
        if (n = 1) {
            return 1;
        }
    }
    var fact_ant : int = factorial(n-1);
    return n * fact_ant;
}

fn main() : int {
		var n:int;
		scan(n);
		var fact:int;
		fact= factorial(n);
		print(fact);
		return 0;
}
```

# Consideraciones finales
- Las funciones solo devolverán tipos básicos, por lo que `f()[i]` es algo que no puede aparecer. Tampoco puede aparecer f().struct_variable. En consecuencia, en la gramática los accesos a arrays y a struct solo funcionan con identificadores. 
- 
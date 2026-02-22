Taller SOLID - OrderManager
¿Qué hace esta clase?
OrderManager es una clase que maneja pedidos. El problema es que hace demasiadas cosas a la vez, lo que viola varios principios SOLID.

Violaciones encontradas
1. SRP — La clase tiene demasiadas responsabilidades
El principio dice que una clase debería hacer solo una cosa. Pero OrderManager hace todo esto al mismo tiempo:

-Crea pedidos
-Calcula descuentos
-Guarda en un archivo
-Manda notificaciones por email
-Calcula impuestos
-Genera reportes

Si quiero cambiar cómo se guardan los datos, tengo que tocar la misma clase donde está la lógica de pedidos. Eso no debería pasar.

2. OCP — Agregar descuentos obliga a modificar el código existente
El principio dice que el código debería ser fácil de extender sin tener que modificarlo. Acá los descuentos están hardcodeados con if:

if (total > 1000) {
    total *= 0.9;
}
if (total > 5000) {
    total *= 0.85;
}

Si mañana quiero agregar un descuento para clientes frecuentes, tengo que entrar al método y modificarlo, con el riesgo de romper lo que ya funcionaba.

4. DIP — Depende directamente de FileWriter y System.out
El principio dice que las clases no deberían depender de implementaciones concretas sino de abstracciones. Acá OrderManager crea directamente un FileWriter:

java.io.FileWriter fw = new java.io.FileWriter("orders.txt", true);

Si quisiera guardar en una base de datos en lugar de un archivo, tendría que modificar esta clase. Además, así es imposible hacer pruebas unitarias sin que se cree el archivo.

6. SRP — No existe una clase Order
Los pedidos se guardan como un arreglo de Strings con índices numéricos:

orders.add(new String[]{orderId, customer, product, String.valueOf(total), "PENDING"});

Y luego se accede así:

double total = Double.parseDouble(o[3]); // ¿qué es o[3]?

Esto es confuso y propenso a errores. Lo correcto sería tener una clase Order con sus atributos bien definidos.

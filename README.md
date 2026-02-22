# Laboratorio: Refactorización con SOLID

## Estructura del proyecto


solid-refactoring/
  src/
    original/
      OrderManager.java
    refactored/
      Order.java
      DiscountStrategy.java
      StandardDiscount.java
      VIPDiscount.java
      OrderRepository.java
      FileOrderRepository.java
      NotificationService.java
      EmailNotificationService.java
      OrderService.java
      Main.java
  README.md


Parte 1: Violaciones SOLID identificadas en `OrderManager`

1. SRP — La clase hace demasiadas cosas

la clase OrderManager tiene demasiadas responsabilidades en un solo lugar: crea pedidos, calcula descuentos, guarda en archivo, manda emails, calcula impuestos y genera reportes. Si necesito cambiar una sola de esas cosas, tengo que entrar a esta clase y arriesgarme a romper las demás.

2. OCP — Los descuentos no son extensibles

Los descuentos están escritos con `if` hardcodeados dentro de `createOrder`:

if (total > 1000) total *= 0.9;
if (total > 5000) total *= 0.85;

Para agregar un nuevo tipo de descuento (por ejemplo para clientes VIP), habría que modificar este método directamente.

3. DIP — Depende de implementaciones concretas

La clase instancia `FileWriter` directamente dentro del método, en lugar de depender de una abstracción:

java.io.FileWriter fw = new java.io.FileWriter("orders.txt", true);

Esto hace imposible cambiar el mecanismo de guardado sin tocar `OrderManager`. Lo mismo pasa con las notificaciones, que están hardcodeadas como `System.out.println`.

4. SRP — No existe una clase `Order`

Los pedidos se guardan como `String[]` con índices numéricos sin ningún significado:

java
orders.add(new String[]{orderId, customer, product, String.valueOf(total), "PENDING"});

double total = Double.parseDouble(o[3]); // ¿qué es o[3]?

No hay encapsulamiento. Si el orden de los campos cambia, el código se rompe sin avisar.


Parte 2: Decisiones de diseño en la refactorización

Crear la clase `Order`

Se extrajo la representación de un pedido a su propia clase con atributos tipados (`id`, `customer`, `product`, `total`, `status`). Así el código es legible y los datos están encapsulados.

Problema que resuelve: violación 4 (SRP).



Aplicar Strategy Pattern para descuentos (`DiscountStrategy`)

Se creó una interfaz `DiscountStrategy` con implementaciones separadas: `StandardDiscount` y `VIPDiscount`. Ahora agregar un nuevo tipo de descuento solo requiere crear una clase nueva, sin tocar el código existente.

Problema que resuelve: violación 2 (OCP).


Crear interfaz `OrderRepository`

Se separó la lógica de persistencia en una interfaz `OrderRepository` con su implementación `FileOrderRepository`. Si en el futuro se quiere guardar en base de datos, solo se crea una nueva implementación sin cambiar nada más.

Problema que resuelve: violación 3 (DIP).

Crear interfaz `NotificationService`

Se separaron las notificaciones en una interfaz `NotificationService` con su implementación `EmailNotificationService`. Cambiar el canal de notificación (SMS, push, etc.) solo requiere una nueva implementación.

Problema que resuelve: violación 3 (DIP).



 `OrderService` con inyección de dependencias

El `OrderService` recibe todas sus dependencias por constructor, sin instanciar nada internamente. Esto lo hace flexible y fácil de probar.


public OrderService(OrderRepository repository,
                    NotificationService notifier,
                    DiscountStrategy discountStrategy) { ... }


Problema que resuelve: violaciones 1 y 3 (SRP y DIP).




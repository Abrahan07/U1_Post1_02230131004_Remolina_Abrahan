/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refactored;

public class Main {

    public static void main(String[] args) {
        OrderRepository repo = new FileOrderRepository();
        NotificationService notifier
                = new EmailNotificationService();
        DiscountStrategy discount = new StandardDiscount();
        OrderService service = new OrderService(
                repo, notifier, discount);
        // Crear pedidos de prueba
        service.createOrder("Abrahan", "Laptop", 2000, 1);
        service.createOrder("Sergio", "Mouse", 50, 3);
        // Probar con VIP discount
        OrderService vipService = new OrderService(
                repo, notifier, new VIPDiscount());
        vipService.createOrder("Alejandra", "Monitor", 800, 2);
        System.out.println("\nPedidos en repositorio: "
                + repo.findAll().size());
    }
}

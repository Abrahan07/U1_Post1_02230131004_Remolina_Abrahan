/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refactored;

public class OrderService {

    private final OrderRepository repository;
    private final NotificationService notifier;
    private final DiscountStrategy discountStrategy;
    // Inyección de dependencias por constructor (DIP)

    public OrderService(OrderRepository repository,
            NotificationService notifier,
            DiscountStrategy discountStrategy) {
        this.repository = repository;
        this.notifier = notifier;
        this.discountStrategy = discountStrategy;
    }

    public Order createOrder(String customer, String product,
            double price, int quantity) {
        double total = discountStrategy.applyDiscount(
                price * quantity);
        String id = "ORD-" + System.currentTimeMillis();
        Order order = new Order(id, customer, product, total);
        repository.save(order);
        notifier.notify(customer,
                "Su pedido " + id + " ha sido creado.");
        return order;
    }
}

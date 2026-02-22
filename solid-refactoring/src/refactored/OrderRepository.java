/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package refactored;

import java.util.List;

public interface OrderRepository {

    void save(Order order);

    List<Order> findAll();

    Order findById(String id);
}

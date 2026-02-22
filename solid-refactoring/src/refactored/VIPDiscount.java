/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refactored;

/**
 *
 * @author abr
 */
public class VIPDiscount implements DiscountStrategy {

    @Override
    public double applyDiscount(double total) {
        return total * 0.80; // 20% siempre
    }
}

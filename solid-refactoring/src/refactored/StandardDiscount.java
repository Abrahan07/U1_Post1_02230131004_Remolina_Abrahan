/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refactored;

public class StandardDiscount implements DiscountStrategy {

    @Override
    public double applyDiscount(double total) {
        if (total > 5000) {
            return total * 0.85;
        }
        if (total > 1000) {
            return total * 0.90;
        }
        return total;
    }
}

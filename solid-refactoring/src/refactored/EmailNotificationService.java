/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refactored;

/**
 *
 * @author abr
 */
public class EmailNotificationService
        implements NotificationService {

    @Override
    public void notify(String recipient, String message) {
        System.out.println("EMAIL a " + recipient + ": " + message);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_1;

/**
 *
 * @author Dell
 */
public abstract class UserRole {

    protected String username;

    public UserRole(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public abstract void logout();
}

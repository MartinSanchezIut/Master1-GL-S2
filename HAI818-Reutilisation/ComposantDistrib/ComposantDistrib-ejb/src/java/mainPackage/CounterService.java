/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mainPackage;

import javax.ejb.Remote;

/**
 *
 * @author martin.sanchez@etu.umontpellier.fr
 */
@Remote
interface CounterService {
    public void incr();
    public void decr();
    public int getValue();
    public void raz();
}

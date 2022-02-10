/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatefulEjbClass.java to edit this template
 */
package mainPackage;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author martin.sanchez@etu.umontpellier.fr
 */
@Stateful
public class Counter implements CounterService {
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    private CounterService ib;


    int value = 0;
    public void incr() {value = value + 1;}
    public void decr() {value = value - 1;}
    public void raz() {value = 0;}
    public int getValue() {return value;}
    
    /*
    static {
    try {
            InitialContext ctx = new InitialContext();
            NewSessionBeanLocal cpt = (NewSessionBeanLocal) ctx.lookup("NewSessionBeanLocal/remote");
        }
        catch (NamingException exp) {}
    }
    */
    
}

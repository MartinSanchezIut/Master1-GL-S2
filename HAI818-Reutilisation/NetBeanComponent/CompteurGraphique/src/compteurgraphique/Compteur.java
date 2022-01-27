/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compteurgraphique;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author martin.sanchez@etu.umontpellier.fr
 */
public class Compteur implements Serializable {
    
    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    
    private String sampleProperty;
    
    private PropertyChangeSupport propertySupport;
    
    private boolean status;
    private int val; 
    
    
    public Compteur() {
        propertySupport = new PropertyChangeSupport(this);
        status = false;
        val = 0;
    }
    
    public void start() {
        status = true;
        System.err.println("true");
    }
    public void stop() {
        status = false;
        System.err.println("false");
    }
    public void raz(){
        setValue(0);
    }
    
    public void incr(){
        setValue(val +1);
    }
        
    public void decr(){
        setValue(val -1);
    }
    
    public void setValue(int v) {
        if (status)        {
            int oldval = val;
            val = v;
            propertySupport.firePropertyChange("value", oldval, val);
            System.err.println("Val = " + val);
        }
    }
    public int getValue(){
        return val;
    }
    
    
    public String getSampleProperty() {
        return sampleProperty;
    }
    
    public void setSampleProperty(String value) {
        String oldValue = sampleProperty;
        sampleProperty = value;
        propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, oldValue, sampleProperty);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
}

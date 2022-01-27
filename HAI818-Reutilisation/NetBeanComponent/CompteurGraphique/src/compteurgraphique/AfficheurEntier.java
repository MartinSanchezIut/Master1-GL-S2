/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compteurgraphique;

import java.beans.*;
import java.io.Serializable;
import javax.swing.JLabel;

/**
 *
 * @author martin.sanchez@etu.umontpellier.fr
 */
public class AfficheurEntier extends JLabel implements Serializable {
    
    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    
    private String sampleProperty;
    
    private PropertyChangeSupport propertySupport;
    
    public AfficheurEntier() {
        super("<none>");
        propertySupport = new PropertyChangeSupport(this);
    }
    
    public void setText(int text){
        this.setText(text + "");
    }
    
    /*
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
    */
}

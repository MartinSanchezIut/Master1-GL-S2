/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package switchapp.p1;

import java.awt.Label;
import java.beans.*;
import java.io.Serializable;
import javax.swing.JLabel;

/**
 *
 * @author martin.sanchez@etu.umontpellier.fr
 */
public class ZeroOuUn2 extends JLabel implements Serializable {
    
    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    
    private String sampleProperty;
    
    private PropertyChangeSupport propertySupport;
    
    public ZeroOuUn2() {
        super("zero") ;
    //    propertySupport = new PropertyChangeSupport(this);
    }
    
    
    public void f_Switch() {
        if (this.getText().equalsIgnoreCase("zero")) {
            this.setText("Un");
        }else {
            this.setText("zero");
        }
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

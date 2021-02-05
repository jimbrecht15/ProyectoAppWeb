/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.programia.info.web;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author user
 */
@Named(value = "idioma")
@SessionScoped
public class IdiomaManagedBean implements Serializable{

        
    //Atributos
    String idioma = "es"; 
    
    public IdiomaManagedBean() {
    }

    public String getIdioma() {
        return idioma;
    }
    
    public String cambiar(String nuevo){
        this.idioma = nuevo;    
        return null; 
    }
}

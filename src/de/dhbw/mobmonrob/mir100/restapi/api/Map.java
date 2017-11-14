/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.mobmonrob.mir100.restapi.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oliver Rettig
 */
@XmlRootElement
public class Map {
    @XmlElement(required = true)
    private String guid; //": "ed167ed1-afed-11e7-90f8-b8aeed719c5d", 
    @XmlElement(required = true)
    private String name; //": "MobMonRob", 
    @XmlElement(required = true)
    private String url;
    
    public String getName(){
        return name;
    }
    public String getURL(){
        return url;
    }
}

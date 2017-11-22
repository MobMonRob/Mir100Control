package de.dhbw.mobmonrob.mir100.restapi.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Oliver Rettig
 */
@XmlRootElement
public class Map {
    
    @XmlElement(required = true)
    private String guid;  
    
    @XmlElement(required = true)
    private String name; 
    
    @XmlElement(required = true)
    private String url;
    
    public String getName(){
        return name;
    }
    public String getURL(){
        return url;
    }
}
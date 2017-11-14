package de.dhbw.mobmonrob.mir100.restapi.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.dhbw.mobmonrob.mir100.restapi.api.Position;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Oliver Rettig
 */
@XmlRootElement(name = "")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Body {
    @XmlElement(name="position",required = true)
    private Position   position;
    
    public Body(){}
    
    public Position getPosition(){
        return position;
    }
    public void setPosition(Position position){
        this.position = position;
    }
}

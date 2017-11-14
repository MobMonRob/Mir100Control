package de.dhbw.mobmonrob.mir100.restapi.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * {
    "position": {
        "orientation": -166.56419372558594, 
        "x": 5.937511920928955, 
        "y": 4.264763355255127
    }, 
    "uptime": 4035, 
    "user_prompt": null, 
    "velocity": {
        "angular": 0.0, 
        "linear": 0.0
    }
}
 * @author Oliver Rettig
 */
@XmlRootElement
public class Position {
    @XmlElement(name="orientation",required = true)
    private double   orientation;
    @XmlElement(required = true)
    private double   x;
    @XmlElement(required = true)
    private double   y;
    
    // braucht jaxb
    public Position(){}
    
    public Position(double orientation, double x, double y) {
        this.orientation = orientation;
        this.x = x;
        this.y = y;
    }

    public double getOrientation(){ 
        return orientation; 
    }
    public double getX() { 
        return x; 
    }
    public double getY() { 
        return y; 
    }
    
    /*public void setOrientation(double orientation){ 
        this.orientation = orientation; 
    }
    public void setX(double x){ 
        this.x = x; 
    }
    public void setY(double y){ 
        this.y = y; 
    }*/
    
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("orientation=");
        sb.append(String.valueOf(orientation));
        sb.append(", x=");
        sb.append(String.valueOf(x));
        sb.append(", y=");
        sb.append(String.valueOf(y));
        return sb.toString();
    }
}

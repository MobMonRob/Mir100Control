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
public class Velocity {
    @XmlElement(name="angular",required = true)
    private double   angular;
    @XmlElement(name="linear", required = true)
    private double   linear;
    
    // braucht jaxb
    public Velocity(){}
    
    public Velocity(double angular, double linear) {
        this.angular = angular;
        this.linear = linear;
    }

    public double getAngular(){ 
        return angular; 
    }
    public double getLinear() { 
        return linear; 
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("angular=");
        sb.append(String.valueOf(angular));
        sb.append(", linear=");
        sb.append(String.valueOf(linear));
        return sb.toString();
    }
}

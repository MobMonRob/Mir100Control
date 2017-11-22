package de.dhbw.mobmonrob.mir100.restapi.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Oliver Rettig
 */
@XmlRootElement(name = "")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    @XmlElement(name="position",required = true)
    private Position   position;
    
    @XmlElement(name="velocity", required = true)
    private Velocity velocity;
    
    @XmlElement(name="uptime", required = false)
    private double uptime;
    
    public Result(){}
    
    public Position getPosition(){
        return position;
    }
    public void setPosition(Position position){
        this.position = position;
    }
    
    public Velocity getVelocity(){
        return velocity;
    }
    public void setVelocity(Velocity velocity){
        this.velocity = velocity;
    }
    public double getUptime(){
        return uptime;
    }
    public void setUptime(double uptime){
        this.uptime = uptime;
    }
}

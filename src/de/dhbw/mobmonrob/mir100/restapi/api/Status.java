package de.dhbw.mobmonrob.mir100.restapi.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oliver Rettig
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {
    
    //"allowed_methods": null, 
    private double battery_percentage; //97.69999694824219, 
    private double battery_time_remaining; // 46907, 
    private double distance_to_next_target; // 0.0, 
    
    //"errors": [], 
    
    //"footprint": "[[0.506,-0.32],[0.506,0.32],[-0.454,0.32],[-0.454,-0.32]]", 
    
    String map_id; // "ed167ed1-afed-11e7-90f8-b8aeed719c5d", 
    
    // "mission_queue_id": null, 
    
    String mission_queue_url; // null, 
    String mission_text; // "Starting...", 
    
    private long mode_id; // 7, 
    private String mode_text; // "Mission", 
    private double moved; // 15133.61, 
    
    /*"position": {
        "orientation": -166.56419372558594, 
        "x": 5.937511920928955, 
        "y": 4.264763355255127
    }*/
    
    /*"robot_model": "Unknown", 
    "robot_name": "mir", 
    "serial_number": "", 
    "session_id": "ec356fc1-afed-11e7-90f8-b8aeed719c5d", 
    "state_id": 4, 
    "state_text": "Pause", 
    "unloaded_map_changes": false, 
    "uptime": 2006, 
    "user_prompt": null, 
    "velocity": {
        "angular": 0.0, 
        "linear": 0.0
    }*/
}

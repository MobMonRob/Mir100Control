package de.dhbw.mobmonrob.mir100.restapi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import de.dhbw.mobmonrob.mir100.restapi.impl.Body;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.jackson.JacksonFeature;

public class Mir100 {
    
	static final String REST_URI = "http://192.168.12.20:8080/v2.0.0/";
        static final String CREDENTIALS = "Basic ZGlzdHJpYnV0b3I6NjJmMmYwZjFlZmYxMGQzMTUyYzk1ZjZmMDU5NjU3NmU0ODJiYjhlNDQ4MDY0MzNmNGNmOTI5NzkyODM0YjAxNA==";
	static final String REGISTERS_PATH = "registers";
        static final String STATUS_PATH = "status";
	
        private WebTarget webBaseTarget;
        
        private Invocation.Builder currentPositionInvocationBuilder;
        private Invocation.Builder mapsInvocationBuilder;
        
        public static final String REGISTER_PATH = "register/";
        public static final String POSITION_PATH = "position/";
        public static final String MISSION_PATH = "mission/";
        public static final String SESSION_PATH = "session/";
        //public static final String STATUS_PATH = "status/";
        public static final String ROBOT_PATH = "robot/";
        public static final String MAP_PATH = "map/";
        public static final String LOG_PATH = "log/";
        
	public Mir100() {
		
            // creating a web target
            Client client = ClientBuilder.newClient();
            client = client.register(JacksonFeature.class);
            webBaseTarget = client.target(REST_URI);
            buildCurrentPositionInvocationBuilder();
            buildMapsInvocationBuilder();
        }

        public void getRegisters(){
            WebTarget registersWebTarget = webBaseTarget.path(REGISTERS_PATH);

            //test
            //registersWebTarget.register(PositionBodyReader.class);
            //registersWebTarget.register(BodyBodyReader.class);

            // buildingan http request invocation
            Invocation.Builder invocationBuilder = registersWebTarget.request(MediaType.APPLICATION_JSON_TYPE);
            invocationBuilder.header("authorization",CREDENTIALS);


            // invoking http get
            /*
            Wenn z.B. WLAN ausgestellt und damit keine Verbindung zur Mir100 aufgebaut werden kann:
            Exception in thread "main" javax.ws.rs.ProcessingException: java.net.ConnectException: Connection timed out: connect
            at org.glassfish.jersey.client.internal.HttpUrlConnector.apply(HttpUrlConnector.java:284)
            at org.glassfish.jersey.client.ClientRuntime.invoke(ClientRuntime.java:278)
            at org.glassfish.jersey.client.JerseyInvocation.lambda$invoke$0(JerseyInvocation.java:753)
            at org.glassfish.jersey.internal.Errors.process(Errors.java:316)
            at org.glassfish.jersey.internal.Errors.process(Errors.java:298)
            at org.glassfish.jersey.internal.Errors.process(Errors.java:229)
            at org.glassfish.jersey.process.internal.RequestScope.runInScope(RequestScope.java:414)
            at org.glassfish.jersey.client.JerseyInvocation.invoke(JerseyInvocation.java:752)
            at org.glassfish.jersey.client.JerseyInvocation$Builder.method(JerseyInvocation.java:419)
            at org.glassfish.jersey.client.JerseyInvocation$Builder.get(JerseyInvocation.java:319)
            */
            //TODO
            // als Argument die Klasse übergeben...
            Response response = invocationBuilder.get();

            System.out.println(response.getStatus()); // 200 wenn alles ok ist

            //TODO
            // als json object den Rückgabewert beschaffen

            System.out.println(response.readEntity(String.class));

        }
        
        /**
         * Get the list of maps in the robot.
         * 
         *  {
                "guid": "mirconst-guid-0000-0001-maps00000000", 
                "name": "ConfigurationMap", 
                "url": "/v2.0.0/maps/mirconst-guid-0000-0001-maps00000000"
            }, 
            {
                "guid": "38b5955f-ae94-11e7-8f94-b8aeed719c5d", 
                "name": "test-minimap", 
                "url": "/v2.0.0/maps/38b5955f-ae94-11e7-8f94-b8aeed719c5d"
            }, 
            {
                "guid": "ed167ed1-afed-11e7-90f8-b8aeed719c5d", 
                "name": "MobMonRob", 
                "url": "/v2.0.0/maps/ed167ed1-afed-11e7-90f8-b8aeed719c5d"
            }
            * @return 
         */
        public List<Map> getMapList(){
            List<Map> result = null;
            try {
                Response response = mapsInvocationBuilder.get();
                if (response.getStatus() != 200) throw new RuntimeException("getMapList() failed: "+String.valueOf(response.getStatus()));
                ObjectMapper mapper = new ObjectMapper();
                result = mapper.readValue(response.readEntity(String.class), 
                        TypeFactory.defaultInstance().constructCollectionType(List.class,Map.class));
                //return response.readEntity(new GenericType<List<Map>>{});
                //System.out.println(response.readEntity(String.class));
            } catch (IOException ex) {
                Logger.getLogger(Mir100.class.getName()).log(Level.SEVERE, null, ex);
            }
            return result;
        }
        public void getMap(String name){
            //HorstBuro
        }
    
        /**
         * Get a list of all actions
         * 
         *     {
            "action_type": "prompt_user", 
            "url": "/v2.0.0/actions/prompt_user"
            }, 
            {
                "action_type": "move", 
                "url": "/v2.0.0/actions/move"
            }, 
            {
                "action_type": "wait_for_plc_register", 
                "url": "/v2.0.0/actions/wait_for_plc_register"
            }, 
            {
                "action_type": "bluetooth", 
                "url": "/v2.0.0/actions/bluetooth"
            }, 
            {
                "action_type": "try_catch", 
                "url": "/v2.0.0/actions/try_catch"
            }, 
            {
                "action_type": "adjust_localization", 
                "url": "/v2.0.0/actions/adjust_localization"
            }, 
            {
                "action_type": "switch_map", 
                "url": "/v2.0.0/actions/switch_map"
            }, 
            {
                "action_type": "if", 
                "url": "/v2.0.0/actions/if"
            }, 
            {
                "action_type": "docking", 
                "url": "/v2.0.0/actions/docking"
            }, 
            {
                "action_type": "create_autolog", 
                "url": "/v2.0.0/actions/create_autolog"
            }, 
            {
                "action_type": "relative_move", 
                "url": "/v2.0.0/actions/relative_move"
            }, 
            {
                "action_type": "pickup_cart", 
                "url": "/v2.0.0/actions/pickup_cart"
            }, 
            {
                "action_type": "place_shelf", 
                "url": "/v2.0.0/actions/place_shelf"
            }, 
            {
                "action_type": "place_cart", 
                "url": "/v2.0.0/actions/place_cart"
            }, 
            {
                "action_type": "wait_for_fleet", 
                "url": "/v2.0.0/actions/wait_for_fleet"
            }, 
            {
                "action_type": "email", 
                "url": "/v2.0.0/actions/email"
            }, 
            {
                "action_type": "return", 
                "url": "/v2.0.0/actions/return"
            }, 
            {
                "action_type": "set_plc_register", 
                "url": "/v2.0.0/actions/set_plc_register"
            }, 
            {
                "action_type": "break", 
                "url": "/v2.0.0/actions/break"
            }, 
            {
                "action_type": "wait", 
                "url": "/v2.0.0/actions/wait"
            }, 
            {
                "action_type": "sound", 
                "url": "/v2.0.0/actions/sound"
            }, 
            {
                "action_type": "throw_error", 
                "url": "/v2.0.0/actions/throw_error"
            }, 
            {
                "action_type": "move_to_position", 
                "url": "/v2.0.0/actions/move_to_position"
            }, 
            {
                "action_type": "run_ur_program", 
                "url": "/v2.0.0/actions/run_ur_program"
            }, 
            {
                "action_type": "set_footprint", 
                "url": "/v2.0.0/actions/set_footprint"
            }, 
            {
                "action_type": "wait_for_bluetooth", 
                "url": "/v2.0.0/actions/wait_for_bluetooth"
            }, 
            {
                "action_type": "charging", 
                "url": "/v2.0.0/actions/charging"
            }, 
            {
                "action_type": "pickup_shelf", 
                "url": "/v2.0.0/actions/pickup_shelf"
            }, 
            {
                "action_type": "while", 
                "url": "/v2.0.0/actions/while"
            }, 
            {
                "action_type": "continue", 
                "url": "/v2.0.0/actions/continue"
            }, 
            {
                "action_type": "light", 
                "url": "/v2.0.0/actions/light"
            }, 
            {
                "action_type": "loop", 
                "url": "/v2.0.0/actions/loop"
            }
        ]
            */
        public void getActions(){
            WebTarget registersWebTarget = webBaseTarget.path("actions");
        }
        
        public void getState(){
            WebTarget registersWebTarget = webBaseTarget.path("state");
            /*500
            {
                "error_code": "unexpected_error", 
                "error_human": "An unexpected error occured"
            }*/
        }
        
        private void buildCurrentPositionInvocationBuilder(){
            WebTarget registersWebTarget = webBaseTarget.path("status");
            //registersWebTarget = registersWebTarget.queryParam("whitelist", "position, uptime, velocity");
            registersWebTarget = registersWebTarget.queryParam("whitelist", "position");
            // buildingan http request invocation
            currentPositionInvocationBuilder = registersWebTarget.request(MediaType.APPLICATION_JSON_TYPE);
            currentPositionInvocationBuilder.header("authorization",CREDENTIALS);
            currentPositionInvocationBuilder.accept("application/json");
        }
        private void buildMapsInvocationBuilder(){
            WebTarget registersWebTarget = webBaseTarget.path("maps");
            //registersWebTarget = registersWebTarget.queryParam("whitelist", "position");
            // buildingan http request invocation
            mapsInvocationBuilder = registersWebTarget.request(MediaType.APPLICATION_JSON_TYPE);
            mapsInvocationBuilder.header("authorization",CREDENTIALS);
            mapsInvocationBuilder.accept("application/json");
        }
        
        /**
         * Get current position and orientation of the robot.
         * 
         * @return
         * @throws RuntimeException if REST access failed.
         */
        public Position getCurrentPosition() throws RuntimeException {
            Response response = currentPositionInvocationBuilder.get();
            if (response.getStatus() != 200) throw new RuntimeException("getCurrentPosition() failed: "+String.valueOf(response.getStatus()));
            return response.readEntity(Body.class).getPosition();
        }
        
        /**
         * Information about the robot including current state, distance to next 
         * target in meters, battery time left in seconds and current coordinate 
         * and orientation of the robot.
         * 
         * Possible kinds: position, state, distance_to_target, battery_time_left_seconds<p>
         * 
         * @return status
         */
        public Status getRobotState(){
            WebTarget registersWebTarget = webBaseTarget.path("status");
                
            // buildingan http request invocation
            Invocation.Builder invocationBuilder = registersWebTarget.request(MediaType.APPLICATION_JSON_TYPE);
            invocationBuilder.header("authorization",CREDENTIALS);
                
            /*404
            <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
            <title>404 Not Found</title>
            <h1>Not Found</h1>
            <p>The requested URL was not found on the server.  If you entered the URL manually please check your spelling and try again.</p>*/
            
            Response response = invocationBuilder.get();
                
            System.out.println(response.getStatus()); // 200 wenn alles ok ist
            System.out.println(response.readEntity(String.class));
                
            // gets_possible = ['position', 'state', 'distance_to_target', 'battery_time_left_seconds']
            /*if robot_state == 'None':
                response = requests.get(robot_url)
                return response.json()
            elif robot_state in gets_possible:
                response = requests.get(robot_url+str(robot_state))
                return response.json()
            else:
                raise ValueError("The robot state: '%s' in get_robot_state() is not valid" % robot_state)
            */
            
            return null;
        }
        
        //--------------------------------- Status ---------------------------------------
        
        /**
         * Get the status of the robot.
         * 
         * Get all the fields of the status of the robot or only the ones specified in the whitelist
         * 
         * @param whitelist
         * @return status
         */
        public Status get_status(List<String> whitelist){
            Status result = null;
            /*List<String> gets_possible = new ArrayList<String>();
            gets_possible.add("battery");
            gets_possible.add("state");
            gets_possible.add("uptime");
            gets_possible.add("distance");
            gets_possible.add("job");
            gets_possible.add("map");*/
            //if (whitelist == null){
                
            WebTarget registersWebTarget = webBaseTarget.path(STATUS_PATH);
            registersWebTarget = registersWebTarget.queryParam("whitelist", "position");
            
            Invocation.Builder invocationBuilder = registersWebTarget.request(MediaType.APPLICATION_JSON_TYPE);
            invocationBuilder.header("authorization",CREDENTIALS);
                
            //invocationBuilder = invocationBuilder.("whitelist", "position");
                   
            ClientResponse response = invocationBuilder.get(ClientResponse.class);

            if (response.getStatus() != 200){
                    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());   
            }

            //result = response.getEntity(Status.class);

            //Status status = statusWebTarget.request().accept(MediaType.APPLICATION_JSON_TYPE).buildGet().invoke(Status.class);

            /*} else if (get_possible.contains(whitelist)){
                result = requests.get(status_url+str(whitelist))
                return response.json();
            } else {
                //log.warn("The status: '%s' in get_status() is not valid", status_id);
            }*/
            return result;
        }
        
         /*
        def delete_position(position_id):
            response = requests.delete(position_url+str(position_id))
            if (response.json())['success'] == 'false':
                raise ValueError("The position id: %d was not found and therefore not deleted in delete_position()" % position_id)
            else:
                return response.json()

        def get_position_list():
            response = requests.get(position_url)
            return response.json()

        def get_position(position_id):  
            response = requests.get(position_url+str(position_id))
            if (response.json())['success'] == 'false':
                raise ValueError("I couldn't find the position with the id:%d" % position_id)
            else:
                return response.json()

        def get_position_relevant():
            #get all positions and extract size
            complete_list = get_position_list()
            size_ = complete_list['size']
            current_map = get_status("map")
            map_id = current_map['map']['id']
            relevant_maps = []
            for i in range(size_):
            # for every match to our map_id we want to save it to a list 
                if complete_list['items'][i]['map_id'] == map_id:
                    relevant_maps.append(complete_list['items'][i])
            return relevant_maps

        def put_position(position_id, name, pos_x=0, pos_y=0, orientation=0, type_=0, map_id = 1):
            data_position = {"id" : position_id,
                "name" : name,
                    "pos_x" : pos_x,
                        "pos_y" : pos_y,
                            "orientation" : orientation,
                            "type" : type_,
                            "map_id" : map_id}
            try:
                response = requests.put(position_url+str(position_id), data = json.dumps(data_position))
                if (response.json())['success'] == 'false':
                    #raise ValueError("position with id: %d was not found" % position_id)
                    #print "position with id:", position_id
                    raise ValueError('position with id %d, was not found' % position_id)
                else:
                    return response.json()
            except:
                raise ValueError("was not able to update position:\nid:%d\nname:%s\ntype_:%d\nmap_id:%d" % (position_id, name, type_, map_id))*/
        
       /* public void get_position(){
            response = requests.get(position_url+str(position_id))
            if (response.json())['success'] == 'false':
                raise ValueError("I couldn't find the position with the id:%d" % position_id)
            else:
                return response.json()
        }*/
        
        
        /*def get_robot_state(robot_state = 'None'):
            gets_possible = ['position', 'state', 'distance_to_target', 'battery_time_left_seconds']
            if robot_state == 'None':
                response = requests.get(robot_url)
                return response.json()
            elif robot_state in gets_possible:
                response = requests.get(robot_url+str(robot_state))
                return response.json()
            else:
                raise ValueError("The robot state: '%s' in get_robot_state() is not valid" % robot_state)

        # defualt state is pause!
        def set_robot_state(robot_state):
            gets_possible = ["continue","pause"]
            if robot_state in gets_possible:
               data_robot_state = {"command" : robot_state}
               response = requests.post(robot_url, data = json.dumps(data_robot_state))
               if (response.json())['success'] == 'false':
                    print "robot is already in mode: ",robot_state
               else:
                    return response.json()
            else:
                raise ValueError("The robot command %s, is neither of these %d" % (gets_possible, robot_state))
        
        
        
        
        #----------------------------------- Mission ---------------------------------------#
        def delete_mission(mission_id):
            response = requests.delete(mission_url+"id/"+str(mission_id))
            if (response.json())['success'] == 'false':
                raise ValueError("The mission id: %d was not found and therefore not deleted in delete_mission()" % mission_id)
            else:
                return response.json()

        def clear_missions():
            response = requests.delete(mission_url)
            return response.json()

        def get_mission_information(mission_info):
            get_possible = ['active', 'available', 'positions', 'queue']
            if mission_info in get_possible:
                response = requests.get(mission_url+mission_info)
                data_mission_info = response.json()
                return data_mission_info
            else:
                raise ValueError("The argument %s in get_mission_information() doesn't match either one of %s:" % (mission_info, get_possible))

        def get_mission_status(mission_id = 'None'):
                #lists all missions if no mission_id = 'None'
                if mission_id == 'None':
                    response = requests.get(mission_url)
                    return response.json()
                else:
                    # check if the mission is in our session queue
                    response = requests.get(mission_url+"id/"+str(mission_id))
                    if (response.json())['success'] == 'false':
                        raise ValueError("The mission id: %d was not found in get_mission()" % mission_id)
                    else:
                        return response.json()

        # setting mission payload type 1
        def set_mission(type_, name):
            try:
                data_mission = {"type" : type_, "name" : name}
                response = requests.post(mission_url, data = json.dumps(data_mission))
                return response.json()
            except:
                raise ValueError("Unable to create mission with type: %d and name: %s in set_mission()" %(type_, name))

        # setting mission payload type 2
        def set_taxa(type_, name):
            try:
                data_taxa = {"type" : type_, "name" : name}
                response = requests.post(mission_url, data = json.dumps(data_taxa))
                return response.json()
            except:
                raise ValueError("Unable to create mission with type: %d and name: %s in set_taxa()" %(type_, name))

        def set_taxa_pose(type_, x=0, y=0, orientation=0):
            try:
                data_taxa_pose = {"type" : type_, "x" : x, "y" : y, "orientation" : orientation}
                response = requests.post(mission_url, data = json.dumps(data_taxa_pose))
                return response.json()
            except:
                raise ValueError("Unable to create taxi+pose in set_taxa_pose()")
        #----------------------------------- Session ---------------------------------------#
        def get_session_list():
            response = requests.get(session_url)
            return response.json()

        def get_session(session_id):
            response = requests.get(session_url+str(session_id))
            if (response.json())['success'] == 'false':
                raise ValueError("The session with id: %d was not found in get_session()" % session_id)
            else:
                return response.json()

        #----------------------------------- Map ---------------------------------------#
        def get_map_list():
            response = requests.get(map_url)
            return response.json()

        def get_map(map_id):
            response = requests.get(map_url+str(map_id))
            if (response.json())['success'] == 'false':
                raise ValueError("The map with id: %d was not found in get_map()" % map_id)
            else:
                return response.json()

        #----------------------------------- Position ---------------------------------------#
        def delete_position(position_id):
            response = requests.delete(position_url+str(position_id))
            if (response.json())['success'] == 'false':
                raise ValueError("The position id: %d was not found and therefore not deleted in delete_position()" % position_id)
            else:
                return response.json()

        def get_position_list():
            response = requests.get(position_url)
            return response.json()

        def get_position(position_id):  
            response = requests.get(position_url+str(position_id))
            if (response.json())['success'] == 'false':
                raise ValueError("I couldn't find the position with the id:%d" % position_id)
            else:
                return response.json()

        def get_position_relevant():
            #get all positions and extract size
            complete_list = get_position_list()
            size_ = complete_list['size']
            current_map = get_status("map")
            map_id = current_map['map']['id']
            relevant_maps = []
            for i in range(size_):
            # for every match to our map_id we want to save it to a list 
                if complete_list['items'][i]['map_id'] == map_id:
                    relevant_maps.append(complete_list['items'][i])
            return relevant_maps

        def put_position(position_id, name, pos_x=0, pos_y=0, orientation=0, type_=0, map_id = 1):
            data_position = {"id" : position_id,
                "name" : name,
                    "pos_x" : pos_x,
                        "pos_y" : pos_y,
                            "orientation" : orientation,
                            "type" : type_,
                            "map_id" : map_id}
            try:
                response = requests.put(position_url+str(position_id), data = json.dumps(data_position))
                if (response.json())['success'] == 'false':
                    #raise ValueError("position with id: %d was not found" % position_id)
                    #print "position with id:", position_id
                    raise ValueError('position with id %d, was not found' % position_id)
                else:
                    return response.json()
            except:
                raise ValueError("was not able to update position:\nid:%d\nname:%s\ntype_:%d\nmap_id:%d" % (position_id, name, type_, map_id))
        #----------------------------------- Register ---------------------------------------#
        def get_register_value(register_id):
            if register_id in range(1,201):
                response = requests.get(register_url+str(register_id))
                return (response.json())['value']
            else:
                raise ValueError("The registered id number:%d does not exist.\nValid inputs for get_register_value() are [1,200]" % register_id)

        def get_register_list():
            response = requests.get(register_url)
            return response.json()

        def get_register_range(interval1, interval2):
            if interval1 < interval2 and interval1 in range(1,201) and interval2 in range(1,201):
                response = requests.get(register_url+str(interval1)+"/"+str(interval2)+"/")
                return response.json()
            elif interval1 > interval2:
                raise ValueError("interval 1 should be less than interval 2 in get_register_range(interval 1, interval 2)")
            else:
                raise ValueError("interval 1 = %d, interval 2 = %d. One of the intervals is not in the range [1,200] in get_register_range()" % (interval1, interval2))

        def set_register(register_id, register_value):
            if register_id in range(1,201):
                data_register = {'value': register_value}
                try:
                    response = requests.post(register_url+str(register_id), data = json.dumps(data_register))
                    print response.json()
                except:
                    raise valueError("Error in set_register")
            else:
                raise valueError("The registered id number: %d does not exist.\nValid inputs are [1,200]." % register_id)

        def clear_registers():
                data_register = {'value' : 0}
                for i in range(1,201):
                    requests.post(register_url+str(i), data = json.dumps(data_register))
        #----------------------------------- Log ---------------------------------------#
        def get_log_list():
            response = requests.get(log_url)
            return response.json()

        def get_log(log_id):
            response = requests.get(log_url+str(log_id))
            if (response.json())['success'] == 'false':
                raise ValueError("The log with id: %d was not found in get_log()" % log_id)
            else:
                return response.json()

        }*/
}
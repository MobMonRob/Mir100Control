package de.dhbw.mobmonrob.mir100.restapi.test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.dhbw.mobmonrob.mir100.restapi.api.Mir100;
import de.dhbw.mobmonrob.mir100.restapi.api.Position;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oliver Rettig
 */
public class Test {
    public static void main(String[] args){
        Mir100 mir100 = new Mir100();
        //mir100.getRegisters();
        
        mir100.getMapList();
        /*while(true){
            Position position = mir100.getCurrentPosition();
            System.out.println(position.toString());
        }*/
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            OutputStream os = new FileOutputStream("test.json");
            mapper.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
            for (int i=0;i<100;i++){
                Position position = mir100.getCurrentPosition();
                //System.out.println(position.toString());
                try {
                    mapper.writeValue(os, position);
                } catch (IOException ex) {
                    Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //TODO
        // test in welchen zeitlichen Abständen ich die Position beschaffen kann!
        // json--> pojo wie geht das
    }
}

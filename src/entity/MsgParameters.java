package entity;

import java.util.HashMap;
import java.util.Map;

public class MsgParameters {
    Map<String,String> parameters= new HashMap<>();

    public MsgParameters(String inputMsg) {
        String[] splitMsg = inputMsg.split(";");
        for (String msg : splitMsg) {
            String[] split = msg.split(":");
            parameters.put(split[0],split[1]);
        }
    }

    public String get(String parameter){
        return parameters.get(parameter);
    }

}


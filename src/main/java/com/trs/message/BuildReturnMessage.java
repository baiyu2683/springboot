package com.trs.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trs.system.Const;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangheng on 2016/7/28.
 */
public class BuildReturnMessage {

    public static Map<String,Object> buildMessage(Map<String,Object> map){
        if(map == null){
            map = new HashMap<String, Object>();
            map.put("result", Const.RETURN_RESULT_FAILURE);
            map.put("errorMsg", Const.RETURN_MESSAGE_SYSTEMERROR);
        }
        return map;
    }
}

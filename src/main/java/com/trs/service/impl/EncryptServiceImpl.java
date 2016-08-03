package com.trs.service.impl;

import com.trs.exception.TRSOMException;
import com.trs.key.OM.KeyUtilBase;
import com.trs.service.EncryptService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 该类在打包介质中需要被删除！
 * @author chang
 * @since 2012-10-15 09:22:14
 * */
public class EncryptServiceImpl extends EncryptService {

    //实现父类的抽象方法。
    public void checkLicence(){
        String key=(String) System.getProperty("trsom.key");// prop.getProperty("trsom.key");
        // 加载已加密的外壳类KeyUtilBase
        KeyUtilBase obj = null;
        try {
            obj = KeyUtilBase.getInstance();
        } catch (Exception e1) {
            throw new TRSOMException("无法启动系统，找不到注册所需的jar包:"+e1.getMessage(),e1);
        }
        String result =obj.ValidateOMKey(key);
        if(result.startsWith("Invalid")){
            throw new TRSOMException("注册码不正确，请确认。");
        }else {
            String invalidateYear=result.substring(0,4);
            String invalidateMonth=result.substring(4,6);
            String invalidateDay=result.substring(6);
            String invalDate=invalidateYear+"-"+invalidateMonth+"-"+invalidateDay;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date invalidate=null;
            try {
                invalidate = dateFormat.parse(invalDate);
            } catch (ParseException e) {
                throw new TRSOMException("注册码不正确，请确认:"+e.getMessage(),e);
            }
            if(invalidate.before(new Date())){
                throw new TRSOMException("注册码已到期，请重新申请。");
            }
        }
    }
}

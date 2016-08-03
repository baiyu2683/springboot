package com.trs.configs;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangheng on 16-8-4.
 */
@Service
public class ScheduledTasks {
    public void reportCurrentTime(){
        System.out.println ("Scheduling Tasks Examples: The time is now " + dateFormat ().format (new Date()));
    }

    public void reportCurrentByCron(){
        System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + dateFormat ().format (new Date ()));
    }

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("HH:mm:ss");
    }
}

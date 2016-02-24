package com.company;


import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Callable;

public class SumClass implements Callable {

    private static final Logger logger = Logger.getLogger(SumClass.class.getName());
    int mass[];
    //private int result = 0;

    public SumClass(int[] mass){
        this.mass = mass;
    }
    @Override
    public Integer call() {
        int result = 0;
        try {
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i<mass.length;i++)
            result+=mass[i];
        logger.info("Sum calculated: "+result);
        return result;
    }

    public int getResult(){
        return 0;//result;
    }
}

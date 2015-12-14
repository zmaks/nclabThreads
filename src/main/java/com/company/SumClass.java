package com.company;


import org.apache.log4j.Logger;

public class SumClass implements Runnable {

    private static final Logger logger = Logger.getLogger(SumClass.class.getName());
    int mass[];
    private int result = 0;

    public SumClass(int[] mass){
        this.mass = mass;
    }
    @Override
    public void run() {
        for (int i = 0; i<mass.length;i++)
            result+=mass[i];
        logger.info("Sum: "+result);
    }

    public int getResult(){
        return result;
    }
}

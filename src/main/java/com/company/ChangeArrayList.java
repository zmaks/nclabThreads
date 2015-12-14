package com.company;

import org.apache.log4j.Logger;

import java.util.List;


public class ChangeArrayList implements Runnable {
    private static final Logger logger = Logger.getLogger(ChangeArrayList.class.getName());

    List<Integer> list;

    public ChangeArrayList(List<Integer> iterator)
    {
        this.list = iterator;
    }

    public void run() {
        try {
            for(int i = 0; i<10;i++) {

                list.add(i+11);
                logger.info(Thread.currentThread().getName() + ": added number " + (i + 11));
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }

    }
}

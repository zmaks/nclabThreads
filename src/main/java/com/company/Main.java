package com.company;


import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            RunCopyOnWriteArrayListTest();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }

        try {
            RunTest();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    private static void RunCopyOnWriteArrayListTest() throws InterruptedException {
        Random random = new Random();
        CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList<Integer>();

        Thread.currentThread().setName("Thread in Main");
        ChangeArrayList changeArrayList = new ChangeArrayList(numbers);
        Thread thread = new Thread(changeArrayList, "Thread in ChangeArrayList");

        thread.start();
        for (int i = 0; i < 5; i++) {
            numbers.add(i + 1);
            logger.info(Thread.currentThread().getName() + ": added number " + (i + 1));
            Thread.sleep(50);
        }

        Thread.sleep(100);

        String str = "Numbers";
        logger.info("Run read cycle");
        for (int i : numbers) {

            str+=i+" ";
            Thread.sleep(50);
        }
        logger.info("End read cycle: \n" + str);

        Thread.sleep(1000);
        logger.info("\nNumbers: " + numbers + "\n");
    }

    private static void RunTest() throws InterruptedException {
        final ArrayList<int[]> massives = new ArrayList<int[]>();
        for(int i = 0; i<5;i++){
            int[] mass = new int[10];
            String s = "";
            for(int j = 0; j<10;j++) {
                mass[j] = i + j;
                s += (j + i) + "";
            }
            logger.info("Mass "+i+": "+s);
            massives.add(mass);
        }

        int result = 0;

        HashMap<Thread, SumClass> threads = new HashMap<Thread, SumClass>();
        for(int i = 0; i<massives.size();i++) {
            SumClass sumClass = new SumClass(massives.get(i));
            Thread thread = new Thread(sumClass);
            threads.put(thread, sumClass);
            thread.start();

        };

        for(Map.Entry<Thread, SumClass> thread : threads.entrySet()){
            thread.getKey().join();
            result+=thread.getValue().getResult();
        }
        logger.info(result);

    }
}

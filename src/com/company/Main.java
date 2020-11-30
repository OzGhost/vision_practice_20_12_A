package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<TestInput> testSuite;
    public static void main(String[] args) {
        readTestSuite();
        testSuite.forEach(item -> System.out.println(item.p.getX() + ":" + item.p.getY()));
    }

    private static void readTestSuite(){
        try {
            File myObj = new File("src/com/company/testResource.txt");
            System.out.println(myObj.getPath());
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            testSuite = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String[] item = myReader.nextLine().split(" ");
                if(i == 0){
                    testSuite.add(new TestInput());
                    testSuite.get(testSuite.size()-1).p = new Point(Integer.parseInt(item[0]), Integer.parseInt(item[1]));
                } else {
                    for(int t = 0; t< item.length; t=t+2){
                        Point cr = new Point(Integer.parseInt(item[t]),Integer.parseInt(item[t+1]));
                        testSuite.get(testSuite.size()-1).ps.add(cr);
                    }
                }
                i = (i + 1) % 2;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static class TestInput {
        Point p;
        List<Point> ps;

        private TestInput(){
            ps = new ArrayList<>();
        }
    }
}

package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    private ArrayList<Qweston> test = new ArrayList<>();

    public Test() {
        load("D:\\User\\Desktop\\Программная инженерия\\Git kurs2\\task11\\src\\main\\resources\\com\\example\\task11\\test.txt");
    }

    public Test(String filename) {
        load(filename);
    }

    public ArrayList<Qweston> createtest(int numberqwest){
        ArrayList<Qweston> currenttest=new ArrayList<>();
        ArrayList<Qweston> testQwest = new ArrayList<>(test);
        for(int i = 0; i< numberqwest; i++){
            int index;
            index = (int)(Math.random()*testQwest.size());
            if(index == testQwest.size())index --;
            currenttest.add(testQwest.get(index));
            testQwest.remove(index);
        }
        return currenttest;
    }

    public ArrayList<Qweston> getTest() {
        return test;
    }

    public void setTest(ArrayList<Qweston> test) {
        this.test = test;
    }

    private void load(String filename) {
        FileReader fin;
        //
        // Объявляем класс Scanner, инициализируем его с параметром file
        // Создаем цикл, который будет считывать строки, пока не дойдем
        // до конца файла.
        //
        try {
            fin=new FileReader(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не обнаружен");
            return;

        }
        Scanner scanner = new Scanner(fin);
        scanner.useDelimiter("\\n");
        while (scanner.hasNextLine()) {
            Qweston qtemp=new Qweston("");
            String temp=scanner.nextLine();
            qtemp.SetQuest(temp);
            //   System.out.println("считан вопрос:"+qtemp.question);

            while(!(temp=scanner.nextLine()).equalsIgnoreCase("#bad")){
                qtemp.addTrue(temp);
                //       System.out.println("правильный "+temp);
            }

            while(!(temp=scanner.nextLine()).equalsIgnoreCase("#vopros")){
                qtemp.addFalse(temp);
                //      System.out.println("неверный "+temp);
            }

            test.add(qtemp);
        }
        System.out.println("Файл открыт");


    }
}

package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
    int n;
    Buffer buffer;
    String fileName;
    long startTime;
    String mxPrime = "0";
    int count = 0;

    public Consumer(int n, Buffer buffer, String fileName, long startTime) {
        this.n = n;
        this.buffer = buffer;
        this.fileName = fileName;
        this.startTime = startTime;

    }


    @Override
    public void run() {
        String word = " ";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            long endTime = System.currentTimeMillis();
            JLabel l1, o1 = new JLabel(), l2, o2 = new JLabel(), l3, o3 = new JLabel();

            while (true) {
                if (count == 0) {

                    JFrame frame_ = new JFrame("Output Screen ^_^");
                    l1 = new JLabel("The Largest Prime Number:                        ");
                    l1.setBounds(30, 20, 300, 30);

                    o1 = new JLabel(mxPrime);
                    o1.setBounds(310, 20, 200, 30);

                    l2 = new JLabel("# Of Elements (Prime Number) Generated:          ");
                    l2.setBounds(30, 60, 300, 30);

                    o2 = new JLabel(Integer.toString(count));
                    o2.setBounds(310, 60, 200, 30);

                    l3 = new JLabel("Time Elapsed Since The Start Of Processing:      ");
                    l3.setBounds(30, 100, 300, 30);

                    o3 = new JLabel((endTime - startTime + " ms"));
                    o3.setBounds(310, 100, 200, 30);

                    JButton b = new JButton("Finish");
                    b.setBounds(350, 150, 100, 30);
                    b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.exit(0);
                        }
                    });

                    frame_.setSize(600, 250);
                    frame_.add(l1);
                    frame_.add(o1);
                    frame_.add(l2);
                    frame_.add(o2);
                    frame_.add(l3);
                    frame_.add(o3);
                    frame_.add(b);

                    frame_.setLayout(null);
                    frame_.setVisible(true);
                }
                if (buffer.numOfElements == 0 && buffer.finish) {
                    //long endTime = System.currentTimeMillis();
                    fileWriter.close();
                    System.out.println("\nclose");
                    break;

                } else {
                    word = buffer.remove();
                    mxPrime = word;
                    count++;
                    endTime = System.currentTimeMillis();
                    fileWriter.write("\" " + word + " \" , ");
                    o1.setText(mxPrime);
                    o2.setText(Integer.toString(count));
                    o3.setText(endTime - startTime + " ms");
                    //TimeUnit.SECONDS.sleep(1);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}

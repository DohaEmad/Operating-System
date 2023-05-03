package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {

        JFrame frame_ = new JFrame("Generate prime numbers ^_^");
        JLabel label_1, label_2, label_3;
        label_1 = new JLabel("Enter Your Number");
        label_1.setBounds(50, 20, 300, 25);
        final JTextField input1 = new JTextField();
        input1.setBounds(190, 25, 150, 25);

        label_2 = new JLabel("Enter Buffer Size");
        label_2.setBounds(50, 70, 300, 25);
        final JTextField input2 = new JTextField();
        input2.setBounds(190, 75, 150, 25);

        label_3 = new JLabel("Enter Your File Name");
        label_3.setBounds(50, 115, 300, 25);
        final JTextField input3 = new JTextField();
        input3.setBounds(190, 120, 150, 25);

        JButton button_ = new JButton("Produce");
        button_.setBounds(350, 150, 100, 30);

        button_.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = Integer.valueOf(input1.getText());
                int bufferSize = Integer.valueOf(input2.getText());
                String fileName = String.valueOf(String.valueOf(input3.getText()));
                Buffer buffer = new Buffer(bufferSize);
                long startTime = System.currentTimeMillis();
                Thread producer = new Thread(new Producer(n, buffer, startTime));
                Thread consumer = new Thread(new Consumer(n, buffer, fileName, startTime));
                producer.start();
                consumer.start();



            }
        });
        frame_.add(label_1);
        frame_.add(label_2);
        frame_.add(label_3);
        frame_.add(input3);
        frame_.add(input1);
        frame_.add(input2);
        frame_.add(button_);
        frame_.setSize(600, 250);
        frame_.setLayout(null);
        frame_.setVisible(true);


    }
}

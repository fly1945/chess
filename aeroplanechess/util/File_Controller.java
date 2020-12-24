package xyz.chengzi.aeroplanechess.util;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class File_Controller {
    static String input_file_name;
    static String file_name_from_disk;
    public static void main(String[] args) {
        get_file_name_from_disk_2();
    }
    public static String[] file_list(){
        File file=new File("src/saves");
        String file_list[]=file.list();
//        for (int i = 0; i < file_list.length; i++) {
//            System.out.println(file_list[i]);
//        }
        return file_list;
    }
    public static void input_file_name(){
        JFrame jFrame=new JFrame();
        jFrame.setVisible(true);
        jFrame.setBounds(500, 500, 80, 100);

        Container container=jFrame.getContentPane();

        JTextField file_name = new JTextField("1111");
        file_name.setEditable(true);
        file_name.setVisible(true);
        file_name.setHorizontalAlignment(JTextField.LEFT);
        container.add(file_name,BorderLayout.NORTH);
        Button decide=new Button("确定");
        container.add(decide,BorderLayout.SOUTH);
        decide.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //System.out.println(file_name.getText());
                input_file_name =file_name.getText();
                System.out.println(input_file_name);;
                jFrame.setVisible(false);
            }
        });

        return;
    }

    public static void get_file_name_from_disk(){
        String file_list[]=file_list();
        JFrame jFrame=new JFrame();
        jFrame.setBounds(100, 100, 120, file_list.length*50+50);
        //JPanel jPanel=new JPanel();
        //jFrame.add(jPanel,BorderLayout.CENTER);
        ButtonGroup buttonGroup = new ButtonGroup();
        List<JRadioButton> radioButtonList = new ArrayList<JRadioButton>();
        JRadioButton file_select;
        for (int i = 1; i < file_list.length+1; i++) {
            file_select = new JRadioButton(file_list[i-1]);
            System.out.println(file_list[i-1]);
            //file_select.setHorizontalAlignment(SwingConstants.LEFT);
            file_select.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            file_select.setBounds(10, i*50, 100, 10);
            file_select.setSelected(true);
            //file_select.setContentAreaFilled(false);
            buttonGroup.add(file_select);
            jFrame.add(file_select);
            radioButtonList.add(file_select);
        }
        jFrame.setVisible(true);
        Button decide=new Button("确定");
        jFrame.add(decide,BorderLayout.SOUTH);
        decide.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                for (JRadioButton get: radioButtonList
                     ) {
                    if(get.isSelected()){
                        System.out.println(get.getText());;
                        file_name_from_disk=get.getText();
                        //调用剩下的逻辑
                        break;
                    }
                }
            }
        });
        return;
    }

    public static void get_file_name_from_disk_2(){
        String file_list[]=file_list();
        JFrame jFrame=new JFrame();
        jFrame.setBounds(100, 100, 120, file_list.length*50+50);
        //JPanel jPanel=new JPanel();
        //jFrame.add(jPanel,BorderLayout.CENTER);
        JPanel jPanel=new JPanel();
        jPanel.setLayout( new GridLayout(4,4));

        JButton file_select;
//        for (int i = 1; i < file_list.length+1; i++) {
//            file_select = new JButton(file_list[i-1]);
//            System.out.println(file_list[i-1]);
//            //file_select.setHorizontalAlignment(SwingConstants.LEFT);
//            file_select.setFont(new Font("Segoe UI", Font.PLAIN, 10));
//            file_select.setBounds(10, i*50, 100, 10);
//            file_select.setSelected(true);
//            //file_select.setContentAreaFilled(false);
//            jFrame.add(file_select);
//        }
        file_select = new JButton(file_list[0]);
        System.out.println(file_list[0]);
        //file_select.setHorizontalAlignment(SwingConstants.LEFT);
        file_select.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        //file_select.setBounds(10, 1*50, 500, 10);
        file_select.setSelected(true);
        //file_select.setContentAreaFilled(false);
        //jFrame.add(file_select);
        jFrame.add(jPanel, BorderLayout.SOUTH);
        jPanel.add(file_select);
        jFrame.setVisible(true);
    }
    public static void saves(){

    }
}

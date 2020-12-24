package xyz.chengzi.aeroplanechess.util;

import xyz.chengzi.aeroplanechess.controller.GameController;
import xyz.chengzi.aeroplanechess.model.Save;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class file_controller_2 {
    public static String name_to_load;
    public static String name_to_save;
    public static void main(String[] args) {
        //loadSave();
        //System.out.println(name_to_load);
        delete("jlk");
    }
    public static void loadGame(Save save) {

    }
    public static void saveGame(Save save) {

    }
    public static void  loadSave() {
        JPanel contentPane  = new JPanel();
        JFrame frame = new JFrame();
        File file = new File("src/saves");
        String[] files = file.list();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(700, 300, 300, 450);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);


        JPanel panel = new JPanel();
        panel.setBounds(5, 361, 272, 44);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton loadButton = new JButton("LOAD");
        loadButton.setBounds(0, 0, 272, 40);
        panel.add(loadButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(5, 0, 277, 362);
        contentPane.add(scrollPane);

        JList<String> list = new JList<String>(files);
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {
                if(!list.getValueIsAdjusting()) {
                    System.out.println(list.getSelectedValue());
                }
            }
        });
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                name_to_load = (String)list.getSelectedValue();
                frame.dispose();
            }
        });
        scrollPane.setViewportView(list);
        frame.setVisible(true);
    }

    private static void show_wrong(String file_name){
        JPanel contentPane  = new JPanel();
        JFrame frame = new JFrame();
    }

    private static GameController load(String file_name){
        GameController in_controller = null;
        try {
            ObjectInputStream in=null;
            in=new ObjectInputStream(new FileInputStream("src/saves/"+file_name));
            in_controller=(GameController)in.readObject();
        }catch (Exception e){
            System.out.println("wrong file");
            wrong_file.main();
            wrong_file.main();
            delete(name_to_load);
            loadSave();
        }
        return in_controller;
    }
    public static void delete(String file_name){

        System.out.println("file_name "+file_name);
        File file=new File("src/saves/"+file_name+"");
        System.out.println(file.exists());
        System.out.println("delete");
        file.delete();
    }
    private static void save(String file_name,GameController controller){
        ObjectOutputStream out;
        try{
            out=new ObjectOutputStream(new FileOutputStream("chess_save1_test"));
            System.out.println(controller.getDice()[0]+" "+controller.getDice()[1]);
            System.out.println(controller.getCurrentPlayer());
            System.out.println(controller.getSmallTurnCount());
            out.writeObject(controller);
        }catch (Exception r){
            r.printStackTrace();
        }
    }
    static class wrong_file extends JFrame{
        private JPanel contentPane;
        public static void main() {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        wrong_file frame = new wrong_file();
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public wrong_file() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(500, 500, 300, 150);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);

            JButton btnNewButton = new JButton("\u786E\u8BA4");
            btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    dispose();
                }
            });
            btnNewButton.setBounds(22, 69, 103, 25);
            contentPane.add(btnNewButton);

            JButton btnNewButton_1 = new JButton("\u9000\u51FA");
            btnNewButton_1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    dispose();
                }
            });
            btnNewButton_1.setBounds(156, 69, 103, 25);
            contentPane.add(btnNewButton_1);

            JLabel lblNewLabel = new JLabel("´æµµ´íÎó");
            lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel.setBounds(96, 11, 82, 35);
            contentPane.add(lblNewLabel);
        }
    }
}

package xyz.chengzi.aeroplanechess.util;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import xyz.chengzi.aeroplanechess.controller.GameController;
import xyz.chengzi.aeroplanechess.model.Save;
import xyz.chengzi.aeroplanechess.view.MyChessComponent;
import xyz.chengzi.aeroplanechess.view.StartMenu;

public class FileController {
	public static String name;

	public static void saveGame(GameController controller) {
		JFrame frame = new JFrame();
		JPanel contentPane;
		JTextField textField;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(600, 400, 300, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("SAVE");

		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		
		textField = new JTextField();
		textField.setFont(new Font("Ó×Ô²", Font.PLAIN, 20));
		contentPane.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Ó×Ô²", Font.BOLD, 19));
		textPane.setEditable(false);
		textPane.setText("´æµµÃû³Æ£º");
		contentPane.add(textPane, BorderLayout.WEST);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ObjectOutputStream out;
				try{
					out=new ObjectOutputStream(new FileOutputStream("src/saves/"+textField.getText()));
					for(MyChessComponent chess : controller.getChessList()) {
						System.out.println(chess.toString());
					}
					frame.dispose();
					out.writeObject(controller);
				}catch (Exception r){
					r.printStackTrace();
				}
			}
		});
		frame.setVisible(true);
	}
	public static void  loadSave(GameController controller,StartMenu menu) {
		JPanel contentPane  = new JPanel();
		JFrame frame = new JFrame();
		File read_file= new File("src/saves");
		String[] files = read_file.list();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(500, 300, 300, 450);
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
		list.setSelectedIndex(0);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(!list.getValueIsAdjusting()) {
					System.out.println(list.getSelectedValue());

				}
			}
		});



		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				name = (String)list.getSelectedValue();
				try{
					ObjectInputStream in;
					GameController in_controller = null;
					in=new ObjectInputStream(new FileInputStream("src/saves/"+name));
					in_controller=(GameController)in.readObject();
					controller.reset(in_controller);
					System.out.println(controller.getSmallTurnCount());
					System.out.println(in_controller.getCurrentPlayer());;
					controller.getMainFrame().setVisible(true);
					frame.dispose();
					menu.dispose();
					in.close();
				}catch (Exception e){
//					File [] get=read_file.listFiles();
//					int len= get.length;
//					for (int i = 0; i <len; i++) {
//						if(get[i].getName().equals(name)){
//							get[i].delete();
//							//System.out.println("delete");
//						}
//					}
					file_controller_2.wrong_file.main();
					//System.out.println("name: "+name);
				}finally {
					
				}
 			}
		});
		scrollPane.setViewportView(list);
		frame.setVisible(true);
	}
}

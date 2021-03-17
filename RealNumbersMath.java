package herman;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JTextField;

public class RealNumbersMath {

	private JFrame frame;
	private JTextField textFieldMean;
	private JTextField textFieldStdDev;
	private JButton btnCalcStats;
	private JButton btnChooseFile;
	private JFileChooser choose;
	private File numFile;


	/**
	 * @author KatherineHerman-Williams
	 * @version 1.0
	 * 
	 * Launch the application.
	 * 
	 * @param args args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RealNumbersMath window = new RealNumbersMath();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RealNumbersMath() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Statistics Calculator");
		lblNewLabel.setBounds(157, 17, 133, 16);
		frame.getContentPane().add(lblNewLabel);
		
		btnChooseFile = new JButton("Choose File");
		btnChooseFile.setBounds(23, 44, 117, 29);
		frame.getContentPane().add(btnChooseFile);
		
		JLabel lblNewLabel_1 = new JLabel("Mean:");
		lblNewLabel_1.setBounds(49, 137, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Standard Deviation:");
		lblNewLabel_2.setBounds(49, 170, 133, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		btnCalcStats = new JButton("Calculate Stats");
		btnCalcStats.setBounds(157, 82, 117, 29);
		frame.getContentPane().add(btnCalcStats);
		
		textFieldMean = new JTextField();
		textFieldMean.setBounds(204, 132, 171, 26);
		frame.getContentPane().add(textFieldMean);
		textFieldMean.setColumns(10);
		
		textFieldStdDev = new JTextField();
		textFieldStdDev.setBounds(204, 165, 171, 26);
		frame.getContentPane().add(textFieldStdDev);
		textFieldStdDev.setColumns(10);
	}
		
		private void createEvents() {
			btnCalcStats.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buildOutput();//program calculates stats when button pressed
				}
			});
			btnChooseFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {//needed to be in createEvents;
					chooseFile();//user chooses file when button pressed
				}
			});
		}	
					
			
			
		private void buildOutput() {
            StatsCalc math = new StatsCalc(numFile);
            try {
                math.addToList();//creates list of numbers from numfile
                textFieldMean.setText(String.valueOf(math.mean()));//displays mean in text field
                textFieldStdDev.setText(String.valueOf(math.stdDev()));//displays standard dev in txt field
            } 
            catch (IOException ioException) {//don't forget
                ioException.printStackTrace();
            }
		}

		
		
		private void chooseFile() {
			//choose file code:
            File F = new File("/Users/KatherineHerman-Williams/eclipse-workspace/HW5/");
            choose = new JFileChooser(F, FileSystemView.getFileSystemView());//user chooses file

            int i = choose.showOpenDialog(null);
            
            if(i == JFileChooser.APPROVE_OPTION){
                numFile = new File(choose.getSelectedFile().getAbsolutePath());

            }
            else{
                JOptionPane.showMessageDialog(null, "Please choose a file.");//shows error message if no file chosen
            }
	
	}
}

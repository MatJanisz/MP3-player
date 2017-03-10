package mp3.player.apk;

import javazoom.jl.player.Player;
import mp3.player.apk.tool.Tools;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.*;

public class App extends JFrame implements ActionListener {
	
	private JTextField tLoad;
	private JLabel ltext;
	private JButton bPlay, bPause, bLoad, bResume, bStop;
	public static String Path;
	Tools T = new Tools();
	
	App() {
		super("MP3 player");
		setSize(350, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);

		ltext = new JLabel("Song:");
		ltext.setBounds(10, 10, 80, 20);
		ltext.setFont(new Font("Arial",Font.BOLD,14));
		panel.add(ltext);

		tLoad = new JTextField();
		tLoad.setBounds(60, 10, 250, 25);
		tLoad.setEnabled(false);
		panel.add(tLoad);

		bPlay = new JButton("Play");
		bPlay.setBounds(60, 40, 80, 25);
		panel.add(bPlay);
		bPlay.addActionListener(this);

		bPause = new JButton("Pause");
		bPause.setBounds(140, 40, 80, 25);
		panel.add(bPause);
		bPause.addActionListener(this);

		bLoad = new JButton("Load");
		bLoad.setBounds(220, 40, 80, 25);
		panel.add(bLoad);
		bLoad.addActionListener(this);
		
		bResume = new JButton("Resume");
		bResume.setBounds(60, 65, 80, 25);
		panel.add(bResume);
		bResume.addActionListener(this);
		
		bStop = new JButton("Stop");
		bStop.setBounds(140, 65, 80, 25);
		panel.add(bStop);
		bStop.addActionListener(this);

		setContentPane(panel);
		setVisible(true);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		
		if (source == bLoad) {
			JFileChooser fc = new JFileChooser();
			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File plk = fc.getSelectedFile();
				Path = plk.getAbsolutePath();
				tLoad.setText(plk.getName());
				if(!Pattern.matches(".*\\.mp3", plk.getName()))
				{
					tLoad.setText("");
					JOptionPane.showMessageDialog(null,
						    "Chosen file must have mp3 extension",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		} 
		else if (source == bPlay) {

				T.Play(Path);
		}
		else if(source == bPause)
		{
			T.Pause();
		}
		
		else if(source == bResume)
		{
			T.Resume();
		}
		
		else if(source == bStop)
		{
			T.Stop();
		}

	}
	
	

}

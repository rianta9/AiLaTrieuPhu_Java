package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.spi.AudioFileReader;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.border.LineBorder;

import Bean.Audio;
import Bean.Cauhoi;
import Bo.GetFile_Bo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

	private JFrame frmAiLTriu;
	JTextArea CauHoi;
	private JTextField Chon1;
	private JTextField Chon2;
	private JTextField Chon3;
	private JTextField Chon4;
	private JLabel Level;
	private JLabel Start;
	private GetFile_Bo a = new GetFile_Bo();
	private ArrayList<Cauhoi> list;
	private int viTri = 0; // Vi tri cau hoi
	private String level = "file\\info\\level";
	private String image = "file\\Images\\Picture";
	private int goi = 1;
	private int loaitru = 1;
	private int totuvan = 1;
	private Clip clip;
	private Audio audio;
	private int cauduocchon; // câu được lựa chọn làm câu hỏi
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmAiLTriu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		list = a.getFile("file\\info\\level1.txt");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void BatNhac(String filename) {
		try {
			File p = new File(filename);
			AudioInputStream file = AudioSystem.getAudioInputStream(p);
			clip = AudioSystem.getClip();
			clip.open(file);
			clip.start();
			file.close();
		} catch (IOException e) {
			return;
		} catch(UnsupportedAudioFileException a) {
			return;
		} catch (LineUnavailableException e) {
			return;
		}
	}
	
	
	private void initialize() {
		
		JLabel Help1 = new JLabel("");
		Help1.setIcon(new ImageIcon("file\\Images\\jpge50.jpg"));
		
		JLabel Help2 = new JLabel("");
		Help2.setIcon(new ImageIcon("file\\Images\\jpgePhone.jpg"));
		
		JLabel Help3 = new JLabel("");
		Help3.setIcon(new ImageIcon("file\\Images\\jpgePeople.jpg"));
		
		Level  = new JLabel("");
		Level.setIcon(new ImageIcon("file\\Images\\Picture01.png"));
		
		frmAiLTriu = new JFrame();
		frmAiLTriu.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				if(clip != null) clip.stop();
				if(viTri != 0) Level.setIcon(new ImageIcon(image+Integer.toString(viTri)+".png")); // Load ảnh số câu
				Chon1.setOpaque(true);
				Chon1.setBackground(Color.black);
				Chon2.setOpaque(true);
				Chon2.setBackground(Color.black);
				Chon3.setOpaque(true);
				Chon3.setBackground(Color.black);
				Chon4.setOpaque(true);
				Chon4.setBackground(Color.black);
				
				
				if(viTri == 5) list = a.getFile(level+"2.txt");
				else if(viTri == 10) list = a.getFile(level+"3.txt");
				else if(viTri == 14) list = a.getFile(level+"final.txt");
				else if(viTri > 15) System.exit(0);
				
				
				// Chọn câu hỏi
				Random rand = new Random();
				try { // míe bị cái lỗi null khi sys.exit
					do{
						cauduocchon = rand.nextInt(list.size());
					}while(list.get(cauduocchon).isUsed());
					CauHoi.setText(list.get(cauduocchon).getCauhoi());
					Chon1.setText(list.get(cauduocchon).getLuachon1());
					Chon2.setText(list.get(cauduocchon).getLuachon2());
					Chon3.setText(list.get(cauduocchon).getLuachon3());
					Chon4.setText(list.get(cauduocchon).getLuachon4());
				} catch (NullPointerException e) {
					cauduocchon = 0;
				}
				
			}
			
		});
		frmAiLTriu.setForeground(new Color(0, 128, 0));
		frmAiLTriu.setTitle("Ai Là Triệu Phú");
		frmAiLTriu.setResizable(false);
		frmAiLTriu.getContentPane().setBackground(new Color(0, 0, 0));
		frmAiLTriu.setBounds(100, 2, 1136, 750);
		frmAiLTriu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		Start = new JLabel("");
		Start.setIcon(new ImageIcon("file\\Images\\ailatrieuphu.jpg"));
		
		Chon1 = new JTextField();
		Chon1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(Chon1.isFocusable()) {
					Chon1.setOpaque(true);
					Chon1.setBackground(Color.green);
					if(list.get(cauduocchon).checkAnswer(1)) {
						if(viTri >= 15) {
							JOptionPane.showMessageDialog(null, "Chúc mừng bạn đã trở thành quán quân của Ai Là Triệu Phú!");
							System.exit(0);
						}
						viTri++;
						BatNhac("file\\Music\\Dung.wav");
						JOptionPane.showMessageDialog(null, "Chúc mừng bạn đã trả lời đúng câu hỏi thứ " + Integer.toString(viTri)+"!");
					}
					else {
						BatNhac("file\\Music\\Sai.wav");
						JOptionPane.showMessageDialog(null, "Chia buồn bạn đã trả lời sai câu hỏi này!");
						System.exit(0);
					}
				}
			}
		});
		Chon1.setBorder(new LineBorder(new Color(0, 255, 255), 2, true));
		Chon1.setHorizontalAlignment(SwingConstants.CENTER);
		Chon1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Chon1.setForeground(new Color(255, 255, 240));
		Chon1.setBackground(new Color(0, 0, 0));
		Chon1.setColumns(10);
		
		CauHoi = new JTextArea();
		CauHoi.setBorder(new LineBorder(new Color(220, 20, 60), 4, true));
		CauHoi.setBackground(new Color(0, 0, 0));
		CauHoi.setForeground(new Color(255, 255, 224));
		CauHoi.setFont(new Font("Monospaced", Font.BOLD, 20));
		CauHoi.setWrapStyleWord(true);
		CauHoi.setLineWrap(true);
		
		Chon2 = new JTextField();
		Chon2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Chon2.isFocusable()) {
					Chon2.setOpaque(true);
					Chon2.setBackground(Color.green);
					if(list.get(cauduocchon).checkAnswer(2)) {
						if(viTri >= 15) {
							JOptionPane.showMessageDialog(null, "Chúc mừng bạn đã trở thành quán quân của Ai Là Triệu Phú!");
							System.exit(0);
						}
						viTri++;
						BatNhac("file\\Music\\Dung.wav");
						JOptionPane.showMessageDialog(null, "Chúc mừng bạn đã trả lời đúng câu hỏi thứ " + Integer.toString(viTri)+"!");
						
					}
					else {
						BatNhac("file\\Music\\Sai.wav");
						JOptionPane.showMessageDialog(null, "Chia buồn bạn đã trả lời sai câu hỏi này!");
						System.exit(0);
					}
				}
			}
		});
		Chon2.setBorder(new LineBorder(new Color(0, 255, 255), 2, true));
		Chon2.setHorizontalAlignment(SwingConstants.CENTER);
		Chon2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Chon2.setForeground(new Color(255, 255, 240));
		Chon2.setBackground(new Color(0, 0, 0));
		Chon2.setColumns(10);
		
		Chon3 = new JTextField();
		Chon3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Chon3.isFocusable()) {
					Chon3.setOpaque(true);
					Chon3.setBackground(Color.green);
					if(list.get(cauduocchon).checkAnswer(3)) {
						if(viTri >= 15) {
							JOptionPane.showMessageDialog(null, "Chúc mừng bạn đã trở thành quán quân của Ai Là Triệu Phú!");
							System.exit(0);
						}
						viTri++;
						BatNhac("file\\Music\\Dung.wav");
						JOptionPane.showMessageDialog(null, "Chúc mừng bạn đã trả lời đúng câu hỏi thứ " + Integer.toString(viTri)+"!");
						
					}
					else {
						BatNhac("file\\Music\\Sai.wav");
						JOptionPane.showMessageDialog(null, "Chia buồn bạn đã trả lời sai câu hỏi này!");
						System.exit(0);
					}
				}
			}
		});
		Chon3.setBorder(new LineBorder(new Color(0, 255, 255), 2, true));
		Chon3.setHorizontalAlignment(SwingConstants.CENTER);
		Chon3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Chon3.setForeground(new Color(255, 255, 240));
		Chon3.setBackground(new Color(0, 0, 0));
		Chon3.setColumns(10);
		
		Chon4 = new JTextField();
		Chon4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Chon4.isFocusable()) {
					Chon4.setOpaque(true);
					Chon4.setBackground(Color.green);
					if(list.get(cauduocchon).checkAnswer(4)) {
						if(viTri >= 15) {
							JOptionPane.showMessageDialog(null, "Chúc mừng bạn đã trở thành quán quân của Ai Là Triệu Phú!");
							System.exit(0);
						}
						viTri++;
						BatNhac("file\\Music\\Dung.wav");
						JOptionPane.showMessageDialog(null, "Chúc mừng bạn đã trả lời đúng câu hỏi thứ " + Integer.toString(viTri)+"!");
						
					}
					else {
						BatNhac("file\\Music\\Sai.wav");
						JOptionPane.showMessageDialog(null, "Chia buồn bạn đã trả lời sai câu hỏi này!");
						System.exit(0);
					}
				}
			}
		});
		Chon4.setBorder(new LineBorder(new Color(0, 255, 255), 2, true));
		Chon4.setHorizontalAlignment(SwingConstants.CENTER);
		Chon4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Chon4.setForeground(new Color(255, 255, 240));
		Chon4.setBackground(new Color(0, 0, 0));
		Chon4.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frmAiLTriu.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(68)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(CauHoi, GroupLayout.PREFERRED_SIZE, 499, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(Chon1, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(Chon2, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(Chon3, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(Chon4, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(158)
							.addComponent(Start, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)))
					.addGap(70)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(Help1)
							.addGap(18)
							.addComponent(Help2)
							.addGap(18)
							.addComponent(Help3)
							.addGap(30))
						.addComponent(Level))
					.addGap(28))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(Help1)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(Help3)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(Help2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED))))
							.addComponent(Level))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(41)
							.addComponent(Start)
							.addGap(35)
							.addComponent(CauHoi, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(Chon2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(Chon1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(Chon4, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(Chon3, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		frmAiLTriu.getContentPane().setLayout(groupLayout);
	}
}

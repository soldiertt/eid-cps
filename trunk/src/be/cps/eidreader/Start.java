package be.cps.eidreader;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Start {

	private static PanelImage panelImg;
	public static JButton buttonRead;
	private static JButton buttonOK;
	private static ReadCardThread run1;
	private static BufferedImage bgImage;
	
	public static void main(String[] args) {

		final CardReader cardReader = new CardReader();
		
		final JFrame myFrame = new JFrame("Enregistrement");
		myFrame.setLayout(new BorderLayout());
		
		buttonRead = new JButton(" LECTURE ");
		buttonOK = new JButton(" OK ");
		buttonOK.setVisible(false);
		try {
			bgImage = ImageIO.read(Start.class.getResourceAsStream("/images/cpslogo.jpg"));
			panelImg = new PanelImage(bgImage);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		panelImg.setVisible(true);
		
		buttonOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				cardReader.updateFile(run1.getId());
				panelImg.setImage(bgImage);
				panelImg.repaint();
				buttonOK.setVisible(false);
				myFrame.getContentPane().validate();
				myFrame.getContentPane().repaint();
			}
		});
		
		buttonRead.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				buttonRead.setText("Reading...please wait");
				buttonRead.setEnabled(false);
				panelImg.setVisible(false);
				buttonOK.setVisible(false);
				run1 = new ReadCardThread(cardReader, new UiUpdater(panelImg, buttonOK, buttonRead));
				Thread thread1 = new Thread(run1);
				thread1.start();
			}
		});
		myFrame.getContentPane().add(buttonRead, BorderLayout.NORTH);
		myFrame.getContentPane().add(panelImg, BorderLayout.CENTER);
		myFrame.getContentPane().add(buttonOK, BorderLayout.SOUTH);
		
		myFrame.setSize(350, 400);
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		myFrame.setVisible(true);

		System.out.println("END");
	}

}

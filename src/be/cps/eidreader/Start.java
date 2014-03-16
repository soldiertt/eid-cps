package be.cps.eidreader;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Start {

	private static PanelImage panelImg;
	private static JButton buttonRead;
	private static JButton buttonOK;
	private static ReadCardThread run1;
	
	public static void main(String[] args) {

		final CardReader cardReader = new CardReader();
		
		final JFrame myFrame = new JFrame("Enregistrement");
		myFrame.setLayout(new BorderLayout());
		
		buttonRead = new JButton(" LECTURE ");
		buttonOK = new JButton(" OK ");
		buttonOK.setVisible(false);
		panelImg = new PanelImage(null);
		panelImg.setVisible(false);
		
		buttonOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				cardReader.updateFile(run1.getId());
				panelImg.setVisible(false);
				buttonOK.setVisible(false);
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
		
		myFrame.setSize(300, 400);
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		myFrame.setVisible(true);

		System.out.println("END");
	}

}

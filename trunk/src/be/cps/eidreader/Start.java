package be.cps.eidreader;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Start {

	private static Identity id;
	private static JPanel panelImg;
	
	public static void main(String[] args) {

		final CardReader cardReader = new CardReader();
		
		final JFrame myFrame = new JFrame("Enregistrement");
		myFrame.setLayout(new BorderLayout());
		final JButton button = new JButton(" OK ");
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				cardReader.updateFile(id);
				myFrame.getContentPane().remove(panelImg);
				myFrame.getContentPane().remove(button);
				myFrame.getContentPane().validate();
				myFrame.getContentPane().repaint();
			}
		});
		JButton buttonRead = new JButton(" LECTURE ");
		buttonRead.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ImageIcon img = null;
				try {
					img = new ImageIcon(new File("c:\\data\\cps.gif").toURL());
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panelImg = new PanelImage(img.getImage());
				/*panelImg.setSize(150, 150);
				panelImg.setVisible(true);*/
				myFrame.getContentPane().add(panelImg, BorderLayout.CENTER);
				myFrame.getContentPane().validate();
				myFrame.getContentPane().repaint();
				myFrame.validate();
				myFrame.repaint();
				id = cardReader.readCardInfo();
				panelImg = new PanelImage(id.getPhoto());
				myFrame.getContentPane().add(panelImg, BorderLayout.CENTER);
				myFrame.getContentPane().add(button,  BorderLayout.PAGE_END);
				myFrame.getContentPane().validate();
				myFrame.getContentPane().repaint();
			}
		});
		myFrame.getContentPane().add(buttonRead,  BorderLayout.PAGE_START);
		
		
		myFrame.setSize(300, 400);
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		myFrame.setVisible(true);

		System.out.println("END");
	}

}

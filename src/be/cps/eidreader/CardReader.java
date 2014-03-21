package be.cps.eidreader;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import be.belgium.eid.eidlib.BeID;
import be.belgium.eid.exceptions.EIDException;

public class CardReader {

	private static final String FILENAME = "c:\\data\\logfile.csv";
	
	public Identity readCardInfo() {
		BeID eID = new BeID(true); // We allow information to be fetched from
		// test cards
		Identity id = null;
		
		String first = "", last = "", nrn = "", address = "";
		// We fetch the information
		try {
			first = eID.getIDData().get1stFirstname();
			last = eID.getIDData().getName();
			nrn = eID.getIDData().getNationalNumber();
			address = eID.getIDAddress().toString()
					.replaceAll("(\r\n|\n)", " ");
			Image photo = eID.getIDPhoto().getImage();
			id = new Identity(first, last, nrn, address, photo);
			
		} catch (EIDException e) {
			if (e.getMessage().equals("EID Exception: No card present")) {
				final JFrame okFrame = new JFrame("Erreur");
				okFrame.setLayout(new BorderLayout());
				JLabel errorMessage = new JLabel(" Veuillez insérer une carte eID, puis recommencez !");
				okFrame.getContentPane().add(errorMessage, BorderLayout.CENTER);
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						okFrame.dispose();
						Start.buttonRead.setText(" LECTURE ");
						Start.buttonRead.setEnabled(true);
					}
				});
				okFrame.getContentPane().add(okButton, BorderLayout.SOUTH);
				okFrame.setSize(320,100);
				okFrame.setVisible(true);
			} else {
				e.printStackTrace();
			}
		}
		return id;
	}
	
	public void updateFile(Identity id) {
		
		try {
			File testExist = new File(FILENAME);
			boolean fileExists = testExist.exists();
			String dateLog = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
			String timeLog = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
			PrintWriter out = new PrintWriter(new BufferedWriter(
			new FileWriter(FILENAME, true)));
			if (!fileExists) {
				out.println("Date;Heure;Prénom;Nom;Numéro national;Adresse");
			}
			out.println(dateLog + ";" + timeLog + ";" + id.getFirst() + ";" + id.getLast() + ";" + id.getNrn() + ";" + id.getAdresse());
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

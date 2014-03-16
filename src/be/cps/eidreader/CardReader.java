package be.cps.eidreader;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
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

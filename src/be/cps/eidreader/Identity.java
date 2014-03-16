package be.cps.eidreader;

import java.awt.Image;

public class Identity {

	private String first;
	private String last;
	private String nrn;
	private String adresse;
	private Image photo;
	
	
	public Identity(String first, String last, String nrn, String adresse,
			Image photo) {
		super();
		this.first = first;
		this.last = last;
		this.nrn = nrn;
		this.adresse = adresse;
		this.photo = photo;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getNrn() {
		return nrn;
	}
	public void setNrn(String nrn) {
		this.nrn = nrn;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public Image getPhoto() {
		return photo;
	}
	public void setPhoto(Image photo) {
		this.photo = photo;
	}
	
	
}

package be.cps.eidreader;

public class ReadCardThread implements Runnable {

	private volatile Identity id;
	private Callback c;
	private CardReader cardReader;
	
	public ReadCardThread(CardReader cardReader, Callback c){
		this.cardReader = cardReader;
		this.c=c;
	}
	
	public void run(){
		
		id = this.cardReader.readCardInfo();
		this.c.callback(id);//callback
	  
	}

	public Identity getId() {
		return id;
	}

}

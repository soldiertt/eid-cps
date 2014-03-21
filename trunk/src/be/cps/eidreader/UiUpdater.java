package be.cps.eidreader;

import javax.swing.JButton;

public class UiUpdater implements Callback {

	private PanelImage panelImg;
	private JButton buttonOK;
	private JButton buttonRead;
	
	public UiUpdater(PanelImage panelImg, JButton buttonOK, JButton buttonRead) {
		this.panelImg = panelImg;
		this.buttonOK = buttonOK;
		this.buttonRead = buttonRead;
	}


	@Override
	public void callback(Identity id) {
		if (id != null) {
			panelImg.setImage(id.getPhoto());
			panelImg.repaint();
			panelImg.setVisible(true);
			buttonOK.setVisible(true);
			buttonRead.setText(" LECTURE ");
			buttonRead.setEnabled(true);
		}
	}

}

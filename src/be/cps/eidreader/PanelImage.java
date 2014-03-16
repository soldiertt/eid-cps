package be.cps.eidreader;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class PanelImage extends JPanel {

		
		/**
	 * 
	 */
	private static final long serialVersionUID = -8823344020451190308L;
		private Image image;
		
		
        public PanelImage(Image image) {
			super();
			this.image = image;
		}


		public void paintComponent(Graphics g){
                g.drawImage(image, 0, 0, this);
                //Pour une image de fond
                //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                
        }               

}

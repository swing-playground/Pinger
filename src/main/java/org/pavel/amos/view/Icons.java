package org.pavel.amos.view;

import javax.swing.ImageIcon;

public enum Icons {
	
	DELETE, PLUS, WEB, WEBF, PLAY, PAUSE;
	
	public ImageIcon getIcon(){
		return new ImageIcon(MainView.class.getResource("/icons/" + this.toString().toLowerCase() + ".png"));
	}
}

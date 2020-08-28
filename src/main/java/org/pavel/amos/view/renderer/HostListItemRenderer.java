package org.pavel.amos.view.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

import org.pavel.amos.view.Icons;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class HostListItemRenderer extends DefaultListCellRenderer{
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		return new CustomLabel(value.toString(), isSelected, cellHasFocus);
	}
	
	private static class CustomLabel extends JLabel {
		public CustomLabel(String text, boolean isSelected, boolean cellHasFocus){
			setForeground ( isSelected ? Color.WHITE : Color.BLACK );
			setBackground(isSelected ? Color.DARK_GRAY : Color.LIGHT_GRAY);
			setIcon(cellHasFocus ? Icons.WEBF.getIcon() : Icons.WEB.getIcon());
			setOpaque(true);
			setBorder(new EmptyBorder(5,10,5,0));
			setText(text);
		}
	}
}

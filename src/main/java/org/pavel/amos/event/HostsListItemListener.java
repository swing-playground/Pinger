package org.pavel.amos.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.pavel.amos.async.PingHandler;
import org.pavel.amos.view.PingerGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HostsListItemListener implements MouseListener, ActionListener {

	@Autowired
	JList<String> hostsList;

	@Autowired
	JPanel contentPanel;

	@Autowired
	JToggleButton pauseResumeBtn;

	@Autowired
	PingHandler pingHandler;

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && e.getSource() instanceof JList) {
			if (!pauseResumeBtn.isVisible()) {
				pauseResumeBtn.setVisible(true);
				pauseResumeBtn.addActionListener(this);
			}
			contentPanel.add(new PingerGraph(pingHandler, hostsList));
			contentPanel.revalidate();
			contentPanel.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public void actionPerformed(ActionEvent e) {
		if (pauseResumeBtn.isSelected()) {
			pingHandler.resume();
		}
		else {
			pingHandler.pause();
		}
	}
}

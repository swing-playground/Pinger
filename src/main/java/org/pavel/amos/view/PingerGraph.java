package org.pavel.amos.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.pavel.amos.async.PingHandler;
import org.pavel.amos.async.PingThread;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PingerGraph extends JPanel {

	String host;

	List<Point> points;

	PingHandler pingHandler;

	public PingerGraph(PingHandler pingHandler, JList<String> hostsList) {
		this.pingHandler = pingHandler;
		host = hostsList.getSelectedValue();
		setMaximumSize(new Dimension(32767, 300));
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setMinimumSize(new Dimension());
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(142)
						.addContainerGap(195, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addContainerGap(244, Short.MAX_VALUE)));
		setLayout(groupLayout);
		points = new ArrayList<>();
		pingHandler.scheduleWithFixedDelay(new PingThread(this, host), 1, 1,
				TimeUnit.SECONDS);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Double height = this.getSize().getHeight();
		int width = ((Double) this.getSize().getWidth()).intValue();
		int baseLine = height.intValue() / 2;

		Point sPoint = null;
		Point ePoint = null;

		for (Point p : points) {
			sPoint = ePoint;
			ePoint = p;
			if (sPoint != null) {
				g.fillOval(ePoint.x, ePoint.y + baseLine, 4, 4);
				g.drawLine(sPoint.x + 2, sPoint.y + 2 + baseLine, ePoint.x + 2,
						ePoint.y + 2 + baseLine);
			}
			else {
				if (ePoint != null) {
					g.fillOval(ePoint.x, ePoint.y + baseLine, 4, 4);
				}
			}
		}
		g.drawString(host, (width - host.length()) / 2, 30);
	}

	public void addPoint(Point p) {
		if (p != null) {
			points.add(p);
		}
		repaint();
	}

	public void resume() {
		pingHandler.resume();
	}

	public void pause() {
		pingHandler.pause();
	}
}

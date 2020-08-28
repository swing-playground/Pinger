package org.pavel.amos.async;

import java.awt.Point;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

import org.pavel.amos.view.PingerGraph;

public class PingThread implements Runnable {

	String host;
	AtomicInteger cnt;
	private PingerGraph panel;

	public PingThread(PingerGraph panel, String host) {
		this.host = host;
		this.cnt = new AtomicInteger(0);
		this.panel = panel;
	}

	public void run() {
		update();
	}

	private void update() {
		panel.addPoint(getPoint());
	}

	private Point getPoint() {
		int ping = ping();
		return new Point(cnt.getAndAdd(15), (ping - 100) * -1);
	}

	private int ping() {
		try {
			long before = System.currentTimeMillis();
			URL url = new URL(host);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			long after = System.currentTimeMillis();
			Double percent = (100d / (after - before)) * 100;
			return percent.intValue();
		}
		catch (Exception e) {
			return 100;
		}
	}
}

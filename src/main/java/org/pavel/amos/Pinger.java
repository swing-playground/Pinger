package org.pavel.amos;

import java.awt.EventQueue;

import org.pavel.amos.view.MainView;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Pinger {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Pinger.class)
				.headless(false).run(args);
		EventQueue.invokeLater(() -> {
			MainView ex = ctx.getBean(MainView.class);
			ex.setVisible(true);
		});
	}
}

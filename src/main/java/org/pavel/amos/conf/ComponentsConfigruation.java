package org.pavel.amos.conf;

import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.pavel.amos.event.AddHostEventListener;
import org.pavel.amos.event.HostsListItemListener;
import org.pavel.amos.event.RemoveHostEventListener;
import org.pavel.amos.view.Icons;
import org.pavel.amos.view.MainView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ComponentsConfigruation {

	@Bean(name = "addButton")
	@DependsOn({ "getHostModel" })
	public JButton getAddButton(AddHostEventListener addHostEventListener) {
		JButton jButton = new JButton("");
		jButton.addActionListener(addHostEventListener);
		jButton.setIcon(new ImageIcon(MainView.class.getResource("/icons/plus.png")));
		jButton.setBorderPainted(false);
		return jButton;
	}

	@Bean(name = "removeButton")
	@DependsOn({ "getList" })
	public JButton getRemoveButton(RemoveHostEventListener removeHostEventListener) {
		JButton removeBtn = new JButton("");
		removeBtn.setEnabled(false);
		removeBtn.addActionListener(removeHostEventListener);
		removeBtn.setIcon(new ImageIcon(MainView.class.getResource("/icons/delete.png")));
		removeBtn.setBorderPainted(false);
		return removeBtn;
	}
	
	@Bean
	public JToggleButton getPauseResumeBtn(RemoveHostEventListener removeHostEventListener) {
		JToggleButton pauseResumeBtn = new JToggleButton("");
		pauseResumeBtn.setVisible(false);
		pauseResumeBtn.setSelectedIcon(Icons.PAUSE.getIcon());
		pauseResumeBtn.setIcon(Icons.PLAY.getIcon());
		pauseResumeBtn.setSelected(true);
		return pauseResumeBtn;
	}
	
	

	@Bean
	public DefaultListModel<String> getHostModel() {
		return new DefaultListModel<>();
	}

	@Bean
	public JList<String> getList() {
		return new JList<>();
	}

	@Bean
	@DependsOn({ "getPauseResumeBtn" })
	public HostsListItemListener getHostsListItemListener() {
		return new HostsListItemListener();
	}

	@Bean(name="contentPanel")
	public JPanel getContentPanel() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(0, 1, 10, 10));
		return jPanel;
	}
}

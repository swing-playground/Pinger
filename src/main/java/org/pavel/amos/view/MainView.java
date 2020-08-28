package org.pavel.amos.view;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import org.pavel.amos.event.HostsListItemListener;
import org.pavel.amos.view.renderer.HostListItemRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@DependsOn("addButton")
public class MainView extends JFrame {

	@Autowired
	public MainView(@Qualifier("addButton") JButton addBtn,
			@Qualifier("removeButton") JButton removeBtn, JList<String> hostsList,
			DefaultListModel<String> defaultListModel,
			HostListItemRenderer hostListItemRenderer,
			HostsListItemListener hostsListItemListener,@Qualifier("contentPanel") JPanel contentPanel,
			JToggleButton pauseResumeBtn) {

		JScrollPane contentScrollPane = new JScrollPane();
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Pinger");
		setName("mainFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JSeparator separator = new JSeparator();

		JScrollPane scrollPane = new JScrollPane();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addComponent(addBtn)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(removeBtn)
						.addPreferredGap(ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
						.addComponent(pauseResumeBtn).addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 252,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(contentScrollPane, GroupLayout.DEFAULT_SIZE, 176,
								Short.MAX_VALUE)
						.addContainerGap())
				.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
						440, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(addBtn).addComponent(removeBtn)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addContainerGap().addComponent(pauseResumeBtn)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 12,
								GroupLayout.PREFERRED_SIZE)
						.addGap(28)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(contentScrollPane, GroupLayout.DEFAULT_SIZE,
										178, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 178,
										Short.MAX_VALUE))));

		contentScrollPane.setViewportView(contentPanel);

		hostsList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		hostsList.setFont(new Font("Arial", Font.PLAIN, 12));
		hostsList.setModel(defaultListModel);
		hostsList.addMouseListener(hostsListItemListener);
		hostsList.setCellRenderer(hostListItemRenderer);
		scrollPane.setViewportView(hostsList);
		hostsList.addListSelectionListener(e -> removeBtn.setEnabled(true));
		contentPane.setLayout(gl_contentPane);
	}
}

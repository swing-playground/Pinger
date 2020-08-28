package org.pavel.amos.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import org.pavel.amos.db.HostsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RemoveHostEventListener implements ActionListener{

	@Autowired
	HostsRepo hostsRepo;
	
	@Autowired
	JList<String> hostsList;
	
	@Autowired
	DefaultListModel<String> defaultListModel;
	
	@Transactional
	public void actionPerformed(ActionEvent e) {
		String host = hostsList.getSelectedValue();
		hostsRepo.deleteByHost(host);
		defaultListModel.remove(hostsList.getSelectedIndex());
	}
}

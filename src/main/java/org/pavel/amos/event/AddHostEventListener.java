package org.pavel.amos.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import org.pavel.amos.db.HostsRepo;
import org.pavel.amos.db.model.HostModel;
import org.pavel.amos.view.Icons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddHostEventListener implements ActionListener{

	@Autowired
	DefaultListModel<String> defaultListModel;
	
	@Autowired
	HostsRepo hostsRepo;
	
	@Transactional
	public void actionPerformed(ActionEvent e) {
		 String name = JOptionPane.showInputDialog(null, "Add Host", null, JOptionPane.QUESTION_MESSAGE, Icons.WEBF.getIcon(), null, null).toString();
		 if (!StringUtils.isEmpty(name)){
			 defaultListModel.addElement(name);
			 hostsRepo.saveAndFlush(HostModel.builder().host(name).build());
		 }
	}
}

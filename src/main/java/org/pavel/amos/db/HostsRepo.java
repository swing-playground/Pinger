package org.pavel.amos.db;

import org.pavel.amos.db.model.HostModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostsRepo extends JpaRepository<HostModel, String>{

	public void deleteByHost(String host);
}

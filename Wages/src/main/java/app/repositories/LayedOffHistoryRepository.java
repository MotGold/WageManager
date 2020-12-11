package app.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.beans.LayedOffHistory;

public interface LayedOffHistoryRepository extends JpaRepository<LayedOffHistory, Integer> {
	
	List<LayedOffHistory> findByDate(Date date);

}

package app.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.beans.History;

public interface HistoryRepository extends JpaRepository<History, Integer> {

	List<History> findByDate(Date date);

}

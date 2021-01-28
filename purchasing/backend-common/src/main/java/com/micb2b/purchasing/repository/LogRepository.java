package com.micb2b.purchasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.micb2b.purchasing.domain.Log;


/**
 * @author jay
 * @date 2019-11-19
 */
@Repository
public interface LogRepository extends JpaRepository<Log, Long>, JpaSpecificationExecutor {

	/**
	 * 獲取一個時間段的IP記錄
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	@Query(value = "select count(*) FROM (select request_ip FROM log where create_time between ?1 and ?2 GROUP BY request_ip) as s", nativeQuery = true)
	Long findIp(String date1, String date2);
}

package cn.segema.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.segema.report.domain.Scheme;

@Repository
public interface SchemeRepository extends PagingAndSortingRepository<Scheme, String>,JpaRepository<Scheme, String>,JpaSpecificationExecutor<Scheme>  {
	
	 @Query(value = "SELECT * FROM report_scheme WHERE if(:#{#scheme.addressId}!='',address_id = :#{#scheme.addressId},1=1) ORDER BY create_time DESC", nativeQuery = true) 
	 public List<Scheme> findListByScheme(@Param("scheme") Scheme scheme); 
}

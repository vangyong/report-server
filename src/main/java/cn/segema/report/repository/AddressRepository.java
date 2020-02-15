package cn.segema.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.segema.report.domain.Address;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address, String>,JpaRepository<Address, String>,JpaSpecificationExecutor<Address>  {
	
	 @Query(value = "SELECT * FROM report_address WHERE if(:#{#address.status}!='',status = :#{#address.status},1=1) ORDER BY status,create_time DESC", nativeQuery = true) 
	 public List<Address> findListByAddress(@Param("address") Address address); 
}

package cn.segema.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.segema.report.domain.Order;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, String>,JpaRepository<Order, String>,JpaSpecificationExecutor<Order>  {
	
	 @Query(value = "SELECT * FROM report_order WHERE if(:#{#order.addressId}!='',address_id = :#{#order.addressId},1=1)"
	 		+ " AND if(:#{#order.nickName}!='',nick_name = :#{#order.nickName},1=1)"
	 		+ " AND if(:#{#order.schemeId}!='',scheme_id = :#{#order.schemeId},1=1)"
	 		+ " AND if(:#{#order.alipayAccount}!='',alipay_account = :#{#order.alipayAccount},1=1)"
	 		+ " AND if(:#{#order.expressOrder}!='',express_order LIKE %:#{#order.expressOrder}%,1=1)  ORDER BY create_time DESC", nativeQuery = true) 
	 public List<Order> findListByOrder(@Param("order") Order order); 
	 
	 
	 @Query(value = "SELECT * FROM report_order WHERE if(:#{#order.addressId}!='',address_id = :#{#order.addressId},1=1)"
			    + " AND if(:#{#order.schemeId}!='',scheme_id = :#{#order.schemeId},1=1)"
		 		+ " AND if(:#{#order.nickName}!='',nick_name = :#{#order.nickName},1=1)"
		 		+ " AND if(:#{#order.alipayAccount}!='',alipay_account = :#{#order.alipayAccount},1=1) ORDER BY create_time DESC", nativeQuery = true) 
	 public List<Order> findListByQuery(@Param("order") Order order); 
}

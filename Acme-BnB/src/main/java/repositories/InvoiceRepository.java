package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{

	
	
	// Dashboard
	
	@Query("select count(i) from Invoice i where (select count(i3) from Invoice i3 where i3.book.tenant = i.book.tenant) >= all(select count(i2) from Invoice i2 group by i2.book.tenant) group by i.book.tenant")
	Integer getMaximumInvoicesIssuedToTenants();
	
	@Query("select count(i) from Invoice i where (select count(i3) from Invoice i3 where i3.book.tenant = i.book.tenant) <= all(select count(i2) from Invoice i2 group by i2.book.tenant) group by i.book.tenant")
	Integer getMinimumInvoicesIssuedToTenants();
	
	@Query("select 1.0*count(i)/(select count(t) from Tenant t) from Invoice i")
	Double getAverageInvoicesIssuedToTenants();
	
	@Query("select sum(i.totalAmount) from Invoice i")
	Double getTotalAmountOfMoney();
}

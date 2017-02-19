package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{

	
	
	//DASHBOARD
	@Query("select distinct count(i) from Tenant t join t.books b join b.invoice i group by t having count(i) >= all(select count(i1) from Tenant t1 join t1.books b1 join b1.invoice i1 group by t1)")
	public Double maxInvoiceIssue();
	
	@Query("select distinct count(i) from Tenant t join t.books b join b.invoice i group by t having count(i) <= all(select count(i1) from Tenant t1 join t1.books b1 join b1.invoice i1 group by t1)")
	public Double minInvoiceIssue();
	
	@Query("select distinct (select (select count(t.books.size)*1.0 from Tenant t join t.books b where  b.invoice is not null)/count(t1) from Tenant t1) from Tenant t2 join t2.books b2")
	public Double avgInvoiceIssue();
	
	@Query("select sum(i.totalAmount) from Invoice i")
	public Double totalAmountInvoice();
}

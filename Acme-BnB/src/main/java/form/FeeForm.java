
package form;

import javax.validation.constraints.Min;

public class FeeForm {

	//Attributes
	private int		id;
	private double	amount;


	//Constructor
	public FeeForm() {
		super();
	}

	@Min(1)
	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	
}

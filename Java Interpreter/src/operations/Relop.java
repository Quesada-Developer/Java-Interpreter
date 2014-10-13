package operations;

import operators.RelopOperator;

public class Relop {
	
	private RelopOperator rOp;
	private Expression r1;
	private Expression r2;
	
	public Relop()
	{
		
	}
	
	public Relop(RelopOperator rOp, Expression r1, Expression r2)
	{
		if (r1 == null || r2 == null)
			throw new IllegalArgumentException ("null expression argument");
		this.rOp = rOp;
		this.r1 = r1;
		this.r2 = r2;
	}

	
	public boolean evaluate()
	{
		boolean value = false;
		
		switch (rOp)
		{
		case LESS:
				value = r1.evaluate() < r2.evaluate();
			break;
		case LESSEQUAL:
				value = r1.evaluate() <= r2.evaluate();
			break;
		case GREATER:
				value = r1.evaluate() > r2.evaluate();
			break;
		case GREATEREQUAL:
			value = r1.evaluate() >= r2.evaluate();
			break;
		case EQUAL:
			value = r1.evaluate() == r2.evaluate();
			break;
		case NOT:
			value = r1.evaluate() != r2.evaluate();
			break;
				
		}
		
		return value;
		
	}

}

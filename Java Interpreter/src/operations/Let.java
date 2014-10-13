package operations;

import components.Memory;

public class Let extends Expression
{
	private Id ch;
	private Expression exp1;
	
	public Expression getExp1() {
		return exp1;
	}

	public Let(Id ch, Expression exp1)
	{
		if(exp1 == null)
			throw new IllegalArgumentException("null expression argument");
		
		this.ch = ch;
		this.exp1 = exp1;
	}
	
	public void Execute()
	{
		Memory.store(ch.getCh(), exp1.evaluate());
	}

}

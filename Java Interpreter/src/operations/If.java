package operations;

import operations.Relop;

public class If {
	
	private Relop relop;
	private int lineNumber;
	
	public If(Relop relop, int lineNumber)
	{
		if(lineNumber > 999 || lineNumber < 1)
			throw new IllegalArgumentException("invalid line number");
		else
		{		
			this.relop = relop;
			this.lineNumber = lineNumber;
		}
	}

	public int Execute(int current)
	{
		if(relop.evaluate())
		{
			return lineNumber - 1;
		}
		else
			return current+1;
		
	}

}

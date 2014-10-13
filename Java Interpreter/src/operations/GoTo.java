package operations;

public class GoTo {

	private int lineNumber;
	
	public GoTo(int lineNumber)
	{
		if(lineNumber > 999 || lineNumber < 1)
			throw new IllegalArgumentException("invalid line number");
		else
			this.lineNumber = lineNumber;
	}
	
	public int returnNumber()
	{
		return lineNumber;
		
	}

	
}

package operations;

import components.Memory;
import exception.ParserException;

public class Id extends Expression
{

	private char ch;

	/**
	 * @param ch
	 * @throws ParserException 
	 */
	public Id(char ch) throws ParserException
	{
		if (ch <= 90 && ch >= 65)
			this.ch = ch;
		else
			throw new ParserException("ID must be a capital letter");
	}
	
	/* (non-Javadoc)
	 * @see Expression#evaluate()
	 */
	public int evaluate ()
	{
		return Memory.fetch (ch);
	}

	public char getCh()
	{
		return ch;
	}

}

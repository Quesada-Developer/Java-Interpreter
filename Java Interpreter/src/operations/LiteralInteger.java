package operations;


public class LiteralInteger extends Expression
{

	private int value;

	public LiteralInteger(int value)
	{
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see Expression#evaluate()
	 */
	public int evaluate ()
	{
		return value;
	}
}

package operations;

public class Print {

	private Id ch;
	
	public Print(Id ch)
	{
		this.ch = ch;
	}
	
	public void Execute()
	{
		System.out.println(ch.getCh());
	}
	
	
}

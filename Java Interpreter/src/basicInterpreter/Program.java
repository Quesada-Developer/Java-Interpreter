package basicInterpreter;

import java.util.List;

import exception.ParserException;

import operations.BasicStatement;
import operations.GoTo;
import operations.If;
import operations.Let;
import operations.Print;
import operations.Relop;
import operations.Stop;
import operators.TokenType;

public class Program {

	private List<BasicStatement> statement_list ;
	private int index;
	
	public Program(List<BasicStatement> statemnet_list)
	{
		this.statement_list = statemnet_list;
	}
	
	public void ExecuteProgram() throws ParserException
	{
		/*
		for(int index = 0; index < statement_list.size(); index++)
		{
			
			try
			{
				statement_list.get(index);
			}
			
			catch(NullPointerException e)
			{
				Id ch = new Id('a');
				statement_list.add(1,new BasicStatement(new Print(ch)));
				System.out.println(statement_list.get(1));
				
			}
				
		}*/
		for(index = 0; index < 999; index++)
		{
			try
			{
				Execute(index);			
			}
			catch(NullPointerException e)
			{
				
			}
		}
	}
		public void Execute(int index)
		{
			try
			{

				if(statement_list.get(index).returnType().equals(TokenType.IF_ST))
				{
					BasicStatement r1 = statement_list.get(index);
					Relop r2 = new Relop(r1.getrOp(), r1.getExp1(), r1.getExp2());
					If r3 = new If(r2, r1.getLineNumber());
					this.index = r3.Execute(index);
				}
				else if(statement_list.get(index).returnType().equals(TokenType.LET_ST))
				{
					BasicStatement r1 = statement_list.get(index);
					Let r2 = new Let(r1.getCh(), r1.getExp1());
					r2.Execute();
				}
				else if(statement_list.get(index).returnType().equals(TokenType.PRINT_ST))
				{
					BasicStatement r1 = statement_list.get(index);
					Print r2 = new Print(r1.getCh());
					r2.Execute();
				}
				else if(statement_list.get(index).returnType().equals(TokenType.GOTO_ST))
				{
					BasicStatement r1 = statement_list.get(index);
					GoTo r2 = new GoTo(r1.getLineNumber());
					this.index = r2.returnNumber();
				}
				else if(statement_list.get(index).returnType().equals(TokenType.STOP_ST))
				{
					Stop r2 = new Stop();
					r2.Execute();
				}
			}
			catch(NullPointerException e)
			{
				throw new NullPointerException("fatal error: code is null");
			}
		}
	
	}

package components;
import java.io.FileNotFoundException;
import java.util.List;

import operations.BasicStatement;

import basicInterpreter.Parser;
import basicInterpreter.Program;

import exception.LexException;
import exception.ParserException;


public class Interpreter
{

	public static void main(String[] args)
	{
		args = new String[1];
		args[0] = "C:/Users/Cisco/Documents/test1.bas";
		if (args.length == 0)
			System.out.println("java Interpreter file_name expected");
		else
		{
			try
			{
				Parser p = new Parser(args[0]);
				//Expression expr = p.parse();
				p.parseList();
				Program r1 = new Program(p.getStatement_list());
				r1.ExecuteProgram();
			}
			catch (FileNotFoundException e)
			{
				System.out.println("source file not found");
				e.printStackTrace();
			}
			catch (LexException e)
			{
				System.out.println(e.getMessage() + " row: " + e.getRowNumber() + " column: " + e.getColumnNumber());
				e.printStackTrace();
			}			
			catch (ParserException e)
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			catch (IllegalArgumentException e)
			{
				System.out.println (e.getMessage());
				e.printStackTrace();
			}
			
			
			
			catch (Exception e)
			{
				System.out.println("unknown error occurred - terminating");
				e.printStackTrace();
			}
		}
	}

}

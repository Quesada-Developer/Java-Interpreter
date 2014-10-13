package basicInterpreter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


import exception.LexException;
import exception.ParserException;

import operations.Expression;
import operations.Id;
import operations.LiteralInteger;
import operations.BasicStatement;
import operators.ArithmeticOperator;
import operators.RelopOperator;
import operators.TokenType;


public class Parser
{

	private LexicalAnalyzer lex;
	private List<BasicStatement> statement_list ;
	
	public List<BasicStatement> getStatement_list() 
	{
		return statement_list;
	}


	/**
	 * precondition: fileName is not null
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws LexException 
	 * @throws ParserException 
	 * @throws IllegalArgumentException if fileName is null
	 */
	public Parser (String fileName) throws FileNotFoundException, LexException, ParserException
	{
		if (fileName == null)
			throw new IllegalArgumentException ("null string argument");
		lex = new LexicalAnalyzer (fileName);
		//lex.getTokens();
		statement_list = new ArrayList<BasicStatement>();

		for(int i = 0; i <= 999; i++)
			statement_list.add(null);
		
	}
	
	public void parseList() throws ParserException
	{
	
		
		int length = lex.getTokenLength();
		Token tok = lex.getNextToken();
		
		int lineNumber = -1;
		for(int i = 1; i < length; i++)
		{
			if(tok.getTokenType().equals(TokenType.INT_LIT))
					{
						lineNumber = Integer.parseInt(tok.getLexeme());
						tok = lex.getNextToken();
						i++;
						
						if(tok.getTokenType().equals(TokenType.LET_ST))
						{
							Id ch = getId();
							tok = lex.getNextToken();
							i++;
							
							if(tok.getTokenType().equals(TokenType.ASSIGN_OP))
							{								
								Expression expr1 = getExpression();
								i++;
								
								BasicStatement r1 = new BasicStatement(TokenType.LET_ST, ch, expr1);
								statement_list.add(lineNumber,r1);
							}
							else
								throw new ParserException("reserved token expected at line number: " + tok.getLineNumber() + 
										" column number: " + tok.getColumnNumber());
							
						}
						
						else if(tok.getTokenType().equals(TokenType.PRINT_ST))
						{
						Id r1 = getId();
						i++;
						
						BasicStatement r2 = new BasicStatement(TokenType.PRINT_ST, r1);
						statement_list.add(lineNumber, r2);
						
							
						}
						
						else if(tok.getTokenType().equals(TokenType.STOP_ST))
						{
							BasicStatement r1 = new BasicStatement(TokenType.STOP_ST);
							statement_list.add(lineNumber, r1);
						}
						
						else if(tok.getTokenType().equals(TokenType.GOTO_ST))
						{
							tok = lex.getNextToken();
							i++;
							if(tok.getTokenType().equals(TokenType.INT_LIT))
							{
								BasicStatement r1 = new BasicStatement(TokenType.GOTO_ST, Integer.parseInt(tok.getLexeme()));
								statement_list.add(lineNumber, r1);
							}
							
							else
								throw new ParserException("literal integer expected at line number: " + tok.getLineNumber() + 
									" column number: " + tok.getColumnNumber());
							
						}
						
						
						else if(tok.getTokenType().equals(TokenType.IF_ST))
						{							
							RelopOperator rOp = getRelopOperator();
							Expression expr1 = getExpression();
							i++;
							Expression expr2 = getExpression();
							i++;

							tok = lex.getNextToken();
							i++;
							if(tok.getTokenType().equals(TokenType.GOTO_ST))
							{
								tok = lex.getNextToken();
								i++;
								if(tok.getTokenType().equals(TokenType.INT_LIT))
								{

									BasicStatement r2 = new BasicStatement(TokenType.IF_ST, expr1, expr2, rOp, Integer.parseInt(tok.getLexeme()));
									statement_list.add(lineNumber, r2);
								}
								
								else
									throw new ParserException("literal integer expected at line number: " + tok.getLineNumber() + 
										" column number: " + tok.getColumnNumber());
								
							}

							else
								throw new ParserException("reserved word expected at line number: " + tok.getLineNumber() + 
										" column number: " + tok.getColumnNumber());
							
							
							
						}
						else
							throw new ParserException("reserved word expected at line number: " + tok.getLineNumber() + 
									" column number: " + tok.getColumnNumber());
					}
			else
				throw new ParserException("literal integer expected at line number: " + tok.getLineNumber() + 
						" column number: " + tok.getColumnNumber());

			tok = lex.getNextToken();
			i++;
			if(tok.getTokenType().equals(TokenType.EOLN))
			{
				tok = lex.getNextToken();
				i++;
			}
			else
				throw new ParserException("end of line token expected at line number: " + tok.getLineNumber() + 
						" column number: " + tok.getColumnNumber());
		}	
	}


	/**
	 * postcondition: Program object has been created from source code
	 * @return
	 * @throws ParserException if a parse error occurred
	 */
	public Expression parse () throws ParserException
	{
		return getExpression();
	}

	private Expression getExpression() throws ParserException
	{
		Expression expr;
		Token tok = lex.getLookaheadToken();
		if (tok.getTokenType() == TokenType.IDENT)
			expr = getId();
		else if (tok.getTokenType() == TokenType.INT_LIT)
			expr = getLiteralInteger ();
		/*else if (tok.getTokenType() == TokenType.LESS || tok.getTokenType() == TokenType.LESSEQUAL || tok.getTokenType() == TokenType.GREATER || 
			tok.getTokenType() == TokenType.GREATEREQUAL || tok.getTokenType() == TokenType.ASSIGN_OP)
			{
				RelopOperator op = getRelopOperator(tok);
				Expression expr1 = getExpression();
				Expression expr2 = getExpression();
				expr = new Expression (op, expr1, expr2);
				
			}*/
		else
		{
			ArithmeticOperator op = getArithmeticOperator();
			Expression expr1 = getExpression();
			Expression expr2 = getExpression();
			expr = new Expression (op, expr1, expr2);
		}
		return expr;
	}	

	private RelopOperator getRelopOperator() throws ParserException
	{
		Token tok = lex.getNextToken();
		RelopOperator relop;
		if (tok.getTokenType() == TokenType.LESS)
			relop = RelopOperator.LESS;
		else if (tok.getTokenType() == TokenType.LESSEQUAL)
			relop = RelopOperator.LESSEQUAL;
		else if (tok.getTokenType() == TokenType.GREATER)
			relop = RelopOperator.GREATER;
		else if (tok.getTokenType() == TokenType.GREATEREQUAL)
			relop = RelopOperator.GREATEREQUAL;
		else if (tok.getTokenType() == TokenType.EQUAL)
			relop = RelopOperator.EQUAL;
		else if (tok.getTokenType() == TokenType.NOT)
			relop = RelopOperator.NOT;
		else
			throw new ParserException ("relop operator expected at line number: " + tok.getLineNumber() + 
				" column number: " + tok.getColumnNumber());		
		return relop;
	}
	
	private ArithmeticOperator getArithmeticOperator() throws ParserException
	{
		ArithmeticOperator op;
		Token tok = lex.getNextToken();
		if (tok.getTokenType() == TokenType.ADD_OP)
			op = ArithmeticOperator.ADD;
		else if (tok.getTokenType() == TokenType.SUB_OP)
			op = ArithmeticOperator.SUB;
		else if (tok.getTokenType() == TokenType.MULT_OP)
			op = ArithmeticOperator.MUL;
		else if (tok.getTokenType() == TokenType.DIV_OP)
			op = ArithmeticOperator.DIV;
		else
			throw new ParserException ("arithmetic operator expected at line number: " + tok.getLineNumber() + 
				" column number: " + tok.getColumnNumber());
		return op;
	}

	private LiteralInteger getLiteralInteger() throws ParserException
	{
		Token tok = lex.getNextToken();
		int value = 0;
		try
		{
			value = Integer.parseInt(tok.getLexeme());
		}
		catch (NumberFormatException e)
		{
			throw new ParserException ("literal integer expected at line number: " + tok.getLineNumber() + 
				" column number: " + tok.getColumnNumber());
		}
		return new LiteralInteger (value);
	}

	private Id getId() throws ParserException
	{
		Token tok = lex.getNextToken();
		return new Id (tok.getLexeme().charAt(0));
	}
	
}

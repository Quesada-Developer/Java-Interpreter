package operations;

import operators.RelopOperator;
import operators.TokenType;

public class BasicStatement {

	private TokenType tok;
	private Id ch;
	private int lineNumber;
	private Expression exp1;
	private Expression exp2;
	private RelopOperator rOp;
	
	public TokenType returnType()
	{
		return tok;
		
	}
	
	public BasicStatement(TokenType tok, Expression exp1, Expression exp2, RelopOperator rOp, int lineNumber)
	{
		this.tok = tok;
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.rOp = rOp;
		this.lineNumber = lineNumber;
	}
	
	public BasicStatement(TokenType tok, Id ch, Expression exp1)
	{
		this.tok = tok;
		this.ch = ch;
		this.exp1 = exp1;
	}
	
	public BasicStatement(TokenType tok, Id ch)
	{
		this.tok = tok;
		this.ch = ch;
	}

	public BasicStatement(TokenType tok)
	{
		this.tok = tok;
	}

	public BasicStatement(TokenType tok, int lineNumber)
	{
		this.tok = tok;
		this.lineNumber = lineNumber;
	}

	public TokenType getTok() {
		return tok;
	}

	public Id getCh() {
		return ch;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public Expression getExp1() {
		return exp1;
	}

	public Expression getExp2() {
		return exp2;
	}

	public RelopOperator getrOp() {
		return rOp;
	}
}
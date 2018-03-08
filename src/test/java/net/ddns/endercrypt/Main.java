package net.ddns.endercrypt;

import net.ddns.endercrypt.library.expression.MathExpression;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("-- { PARSE } --");
		MathExpression expression = new MathExpression("2 + 5* sqrt(26) - (2^magic)  + ((3 *4) - (5* 6))");
		System.out.println("-- { SOLVE } --");
		expression.setVariable("magic", 5.84);
		System.out.println("Result: " + expression.solve());
	}
}

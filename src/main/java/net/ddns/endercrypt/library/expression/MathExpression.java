package net.ddns.endercrypt.library.expression;

import net.ddns.endercrypt.library.expression.math.extression.MathExpressionObject;

public class MathExpression
{
	private MathExpressionObject expressionCode;

	public MathExpression(String text)
	{
		expressionCode = new MathExpressionObject(text);
	}

	public void setVariable(String name, double value)
	{
		expressionCode.setVariable(name, value);
	}

	public double solve()
	{
		return expressionCode.solve();
	}
}

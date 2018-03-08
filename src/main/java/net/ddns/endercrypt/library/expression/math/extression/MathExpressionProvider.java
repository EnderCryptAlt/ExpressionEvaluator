package net.ddns.endercrypt.library.expression.math.extression;

import java.util.PrimitiveIterator;

import net.ddns.endercrypt.library.expression.MathPriority;
import net.ddns.endercrypt.library.expression.constructor.MathConstructor;
import net.ddns.endercrypt.library.expression.constructor.MathObjectConstructorProvider;
import net.ddns.endercrypt.library.expression.sub.MathObject;

public class MathExpressionProvider extends MathObjectConstructorProvider
{
	@Override
	public MathConstructor makeMathConstructor()
	{
		return new MathConstructor()
		{
			@Override
			protected boolean validate(String text)
			{
				if (text.startsWith("(") == false)
				{
					return false;
				}
				int indent = 0;
				PrimitiveIterator.OfInt iterator = text.chars().iterator();
				while (iterator.hasNext())
				{
					char c = (char) iterator.nextInt();
					if (c == '(')
					{
						indent++;
					}
					if (c == ')')
					{
						indent--;
					}
					if (indent == 0)
					{
						break;
					}
				}
				return ((indent >= 0) && (iterator.hasNext() == false));
			}

			@Override
			protected MathObject internalBuild(String text)
			{
				System.out.println("Expr build: " + text);
				return new MathExpressionObject(text.substring(1, text.length() - 1));
			}

			@Override
			public MathPriority getPriority()
			{
				return MathPriority.EXPRESSION;
			}
		};
	}
}

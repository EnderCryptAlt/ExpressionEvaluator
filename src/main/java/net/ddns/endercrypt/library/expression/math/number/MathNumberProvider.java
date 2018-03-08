package net.ddns.endercrypt.library.expression.math.number;

import net.ddns.endercrypt.library.expression.constructor.MathObjectConstructorProvider;
import net.ddns.endercrypt.library.expression.sub.MathObject;
import net.ddns.endercrypt.library.expression.MathPriority;
import net.ddns.endercrypt.library.expression.constructor.MathConstructor;

public class MathNumberProvider extends MathObjectConstructorProvider
{
	@Override
	public MathConstructor makeMathConstructor()
	{
		return new MathConstructor()
		{
			@Override
			protected boolean validate(String text)
			{
				if (text.contains(" "))
				{
					return false;
				}
				try
				{
					Double.parseDouble(text);
				}
				catch (NumberFormatException e)
				{
					return false;
				}
				return true;
			}

			@Override
			protected MathObject internalBuild(String text)
			{
				return new MathNumberObject(Double.parseDouble(text));
			}

			@Override
			public MathPriority getPriority()
			{
				return MathPriority.NUMBER;
			}
		};
	}
}

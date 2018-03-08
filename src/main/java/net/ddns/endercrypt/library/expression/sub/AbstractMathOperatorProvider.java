package net.ddns.endercrypt.library.expression.sub;

import net.ddns.endercrypt.library.expression.constructor.MathObjectConstructorProvider;
import net.ddns.endercrypt.library.expression.sub.MathObject;
import net.ddns.endercrypt.library.expression.MathPriority;
import net.ddns.endercrypt.library.expression.constructor.MathConstructor;

public abstract class AbstractMathOperatorProvider extends MathObjectConstructorProvider
{
	private char character;
	private MathPriority priority;

	protected AbstractMathOperatorProvider(char character, MathPriority priority)
	{
		this.character = character;
		this.priority = priority;
	}

	@Override
	public MathConstructor makeMathConstructor()
	{
		return new MathConstructor()
		{
			@Override
			protected boolean validate(String text)
			{
				return ((text.length() == 1) && (text.charAt(0) == character));
			}

			@Override
			protected MathObject internalBuild(String text)
			{
				return constructOperatorObject();
			}

			@Override
			public MathPriority getPriority()
			{
				return priority;
			}
		};
	}

	protected abstract MathObject constructOperatorObject();
}

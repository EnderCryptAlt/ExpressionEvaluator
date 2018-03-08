package net.ddns.endercrypt.library.expression.math.var;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import net.ddns.endercrypt.library.expression.MathException;
import net.ddns.endercrypt.library.expression.MathPriority;
import net.ddns.endercrypt.library.expression.constructor.MathConstructor;
import net.ddns.endercrypt.library.expression.constructor.MathObjectConstructorProvider;
import net.ddns.endercrypt.library.expression.sub.MathObject;
import net.ddns.endercrypt.library.expression.sub.SolveableMathObject;

public class MathVariableProvider extends MathObjectConstructorProvider
{

	public MathVariableProvider()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public MathConstructor makeMathConstructor()
	{
		return new MathConstructor()
		{
			@Override
			protected boolean validate(String text)
			{
				return ((text.length() > 0) && (StringUtils.isAlpha(text)));
			}

			@Override
			protected MathObject internalBuild(String text)
			{
				return new MathVariableObject(text);
			}

			@Override
			public MathPriority getPriority()
			{
				return MathPriority.VARIABLE;
			}
		};
	}

	private static class MathVariableObject extends SolveableMathObject
	{
		private String name;
		private Optional<Double> value = Optional.empty();

		public MathVariableObject(String name)
		{
			this.name = name;
		}

		@Override
		public void setVariable(String name, double value)
		{
			if (this.name.equalsIgnoreCase(name))
			{
				this.value = Optional.of(value);
			}
		}

		@Override
		public MathPriority getPriority()
		{
			return MathPriority.VARIABLE;
		}

		@Override
		public double solve()
		{
			return value.orElseThrow(() -> new MathException("Variable " + name + " not set"));
		}

		@Override
		protected Optional<String> info()
		{
			String valueString = value.map(v -> String.valueOf(v)).orElse("Unset");
			return Optional.of("Name=" + name + ",Value=" + valueString);
		}
	}
}

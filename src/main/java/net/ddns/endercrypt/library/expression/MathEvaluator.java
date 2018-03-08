package net.ddns.endercrypt.library.expression;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import net.ddns.endercrypt.library.expression.constructor.MathConstructor;
import net.ddns.endercrypt.library.expression.constructor.MathObjectConstructorProvider;
import net.ddns.endercrypt.library.expression.container.MathOperatorContainer;
import net.ddns.endercrypt.library.expression.iterator.MathIterator;
import net.ddns.endercrypt.library.expression.sub.MathObject;

public class MathEvaluator
{
	private static final String PACKAGE = "net.ddns.endercrypt.library.expression.math";
	private static final Set<MathObjectConstructorProvider> mathObjectConstructors = new HashSet<>();

	static
	{
		Reflections reflection = new Reflections(PACKAGE);
		for (Class<? extends MathObjectConstructorProvider> constructableMathObject : reflection.getSubTypesOf(MathObjectConstructorProvider.class))
		{
			if (constructableMathObject.getName().startsWith(PACKAGE) == false)
			{
				System.out.println("Ignoring: " + constructableMathObject.getName());
				continue;
			}
			try
			{
				System.out.println("Added:  " + constructableMathObject.getName());
				Constructor<? extends MathObjectConstructorProvider> constructor = constructableMathObject.getConstructor();
				MathObjectConstructorProvider instance = constructor.newInstance();
				register(instance);
			}
			catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e)
			{
				System.err.println("Error processing: " + constructableMathObject.getName());
				e.printStackTrace();
			}
		}
	}

	public static void register(MathObjectConstructorProvider constructableMathObject)
	{
		mathObjectConstructors.add(constructableMathObject);
	}

	public static MathOperatorContainer extract(String text)
	{
		MathIterator mathIterator = new MathIterator(text);
		MathOperatorContainer list = new MathOperatorContainer();
		while (mathIterator.canForward())
		{
			MathObject mathObject = extract(mathIterator);
			list.add(mathObject);
		}
		return list;
	}

	private static List<MathConstructor> generateConstructors()
	{
		List<MathConstructor> constructors = new ArrayList<>();
		for (MathObjectConstructorProvider constructableMathObject : mathObjectConstructors)
		{
			MathConstructor mathConstructor = constructableMathObject.makeMathConstructor();
			constructors.add(mathConstructor);
		}
		return constructors;
	}

	public static MathObject extract(MathIterator mathIterator)
	{
		List<MathConstructor> deletedConstructors = new ArrayList<>();
		List<MathConstructor> constructors = generateConstructors();
		mathIterator.syncIndexes();
		mathIterator.autoTrim();
		// int previousRemaining = mathIterator.length() - mathIterator.getEndIndex();
		deletedConstructors.clear();
		String text = "";
		while (true)
		{
			// if can move forwards
			if (mathIterator.canForward())
			{
				deletedConstructors.clear();
				mathIterator.forward();
				text = mathIterator.extract();
				System.out.println("Text: " + text);
				Iterator<MathConstructor> iterator = constructors.iterator();

				// check each iterator
				while (iterator.hasNext())
				{
					MathConstructor instance = iterator.next();
					if (instance.setText(text) == false)
					{
						deletedConstructors.add(instance);
						iterator.remove();
					}
				}
			}

			System.out.println("constructors: " + constructors);

			// check deleted
			if ((constructors.size() == 0) || (mathIterator.canForward() == false))
			{
				if (mathIterator.canForward())
				{
					mathIterator.back();
				}
				else
				{
					System.out.println("text: \"" + text + "\" length: " + mathIterator.length() + " start: " + mathIterator.getStartIndex() + " end: " + mathIterator.getEndIndex());
					//if ((mathIterator.getStartIndex() < mathIterator.length() - 1) && (mathIterator.getEndIndex() == mathIterator.length() - 1))
					if ((constructors.size() == 0) && (mathIterator.getStartIndex() < mathIterator.length() - 1))
					{
						mathIterator.back();
					}
				}
				MathConstructor targetMathContructor = null;
				if (constructors.size() > 0)
				{
					Collections.sort(constructors);
					targetMathContructor = constructors.get(constructors.size() - 1);
					System.out.println("Last alive: " + targetMathContructor);
				}
				else
				{
					deletedConstructors.addAll(constructors);
					Collections.sort(deletedConstructors);
					targetMathContructor = deletedConstructors.get(deletedConstructors.size() - 1);
					System.out.println("Last dead: " + targetMathContructor);
				}
				System.out.println("Construct: " + targetMathContructor);
				return targetMathContructor.build();
			}
		}
	}
}

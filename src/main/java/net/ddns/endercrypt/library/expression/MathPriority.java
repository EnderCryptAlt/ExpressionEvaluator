package net.ddns.endercrypt.library.expression;

public enum MathPriority
{
	//@formatter:off
	BASIC_MATH,   // + -
	POWER,        // ^
	ADVANCE_MATH, // * /
	EXPRESSION,   // ( )
	VARIABLE,     // any_text
	FUNCTION,     // sqrt
	NUMBER;       // number
	//@formatter:on
}

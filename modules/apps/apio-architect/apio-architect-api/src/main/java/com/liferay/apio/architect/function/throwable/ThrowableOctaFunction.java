/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.apio.architect.function.throwable;

import aQute.bnd.annotation.ConsumerType;

import java.util.Objects;

/**
 * Defines a {@code OctaFunction} that can throw an exception.
 *
 * @author Alejandro Hernández
 * @param  <A> the type of the first argument of the function
 * @param  <B> the type of the second argument of the function
 * @param  <C> the type of the third argument of the function
 * @param  <D> the type of the fourth argument of the function
 * @param  <E> the type of the fifth argument of the function
 * @param  <F> the type of the sixth argument of the function
 * @param  <G> the type of the seventh argument of the function
 * @param  <H> the type of the eighth argument of the function
 * @param  <R> the type of the result of the function
 */
@ConsumerType
@FunctionalInterface
public interface ThrowableOctaFunction<A, B, C, D, E, F, G, H, R> {

	/**
	 * Returns the {@code ThrowableOctaFunction} that first executes the current
	 * {@code ThrowableOctaFunction} instance's {@code apply} method, then uses
	 * the result as input for the {@code afterFunction} parameter's {@code
	 * apply} method.
	 *
	 * @param  throwableFunction the {@code ThrowableOctaFunction} to execute
	 *         after the current instance
	 * @return the {@code ThrowableOctaFunction} that executes the current
	 *         instance's {@code apply} method, then uses the result as input
	 *         for the {@code throwableFunction} parameter's {@code apply}
	 *         method
	 */
	public default <V> ThrowableOctaFunction<A, B, C, D, E, F, G, H, V> andThen(
		ThrowableFunction<? super R, ? extends V> throwableFunction) {

		Objects.requireNonNull(throwableFunction);

		return (A a, B b, C c, D d, E e, F f, G g, H h) ->
			throwableFunction.apply(apply(a, b, c, d, e, f, g, h));
	}

	/**
	 * Applies the current {@code ThrowableOctaFunction} and returns a value of
	 * type {@code R}. This function can be implemented explicitly or with a
	 * lambda.
	 *
	 * @param  a the function's first argument
	 * @param  b the function's second argument
	 * @param  c the function's third argument
	 * @param  d the function's fourth argument
	 * @param  e the function's fifth argument
	 * @param  f the function's sixth argument
	 * @param  g the function's seventh argument
	 * @param  h the function's eighth argument
	 * @return the function's result, as a value of type {@code R}
	 */
	public R apply(A a, B b, C c, D d, E e, F f, G g, H h) throws Exception;

}
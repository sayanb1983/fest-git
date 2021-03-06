/*
 * Created on Jul 1, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Collections.*;
import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.util.Collections.duplicatesFrom;
import static org.fest.util.Strings.concat;

import java.util.*;

/**
 * Understands a template for assertion methods related to groups of items (e.g. collections or arrays.)
 * @param <T> the type of object implementations of this template can verify.
 *
 * @author Yvonne Wang
 *
 * @since 1.3
 */
public abstract class ItemGroupAssert<T> extends GroupAssert<T> {

  /**
   * Creates a new </code>{@link ItemGroupAssert}</code>.
   * @param actual
   */
  public ItemGroupAssert(T actual) {
    super(actual);
  }

  /**
   * Verifies that the actual actual group of objects contains the given objects, in any order.
   * @param objects the objects to look for.
   * @throws AssertionError if the actual actual group of objects is <code>null</code>.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws AssertionError if the actual actual group of objects does not contain the given objects.
   */
  protected final void assertContains(Object... objects) {
    isNotNull();
    validateIsNotNull(objects);
    Collection<Object> notFound = notFoundInActual(objects);
    if (notFound.isEmpty()) return;
    throw failureIfExpectedElementsNotFound(notFound);
  }

  private Collection<Object> notFoundInActual(Object... objects) {
    return notFound(actualAsSet(), objects);
  }

  /**
   * Verifies that the actual group of objects contains the given objects <strong>only</strong>, in any order.
   * @param objects the objects to look for.
   * @throws AssertionError if the actual group of objects is <code>null</code>.
   * @throws NullPointerException if the given group of objects is <code>null</code>.
   * @throws AssertionError if the actual group of objects does not contain the given objects, or if the actual
   * group of objects contains elements other than the ones specified.
   */
  protected final void assertContainsOnly(Object... objects) {
    isNotNull();
    validateIsNotNull(objects);
    Set<Object> copy = actualAsSet();
    List<Object> notFound = notFoundInCopy(copy, asSet(objects));
    if (!notFound.isEmpty()) throw failureIfExpectedElementsNotFound(notFound);
    if (copy.isEmpty()) return;
    throw failureIfUnexpectedElementsFound(copy);
  }

  /**
   * Returns the actual value as a {@code Set}.
   * @return the actual value as a {@code Set}.
   */
  protected abstract Set<Object> actualAsSet();

  /**
   * Returns a set containing all the elements in the given array.
   * @param objects the given array.
   * @return a set containing all the elements in the given array.
   */
  protected static Set<Object> asSet(Object[] objects) {
    Set<Object> s = new LinkedHashSet<Object>();
    for (Object o : objects) s.add(o);
    return s;
  }

  private List<Object> notFoundInCopy(Set<Object> copy, Set<Object> objects) {
    List<Object> notFound = new ArrayList<Object>();
    for (Object o : objects) {
      if (!copy.contains(o)) {
        notFound.add(o);
        continue;
      }
      copy.remove(o);
    }
    return notFound;
  }

  private AssertionError failureIfExpectedElementsNotFound(Collection<Object> notFound) {
    failIfCustomMessageIsSet();
    return failure(concat(inBrackets(actual), " does not contain element(s):", inBrackets(notFound)));
  }

  private AssertionError failureIfUnexpectedElementsFound(Collection<Object> unexpected) {
    failIfCustomMessageIsSet();
    return failure(concat("unexpected element(s):", inBrackets(unexpected), " in ", inBrackets(actual)));
  }

  /**
   * Verifies that the actual group of objects does not contain the given objects.
   * @param objects the objects that the group of objects should exclude.
   * @throws AssertionError if the actual group of objects is <code>null</code>.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws AssertionError if the actual group of objects contains any of the given objects.
   */
  protected final void assertExcludes(Object... objects) {
    isNotNull();
    validateIsNotNull(objects);
    Collection<Object> found = found(actualAsSet(), objects);
    if (found.isEmpty()) return;
    failIfCustomMessageIsSet();
    throw failure(concat(inBrackets(actual), " does not exclude element(s):", inBrackets(found)));
  }

  /**
   * Validates that the given array of objects is not <code>null</code>.
   * @param objects the array of objects to verify.
   * @throws NullPointerException if the given array of objects is <code>null</code>.
   */
  protected final void validateIsNotNull(Object[] objects) {
    if (objects == null)
      throw new NullPointerException(formattedErrorMessage("The given array should not be null"));
  }

  /**
   * Verifies that the actual group of objects does not have duplicates.
   * @throws AssertionError if the actual group of objects is <code>null</code>.
   * @throws AssertionError if the actual group of objects has duplicates.
   */
  protected final void assertDoesNotHaveDuplicates() {
    isNotNull();
    Collection<?> duplicates = duplicatesFrom(actualAsList());
    if (duplicates.isEmpty()) return;
    failIfCustomMessageIsSet();
    throw failure(concat(inBrackets(actual), " contains duplicate(s):", inBrackets(duplicates)));
  }

  /**
   * Returns the actual value as a {@code List}.
   * @return the actual value as a {@code List}.
   */
  protected abstract List<Object> actualAsList();

}
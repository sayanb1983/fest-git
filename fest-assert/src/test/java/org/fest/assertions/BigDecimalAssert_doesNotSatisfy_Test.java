/*
 * Created on Jan 10, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.assertions;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import org.junit.BeforeClass;

/**
 * Tests for <code>{@link BigDecimalAssert#doesNotSatisfy(Condition)}</code>.
 *
 * @author David DIDIER
 * @author Ted M. Young
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BigDecimalAssert_doesNotSatisfy_Test extends GenericAssert_doesNotSatisfy_TestCase<BigDecimal> {

  private static BigDecimal notNullValue;

  @BeforeClass
  public static void setUpOnce() {
    notNullValue = ZERO;
  }

  @Override protected BigDecimal notNullValue() {
    return notNullValue;
  }

  @Override protected BigDecimalAssert assertionsFor(BigDecimal actual) {
    return new BigDecimalAssert(actual);
  }
}

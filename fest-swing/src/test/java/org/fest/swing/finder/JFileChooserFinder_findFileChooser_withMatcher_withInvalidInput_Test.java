/*
 * Created on Aug 27, 2009
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.finder;

import javax.swing.JFileChooser;

import org.fest.swing.core.GenericTypeMatcher;
import org.junit.Test;

/**
 * Tests for <code>{@link JFileChooserFinder#findFileChooser(org.fest.swing.core.GenericTypeMatcher)}</code>.
 *
 * @author Alex Ruiz
 */
public class JFileChooserFinder_findFileChooser_withMatcher_withInvalidInput_Test {

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_matcher_is_null() {
    GenericTypeMatcher<JFileChooser> matcher = null;
    JFileChooserFinder.findFileChooser(matcher);
  }

  @Test(expected = IllegalArgumentException.class)
  public void should_throw_error_if_timeout_is_negative() {
    JFileChooserFinder.findFileChooser(new MyMatcher()).withTimeout(-20);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_time_unit_is_null() {
    JFileChooserFinder.findFileChooser(new MyMatcher()).withTimeout(10, null);
  }

  private static class MyMatcher extends GenericTypeMatcher<JFileChooser> {
    MyMatcher() {
      super(JFileChooser.class);
    }

    @Override protected boolean isMatching(JFileChooser fileChooser) {
      return false;
    }
  }
}

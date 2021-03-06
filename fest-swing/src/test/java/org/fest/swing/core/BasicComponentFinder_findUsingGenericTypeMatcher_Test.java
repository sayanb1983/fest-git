/*
 * Created on Jul 24, 2009
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
package org.fest.swing.core;

import static org.fest.assertions.Assertions.assertThat;

import javax.swing.JButton;

import org.fest.swing.exception.ComponentLookupException;
import org.junit.Test;

/**
 * Tests for <code>{@link BasicComponentFinder#find(GenericTypeMatcher)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class BasicComponentFinder_findUsingGenericTypeMatcher_Test extends BasicComponentFinder_TestCase {

  @Test
  public void should_find_Component() {
    JButton foundButton = finder.find(new GenericTypeMatcher<JButton>(JButton.class) {
      @Override protected boolean isMatching(JButton button) {
        return "A Button".equals(button.getText());
      }
    });
    assertThat(foundButton).isSameAs(window.button);
  }

  @Test(expected = ComponentLookupException.class)
  public void should_throw_error_if_GenericTypeMatcher_never_matches_Component() {
    finder.find(new GenericTypeMatcher<JButton>(JButton.class) {
      @Override protected boolean isMatching(JButton component) {
        return false;
      }
    });
  }
}

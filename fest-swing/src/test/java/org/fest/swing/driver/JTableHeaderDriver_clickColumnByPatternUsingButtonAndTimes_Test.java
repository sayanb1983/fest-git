/*
 * Created on Mar 16, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.test.core.Regex.regex;
import static org.fest.swing.test.recorder.ClickRecorder.attachTo;

import org.fest.swing.test.recorder.ClickRecorder;
import org.junit.Test;

/**
 * Tests for <code>{@link JTableHeaderDriver#clickColumn(javax.swing.table.JTableHeader, String)}</code>.
 *
 * @author Yvonne Wang
 */
public class JTableHeaderDriver_clickColumnByPatternUsingButtonAndTimes_Test extends JTableHeaderDriver_TestCase {

  @Test
  public void should_click_column() {
    showWindow();
    ClickRecorder recorder = attachTo(tableHeader);
    driver.clickColumn(tableHeader, regex("0.*"), LEFT_BUTTON, 3);
    assertThat(recorder).wasClickedWith(LEFT_BUTTON).timesClicked(3);
    assertThatColumnWasClicked(recorder, 0);
  }

}

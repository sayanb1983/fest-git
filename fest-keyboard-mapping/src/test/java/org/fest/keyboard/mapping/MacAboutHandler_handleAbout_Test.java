/*
 * Created on May 1, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.keyboard.mapping;

import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;
import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.*;

/**
 * Tests for <code>{@link MacAboutHandler#handleAbout()}</code>.
 *
 * @author Alex Ruiz
 */
public class MacAboutHandler_handleAbout_Test extends EDTSafeTestCase {

  private static MainFrame frame;
  private MacAboutHandler handler;

  @BeforeClass
  public static void setUpOnce() {
    frame = createMock(MainFrame.class);
  }

  @Before
  public void setUp() {
    handler = new MacAboutHandler(frame);
  }

  @Test
  public void should_show_About_dialog() {
    new EasyMockTemplate(frame) {
      @Override protected void expectations() {
        frame.showAboutWindow();
        expectLastCall();
      }

      @Override protected void codeToTest() {
        handler.handleAbout();
      }
    }.run();
  }
}

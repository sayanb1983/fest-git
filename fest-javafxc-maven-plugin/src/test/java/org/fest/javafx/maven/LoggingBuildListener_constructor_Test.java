/*
 * Created on Jan 23, 2010
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
package org.fest.javafx.maven;

import static org.fest.assertions.Assertions.assertThat;

import org.apache.maven.plugin.logging.Log;
import org.junit.Test;

/**
 * Test case for <code>{@link LoggingBuildListener#LoggingBuildListener(Log)}</code>.
 *
 * @author Alex Ruiz
 */
public class LoggingBuildListener_constructor_Test {

  @Test
  public void should_create_listener_to_use_Maven_Log() {
    Log logger = new LogStub();
    LoggingBuildListener listener = new LoggingBuildListener(logger);
    assertThat(listener.logger()).isSameAs(logger);
  }
}

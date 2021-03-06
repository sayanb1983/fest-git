/*
 * Created on Sep 29, 2006
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
 * Copyright @2006-2010 the original author or authors.
 */
package org.fest.swing.exception;

import java.awt.Component;
import java.util.*;

/**
 * Understands an error thrown when looking up a component using a <code>{@link org.fest.swing.core.ComponentFinder}</code>.
 *
 * @author Alex Ruiz
 */
public class ComponentLookupException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final Collection<Component> found = new ArrayList<Component>();

  /**
   * Creates a new </code>{@link ComponentLookupException}</code>.
   * @param message the detail message.
   * @param found the {@code Component}s found by the lookup (if any.)
   */
  public ComponentLookupException(String message, Collection<? extends Component> found) {
    this(message);
    this.found.addAll(found);
  }

  /**
   * Creates a new <code>{@link ComponentLookupException}</code>.
   * @param message the detail message.
   */
  public ComponentLookupException(String message) {
    super(message);
  }

  /**
   * Returns the {@code Component}s found by the lookup (if any.)
   * @return the {@code Component}s found by the lookup (if any.)
   */
  public final Collection<? extends Component> found() {
    return Collections.<Component>unmodifiableCollection(found);
  }
}

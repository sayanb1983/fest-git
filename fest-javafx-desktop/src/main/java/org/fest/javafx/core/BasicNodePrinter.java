/*
 * Created on May 24, 2010
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
package org.fest.javafx.core;

import static org.fest.javafx.core.SingleSceneNodeHierarchy.hierarchyFor;
import static org.fest.javafx.format.Formatting.format;
import static org.fest.javafx.threading.GuiActionRunner.execute;

import java.io.PrintStream;

import javafx.scene.Node;
import javafx.scene.Scene;

import org.fest.javafx.annotations.RunsInCurrentThread;
import org.fest.javafx.annotations.RunsInUIThread;
import org.fest.javafx.threading.GuiTask;
import org.fest.util.VisibleForTesting;

/**
 * Understands the default implementation of <code>{@link NodePrinter}</code>.
 *
 * @author Alex Ruiz
 */
public class BasicNodePrinter implements NodePrinter {

  private static final String INDENTATION = "  ";
  private static final NodeMatcher ALWAYS_MATCHES = new AlwaysMatches();

  /** {@inheritDoc} */
  @RunsInUIThread
  @Override public void printNodes(PrintStream out, NodeHierarchy hierarchy) {
    printNodes(out, ALWAYS_MATCHES, hierarchy);
  }

  /** {@inheritDoc} */
  @RunsInUIThread
  @Override public void printNodes(PrintStream out, Scene root) {
    printNodes(out, ALWAYS_MATCHES, root);
  }

  /** {@inheritDoc} */
  @RunsInUIThread
  @Override public void printNodes(PrintStream out, NodeMatcher matcher, Scene root) {
    printNodes(out, matcher, hierarchyFor(root));
  }

  /** {@inheritDoc} */
  @RunsInUIThread
  @Override public void printNodes(PrintStream out, NodeMatcher matcher, NodeHierarchy hierarchy) {
    validateNotNull(out);
    validateNotNull(matcher);
    print(hierarchy, matcher, out);
  }

  private void validateNotNull(PrintStream out) {
    if (out == null) throw new NullPointerException("The output stream should not be null");
  }

  private void validateNotNull(NodeMatcher matcher) {
    if (matcher == null) throw new NullPointerException("The matcher to use as filter should not be null");
  }

  @VisibleForTesting
  @RunsInUIThread
  void print(final NodeHierarchy hierarchy, final NodeMatcher matcher, final PrintStream out) {
    execute(new GuiTask() {
      @Override protected void executeInUIThread() {
        for (Node n : hierarchy.contents())
          print(n, hierarchy, matcher, 0, out);
      }
    });
  }

  @RunsInCurrentThread
  private static void print(Node n, NodeHierarchy h, NodeMatcher matcher, int level, PrintStream out) {
    if (matcher.matches(n)) print(n, level, out);
    for (Node child : h.childrenOf(n))
      print(child, h, matcher, level + 1, out);
  }

  @RunsInCurrentThread
  private static void print(Node n, int level, PrintStream out) {
    for (int i = 0; i < level; i++) out.print(INDENTATION);
    out.println(format(n));
  }

  @VisibleForTesting
  static class AlwaysMatches implements NodeMatcher {
    @Override public boolean matches(Node node) {
      return true;
    }

    @Override public void reset(boolean matchFound) {}
  }
}

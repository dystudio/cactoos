/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2020 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.text;

import org.junit.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.ScalarHasValue;

/**
 * Test case for {@link Contains}.
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class ContainsTest {

    @Test
    public void textContainsText() {
        new Assertion<>(
            "Text contains other Text",
            new Contains(
                new TextOf("Elegant Object"),
                new TextOf("Elegant")
            ),
            new ScalarHasValue<>(Boolean.TRUE)
        ).affirm();
    }

    @Test
    public void textDoesNotContainText() {
        new Assertion<>(
            "Text does not contain other Text",
            new Contains(
                new TextOf("Java is awesome"),
                new TextOf("good")
            ),
            new ScalarHasValue<>(Boolean.FALSE)
        ).affirm();
    }

    @Test
    public void textContainsString() {
        new Assertion<>(
            "Text contains other String",
            new Contains(
                new TextOf("The quick brown fox"),
                "fox"
            ),
            new ScalarHasValue<>(Boolean.TRUE)
        ).affirm();
    }

    @Test
    public void textDoesNotContainString() {
        new Assertion<>(
            "Text does not contain other String",
            new Contains(
                new TextOf("Stack Overflow"),
                "nope"
            ),
            new ScalarHasValue<>(Boolean.FALSE)
        ).affirm();
    }

    @Test
    public void stringContainsText() {
        new Assertion<>(
            "String contains other Text",
            new Contains(
                "Terra incognita",
                new TextOf("cognita")
            ),
            new ScalarHasValue<>(Boolean.TRUE)
        ).affirm();
    }

    @Test
    public void stringDoesNotContainText() {
        new Assertion<>(
            "String does not contain other Text",
            new Contains(
                "ratio",
                new TextOf("Cogito egro sum")
            ),
            new ScalarHasValue<>(Boolean.FALSE)
        ).affirm();
    }

    @Test
    public void stringContainsString() {
        new Assertion<>(
            "String contains other String",
            new Contains(
                "Lazy dog",
                "dog"
            ),
            new ScalarHasValue<>(Boolean.TRUE)
        ).affirm();
    }

    @Test
    public void stringDoesNotContainString() {
        new Assertion<>(
            "String does not contain other String",
            new Contains(
                "Lorem ipsum",
                "test"
            ),
            new ScalarHasValue<>(Boolean.FALSE)
        ).affirm();
    }
}
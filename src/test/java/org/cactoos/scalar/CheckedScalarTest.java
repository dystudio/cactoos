/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2018 Yegor Bugayenko
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
package org.cactoos.scalar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link CheckedScalar}.
 *
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.30
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class CheckedScalarTest {

    @Test(expected = IOException.class)
    public void throwsException() throws Exception {
        new CheckedScalar<>(
            () -> {
                throw new InterruptedException("interrupt");
            },
            IOException::new
        ).value();
    }

    @Test(expected = IllegalStateException.class)
    public void throwsRuntimeException() {
        new CheckedScalar<>(
            () -> {
                throw new UncheckedIOException(new IOException("io"));
            },
            IllegalStateException::new
        ).value();
    }

    @Test(expected = FileNotFoundException.class)
    public void exceptionGoesOut() throws Exception {
        new CheckedScalar<>(
            () -> {
                throw new FileNotFoundException("file");
            },
            IOException::new
        ).value();
    }

    @Test
    public void runtimeExceptionGoesOut() {
        try {
            new CheckedScalar<>(
                () -> {
                    throw new IllegalStateException("illegal");
                },
                IllegalStateException::new
            ).value();
        } catch (final IllegalStateException exp) {
            MatcherAssert.assertThat(
                exp.getCause(),
                Matchers.nullValue()
            );
        }
    }
}

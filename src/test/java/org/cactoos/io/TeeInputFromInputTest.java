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
package org.cactoos.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.cactoos.text.TextOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Test case for {@link TeeInput}. Cases for ctors which use
 * {@link org.cactoos.Input} as an input.
 * @author Roman Proshin (roman@proshin.org)
 * @version $Id$
 * @since 1.0
 * @checkstyle JavadocMethodCheck (200 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 */
public final class TeeInputFromInputTest {

    /**
     * Temporary files generator.
     */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void copiesFromInputToPath() throws IOException {
        final String message =
            "Hello, товарищ path #1 äÄ üÜ öÖ and ß";
        final File output = this.folder.newFile();
        MatcherAssert.assertThat(
            new TextOf(
                new TeeInput(
                    new InputOf(message),
                    output.toPath()
                )
            ).asString(),
            new IsEqual<>(message)
        );
        MatcherAssert.assertThat(
            new TextOf(output).asString(),
            new IsEqual<>(message)
        );
    }

    @Test
    public void copiesFromInputToFile() throws IOException {
        final String message =
            "Hello, товарищ file #1 äÄ üÜ öÖ and ß";
        final File output = this.folder.newFile();
        MatcherAssert.assertThat(
            new TextOf(
                new TeeInput(
                    new InputOf(message),
                    output
                )
            ).asString(),
            new IsEqual<>(message)
        );
        MatcherAssert.assertThat(
            new TextOf(output).asString(),
            new IsEqual<>(message)
        );
    }

    @Test
    public void copiesFromInputToWriter() throws IOException {
        final String message =
            "Hello, товарищ write #1 äÄ üÜ öÖ and ß";
        final File output = this.folder.newFile();
        MatcherAssert.assertThat(
            new TextOf(
                new TeeInput(
                    new InputOf(message),
                    new WriterTo(output)
                )
            ).asString(),
            new IsEqual<>(message)
        );
        MatcherAssert.assertThat(
            new TextOf(output).asString(),
            new IsEqual<>(message)
        );
    }

    @Test
    public void copiesFromInputWithSizeToWriter() throws IOException {
        final String message =
            "Hello, товарищ writer #2 äÄ üÜ öÖ and ß";
        final File output = this.folder.newFile();
        MatcherAssert.assertThat(
            new TextOf(
                new TeeInput(
                    new InputOf(message),
                    new WriterTo(output),
                    message.length()
                )
            ).asString(),
            new IsEqual<>(message)
        );
        MatcherAssert.assertThat(
            new TextOf(output).asString(),
            new IsEqual<>(message)
        );
    }

    @Test
    public void copiesFromInputWithCharsetToWriter() throws IOException {
        final String message =
            "Hello, товарищ writer #3 äÄ üÜ öÖ and ß";
        final File output = this.folder.newFile();
        MatcherAssert.assertThat(
            new TextOf(
                new TeeInput(
                    new InputOf(message),
                    new WriterTo(output),
                    StandardCharsets.UTF_8
                )
            ).asString(),
            new IsEqual<>(message)
        );
        MatcherAssert.assertThat(
            new TextOf(output).asString(),
            new IsEqual<>(message)
        );
    }

    @Test
    public void copiesFromInputWithCharsetAndSizeToWriter() throws IOException {
        final String message =
            "Hello, товарищ writer #4 äÄ üÜ öÖ and ß";
        final File output = this.folder.newFile();
        MatcherAssert.assertThat(
            new TextOf(
                new TeeInput(
                    new InputOf(message),
                    new WriterTo(output),
                    StandardCharsets.UTF_8,
                    message.length()
                )
            ).asString(),
            new IsEqual<>(message)
        );
        MatcherAssert.assertThat(
            new TextOf(output).asString(),
            new IsEqual<>(message)
        );
    }

    @Test
    public void copiesFromInputWithCharsetByNameToWriter() throws IOException {
        final String message =
            "Hello, товарищ writer #5 äÄ üÜ öÖ and ß";
        final File output = this.folder.newFile();
        MatcherAssert.assertThat(
            new TextOf(
                new TeeInput(
                    new InputOf(message),
                    new WriterTo(output),
                    StandardCharsets.UTF_8.name()
                )
            ).asString(),
            new IsEqual<>(message)
        );
        MatcherAssert.assertThat(
            new TextOf(output).asString(),
            new IsEqual<>(message)
        );
    }

    @Test
    public void copiesFromInputWithCharsetByNameAndSizeToWriter()
        throws Exception {
        final String message =
            "Hello, товарищ writer #6 äÄ üÜ öÖ and ß";
        final File output = this.folder.newFile();
        MatcherAssert.assertThat(
            new TextOf(
                new TeeInput(
                    new InputOf(message),
                    new WriterTo(output),
                    StandardCharsets.UTF_8.name(),
                    message.length()
                )
            ).asString(),
            new IsEqual<>(message)
        );
        MatcherAssert.assertThat(
            new TextOf(output).asString(),
            new IsEqual<>(message)
        );
    }
}

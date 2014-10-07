/*
 * Copyright (c) 2014, nwillc@gmail.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or
 * without fee is hereby granted, provided that the above copyright notice and this permission
 * notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO
 * THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT
 * SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR
 * ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF
 * CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE
 * OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.github.nwillc.contracts;


import org.junit.Test;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

public abstract class IteratorContract {
    private static final Logger LOGGER = Logger.getLogger(ImmutableIteratorContract.class.getName());

    protected abstract Iterator getNonEmptyIterator();

    @Test
    public void hasNextShouldNotAdvanceIterator() throws Exception {
        int count = count(getNonEmptyIterator());
        assertThat(count).as("Iterator should be non empty").isGreaterThan(0);

        Iterator iterator = getNonEmptyIterator();

        while (count > 0) {
            count--;
            iterator.hasNext();
        }

        assertThat(iterator.hasNext()).as("hasNest should not advance iterator").isTrue();
    }

    @Test
    public void nextShouldAdvanceIterator() throws Exception {
        int count = count(getNonEmptyIterator());
        assertThat(count).as("Iterator should be non empty").isGreaterThan(0);

        Iterator iterator = getNonEmptyIterator();

        while (count > 0) {
            count--;
            iterator.next();
        }

        assertThat(iterator.hasNext()).as("next should not advance iterator").isFalse();
    }

    @Test
    public void hasNextPastEnd() throws Exception {
        Iterator iterator = getNonEmptyIterator();

        while (iterator.hasNext()) {
            iterator.next();
        }

        assertThat(iterator.hasNext()).as("hasNext past end of iterator").isFalse();
    }

    @Test
    public void shouldNotPassEndOfIterator() throws Exception {
        Iterator anIterator = getNonEmptyIterator();
        assertThat(anIterator).isNotNull();
        while (anIterator.hasNext()) {
            anIterator.next();
        }
        try {
            anIterator.next();
            failBecauseExceptionWasNotThrown(NoSuchElementException.class);
        } catch (NoSuchElementException e) {
            LOGGER.fine("Caught expected " + e);
        }
    }

    private int count(Iterator iterator) {
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        return count;
    }
}

/*
 * Copyright (c) 2016, nwillc@gmail.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted,
 * provided that the above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF
 * THIS SOFTWARE.
 *
 */

package com.github.nwillc.contracts;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;

import java.util.Comparator;

/**
 * @since 1.6.6
 */
public class ComparatorContractNullsLastTest extends ComparatorContractTest {

    @Before
    @BeforeEach
    public void setUp() throws Exception {
        setNulls(Nulls.NULLS_LAST);
    }

    @Override
    protected Comparator<Long> getComparator() {
        return new NullLastLongComparator();
    }

    @Override
    protected Long getValue() {
        return null;
    }

    public static class NullLastLongComparator implements Comparator<Long> {
        @Override
        public int compare(Long t1, Long t2) {
            if (t1 == null ^ t2 == null) {
                return (t2 == null) ? -1 : 1;
            }

            if (t1 == null) {
                return 0;
            }

            return (int)(t1 - t2);
        }
    }
}
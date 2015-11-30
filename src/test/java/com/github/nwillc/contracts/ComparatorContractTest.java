/*
 * Copyright (c) 2015, nwillc@gmail.com
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

import org.assertj.core.util.Preconditions;

import java.util.Comparator;

public class ComparatorContractTest extends ComparatorContract<Long> {

    @Override
    protected Comparator<Long> getComparator() {
        return new LongComparator();
    }

    @Override
    protected Long getValue() {
        return 1L;
    }

    @Override
    protected Long getLesserValue() {
        return 0L;
    }

    public static class LongComparator implements Comparator<Long> {
        @Override
        public int compare(Long t1, Long t2) {
            Preconditions.checkNotNull(t1);
            Preconditions.checkNotNull(t2);
            return (int)(t1 - t2);
        }
    }
}
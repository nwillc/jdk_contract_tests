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

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class EqualsContractTest extends EqualsContract<EqualsContractTest.Sample> {

    @Override
    protected List<Sample> getEquals() {
        List<Sample> samples = new ArrayList<Sample>();
        samples.add(new Sample(1, "foo"));
        samples.add(new Sample(1, "foo"));
        return samples;
    }

    @Override
    protected List<Sample> getNotEquals() {
        List<Sample> samples = new ArrayList<Sample>();
        samples.add(new Sample(1, "foo"));
        samples.add(new SubSample(1, "foo"));
        samples.add(new Sample(1, "bar"));
        samples.add(new Sample(2, "foo"));
        return samples;
    }

    static class Sample {
		private final String label;
		private final int count;

		public Sample(int count, String label) {
			this.count = count;
			this.label = label;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Sample sample = (Sample) o;

			if (count != sample.count) return false;
			return label.equals(sample.label);

		}

		@Override
		public int hashCode() {
			int result = label.hashCode();
			result = 31 * result + count;
            result = 31 * result + getClass().getSimpleName().hashCode();
			return result;
		}
	}

    static class SubSample extends Sample {
        public SubSample(int count, String label) {
            super(count, label);
        }
    }
}
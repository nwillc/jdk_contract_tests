package com.github.nwillc.contracts;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This contract checks:
 * <ul>
 *     <li>That all the members of the Equals list are equal to one another</li>
 *     <li>That all the members of the NotEquals list are not equal to the others</li>
 *     <li>That hashCodes are also consistent as above.</li>
 *     <li>That null is safely viewed as not equals</li>
 * </ul>
 * Depending on how your equals handles subclasses you should include appropriate examples in your lists.
 *
 * <P><b>NB:</b> While hashcode equivalence is supposed to parallel equals results, there is one exception. While equals
 * often considers the instance's classes, hashcode rarely does. So while a subclass with the same values may
 * fail to be "equal" it may still have the same hashcode. This contract therefore only compares hashcodes of the same class.</P>
 * @since 1.6.8
 * @param <T> type to test on
 */
public abstract class EqualsContract<T> {

    /**
     * List of type T which are all equals based on your implementation of equals. If your
     * equals allows subclasses, include an example.
     * @return List of instances
     */
	protected abstract List<T> getEquals();

    /**
     * List of type T which are not equal based on your implementation of equals. If your
     * equals disallows subclasses, include an subclass.
     * @return List of instances
     */
	protected abstract List<T> getNotEquals();

	@Test
    public void testEquals() throws Exception {
        List<T> instances = getEquals();
        assertThat(instances).isNotEmpty();

        for (T t1 : instances) {
            for (T t2 : instances) {
                assertThat(t1).isEqualTo(t2);
            }
        }
    }

    @Test
    public void testNullValue() throws Exception {
        List<T> instances = getEquals();
        assertThat(instances).isNotEmpty();

        assertThat(instances.get(0).equals(null)).isFalse();
    }

    @Test
    public void testNotEquals() throws Exception {
        List<T> instances = getNotEquals();
        assertThat(instances).isNotEmpty();

        for (T t1 : instances) {
            for (T t2 : instances) {
                if (t1 != t2) {
                    assertThat(t1).isNotEqualTo(t2);
                }
            }
        }
    }

    @Test
    public void testEqualHashes() throws Exception {
        List<T> instances = getEquals();
        assertThat(instances).isNotEmpty();

        for (T t1 : instances) {
            for (T t2 : instances) {
                assertThat(t1.hashCode()).isEqualTo(t2.hashCode());
            }
        }
    }


    @Test
    public void testNotEqualHashes() throws Exception {
        List<T> instances = getNotEquals();
        assertThat(instances).isNotEmpty();

        for (T t1 : instances) {
            for (T t2 : instances) {
                if (t1 != t2 && t1.getClass() == t2.getClass()) {
                    assertThat(t1.hashCode()).isNotEqualTo(t2.hashCode());
                }
            }
        }
    }
}

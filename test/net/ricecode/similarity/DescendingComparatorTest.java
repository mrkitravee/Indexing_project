//2010 Ralph Allan Rice <ralph.rice@gmail.com>

package net.ricecode.similarity;

import static org.junit.Assert.*;

import org.junit.Test;

public class DescendingComparatorTest {

	@Test
	public void testCompareScoreFirstGreater() {
		SimilarityScore first = new SimilarityScore("First", 0.87);
		SimilarityScore second = new SimilarityScore("Second", 0.54);
		DescendingSimilarityScoreComparator c = new DescendingSimilarityScoreComparator();
		assertTrue(c.compare(first, second)<0);
		assertTrue(c.compare(second, first)>0);
	}
	
	@Test
	public void testCompareScoreSecondGreater() {
		SimilarityScore first = new SimilarityScore("First", 0.37);
		SimilarityScore second = new SimilarityScore("Second", 0.65);
		DescendingSimilarityScoreComparator c = new DescendingSimilarityScoreComparator();
		assertTrue(c.compare(first, second)>0);
		assertTrue(c.compare(second, first)<0);
	}

	@Test
	public void testCompareScoreEquality() {
		SimilarityScore first = new SimilarityScore("First", 0.96);
		SimilarityScore second = new SimilarityScore("Second", 0.96);
		DescendingSimilarityScoreComparator c = new DescendingSimilarityScoreComparator();
		assertEquals(c.compare(first, second), 0);
		assertEquals(c.compare(second, first), 0);
	}

}

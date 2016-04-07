package net.ricecode.similarity;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimilarityScoreTest {

    @Test
    public void testGetKey() {
        SimilarityScore s = new SimilarityScore("Test", 0.99);
        assertEquals("Test", s.getKey());
    }

    @Test
    public void testGetScore() {
        SimilarityScore s = new SimilarityScore("Test", 0.99);
        assertEquals(0.99, s.getScore(), 0.000);
        //test
    }

}

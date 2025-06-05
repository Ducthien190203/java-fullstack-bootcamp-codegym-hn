package code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TriangleClassifierTest {

	@Test
	void testEquilateralTriangle() {
		assertEquals("Tam giác đều", TriangleClassifier.checkTriangle(2, 2, 2));
	}

	@Test
	void testIsoscelesTriangle() {
		assertEquals("Tam giác cân", TriangleClassifier.checkTriangle(2, 2, 3));
	}

	@Test
	void testScaleneTriangle() {
		assertEquals("Tam giác thường", TriangleClassifier.checkTriangle(3, 4, 5));
	}

	@Test
	void testNotATriangle1() {
		assertEquals("Không phải tam giác", TriangleClassifier.checkTriangle(8, 2, 3));
	}

	@Test
	void testNotATriangle2() {
		assertEquals("Không phải tam giác", TriangleClassifier.checkTriangle(-1, 2, 1));
	}

	@Test
	void testNotATriangle3() {
		assertEquals("Không phải tam giác", TriangleClassifier.checkTriangle(0, 1, 1));
	}

}

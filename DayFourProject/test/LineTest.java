import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.ss.four.line.Line;
/**
 * 
 */

/**
 * @author sj
 *
 */
class LineTest {

	
	@Test
	public void testGetSlope() {
		double expectedValue = 1;
		double givenValue = new Line(1.0,1.0,2.0,2.0).getSlope();
		assertEquals(expectedValue, givenValue, .0001);
	}

	@Test
	public void testGetDistance() {
		double expectedValue = 1;
		double givenValue = new Line(1.0,1.0,1.0,2.0).getDistance();
		assertEquals(expectedValue, givenValue, .0001);
	}
	@Test
	public void test2GetDistance() {
		double expectedValue = 0;
		double givenValue = new Line(1.0,1.0,1.0,2.0).getDistance();
		assertEquals(expectedValue, givenValue, .0001);
	}
	
	
	@Test
	public void testParallelTo() {
		Line line = new Line(0,0,1,1);
		Line line2 = new Line(1,1,2,2);
		assertEquals(line.parallelTo(line2), true);
	}
}

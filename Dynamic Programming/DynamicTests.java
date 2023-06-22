package examdynamic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class DynamicTests {
	@Test
	public void testRecursive() {
		long answer1 = ExamDynamic.nttpot(0);
		assertEquals(answer1, 1);
		long answer2 = ExamDynamic.nttpot(1);
		assertEquals(answer2, 2);
		long answer3 = ExamDynamic.nttpot(2);
		assertEquals(answer3, 4);
		long answer4 = ExamDynamic.nttpot(3);
		assertEquals(answer4, 8);
		long answer5 = ExamDynamic.nttpot(4);
		assertEquals(answer5, 16);
		long answer6 = ExamDynamic.nttpot(10);
		System.out.println(answer6);
		assertEquals(answer6, 1024);
	}
	
	@Test
	public void testDyn1() {
		long answer1 = ExamDynamic.nttpotDyn1(0);
		assertEquals(answer1, 1);
		long answer2 = ExamDynamic.nttpotDyn1(1);
		assertEquals(answer2, 2);
		long answer3 = ExamDynamic.nttpotDyn1(2);
		assertEquals(answer3, 4);
		long answer4 = ExamDynamic.nttpotDyn1(3);
		assertEquals(answer4, 8);
		long answer5 = ExamDynamic.nttpotDyn1(4);
		assertEquals(answer5, 16);
		long answer6 = ExamDynamic.nttpotDyn1(10);
		System.out.println(answer6);
		assertEquals(answer6, 1024);
	}
	
	@Test
	public void testDyn2() {
		long answer1 = ExamDynamic.nttpotDyn2(0);
		assertEquals(answer1, 1);
		long answer2 = ExamDynamic.nttpotDyn2(1);
		assertEquals(answer2, 2);
		long answer3 = ExamDynamic.nttpotDyn2(2);
		assertEquals(answer3, 4);
		long answer4 = ExamDynamic.nttpotDyn2(3);
		assertEquals(answer4, 8);
		long answer5 = ExamDynamic.nttpotDyn2(4);
		assertEquals(answer5, 16);
		long answer6 = ExamDynamic.nttpotDyn2(10);
		System.out.println(answer6);
		assertEquals(answer6, 1024);
	}
}

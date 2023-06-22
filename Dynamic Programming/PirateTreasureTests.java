package piratetreasure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PirateTreasureTests {
	
	@Test
	void Test1 () {
		piratetreasuredynamic PT = new piratetreasuredynamic();
		
		assertEquals(PT.Locks(3, 1), 1);
		assertEquals(PT.Locks(3, 2), 3);
		assertEquals(PT.Locks(4, 2), 4);
		assertEquals(PT.Locks(4, 3), 6);
	}
	
	@Test
	void Test2 () {
		piratetreasuredynamic PT = new piratetreasuredynamic();		
		assertEquals(PT.Locks(27, 12), 13037895);
	}
	
	@Test
	void Test3 () {
		piratetreasuredynamic PT = new piratetreasuredynamic();		
		assertEquals(PT.Locks(30, 16), 155117520);
	}
	
	@Test
	void Test4 () {
		piratetreasuredynamic PT = new piratetreasuredynamic();		
		long answer = 118264581564861424L;
		assertEquals(PT.Locks(60, 31), answer);
	}
	
}

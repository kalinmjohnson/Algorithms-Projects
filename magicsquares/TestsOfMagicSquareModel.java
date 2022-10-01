package magicsquares;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Extremely basic tests of MagicSquareModel class
 * @author Nathan Gossett
 * @version Spring 2020
 *
 */
public class TestsOfMagicSquareModel {
	private MagicSquareModel myModel;
	
	/** create a blank 3x3 magic square before each test case */
	@BeforeEach
	public void setup(){
		myModel = new MagicSquareModel(4);
		//if you don't have that constructor, you can try this instead
		//myModel = new MagicSquareModel();
		//myModel.setSize(3);
	}
	
	/** make sure required interfaces are implemented */
	@Test
	public void testInterfaces() {
		
		//checking for SquareModel interface
		assertTrue(myModel instanceof SquareModel);
		
	}
	
	
	
	@Test
	public void testTitle() {
		
		//checking for SquareModel interface
		assertTrue(myModel.getTitle() == "Magic Squares");
		
	}
	
	/** Make sure size is initialized correctly */
	@Test
	public void testSize(){
		//check to make sure size is set properly
		assertEquals(4, myModel.getSize());
		
		
		myModel.setSize(5);
		assertEquals(5, myModel.getSize());
	}
	
	
	/** Make sure setValueAt() is working properly */
	@Test
	public void testSetter(){
		//check to make sure setValueAt() is changing value
		myModel.setValueAt("4", 1, 1);
		assertEquals( "4", myModel.getValueAt(1, 1));
		
	}
	
	
	/** Check the isMagic() and isNormalMagic() methods on a magic non-normal square*/
	/*
	@Test
	public void testMagicNotNormal(){
		//make the square magic but not normal
		myModel.setValueAt("1", 0, 0);
		myModel.setValueAt("1", 0, 1);
		myModel.setValueAt("1", 0, 2);
		myModel.setValueAt("1", 1, 0);
		myModel.setValueAt("1", 1, 1);
		myModel.setValueAt("1", 1, 2);
		myModel.setValueAt("1", 2, 0);
		myModel.setValueAt("1", 2, 1);
		myModel.setValueAt("1", 2, 2);
		
		assertTrue(myModel.isMagic());
		assertFalse(myModel.isNormalMagic());
		
	}
	
	 //Check the isMagic() and isNormalMagic() methods on a magic normal square
	@Test
	public void testNormalMagic(){
		//make the square normal magic
		myModel.setValueAt("2", 0, 0);
		myModel.setValueAt("7", 0, 1);
		myModel.setValueAt("6", 0, 2);
		myModel.setValueAt("9", 1, 0);
		myModel.setValueAt("5", 1, 1);
		myModel.setValueAt("1", 1, 2);
		myModel.setValueAt("4", 2, 0);
		myModel.setValueAt("3", 2, 1);
		myModel.setValueAt("8", 2, 2);
		
		assertTrue(myModel.isMagic());
		assertTrue(myModel.isNormalMagic());
	}
	
	//Test a square that isn't magic 
	@Test 
	public void testNotMagic() {
		myModel.setValueAt("1", 0, 0);
		myModel.setValueAt("2", 0, 1);
		myModel.setValueAt("1", 0, 2);
		myModel.setValueAt("1", 1, 0);
		myModel.setValueAt("1", 1, 1);
		myModel.setValueAt("1", 1, 2);
		myModel.setValueAt("1", 2, 0);
		myModel.setValueAt("1", 2, 1);
		myModel.setValueAt("1", 2, 2);
		assertFalse(myModel.isMagic());
		assertFalse(myModel.isNormalMagic());
	}
	*/
	//New Tests I have added
	
	@Test
	public void testNormalMagic2(){
		//make the square normal magic
		myModel.setValueAt("2", 0, 0);
		myModel.setValueAt("16", 0, 1);
		myModel.setValueAt("13", 0, 2);
		myModel.setValueAt("3", 0, 3);
		myModel.setValueAt("11", 1, 0);
		myModel.setValueAt("5", 1, 1);
		myModel.setValueAt("8", 1, 2);
		myModel.setValueAt("10", 1, 3);
		myModel.setValueAt("7", 2, 0);
		myModel.setValueAt("9", 2, 1);
		myModel.setValueAt("12", 2, 2);
		myModel.setValueAt("6", 2, 3);
		myModel.setValueAt("14", 3, 0);
		myModel.setValueAt("4", 3, 1);
		myModel.setValueAt("1", 3, 2);
		myModel.setValueAt("15", 3, 3);
		
		assertTrue(myModel.isMagic());
		assertTrue(myModel.isNormalMagic());
	}
	
	@Test
	public void testNormalMagic3(){
		//make the square normal magic
		myModel.setValueAt("2", 0, 0);
		myModel.setValueAt("16", 0, 1);
		myModel.setValueAt("13", 0, 2);
		myModel.setValueAt("3", 0, 3);
		myModel.setValueAt("11", 1, 0);
		myModel.setValueAt("5", 1, 1);
		myModel.setValueAt("8", 1, 2);
		myModel.setValueAt("10", 1, 3);
		myModel.setValueAt("7", 2, 0);
		myModel.setValueAt("9", 2, 1);
		myModel.setValueAt("11", 2, 2);
		myModel.setValueAt("6", 2, 3);
		myModel.setValueAt("14", 3, 0);
		myModel.setValueAt("4", 3, 1);
		myModel.setValueAt("1", 3, 2);
		myModel.setValueAt("15", 3, 3);
		
		assertFalse(myModel.isMagic());
		assertFalse(myModel.isNormalMagic());
	}
	
	@Test
	public void testClear() {
		myModel.setValueAt("2", 0, 0);
		myModel.setValueAt("16", 0, 1);
		myModel.setValueAt("13", 0, 2);
		myModel.setValueAt("3", 0, 3);
		myModel.setValueAt("11", 1, 0);
		myModel.setValueAt("5", 1, 1);
		myModel.setValueAt("8", 1, 2);
		myModel.setValueAt("10", 1, 3);
		myModel.setValueAt("7", 2, 0);
		myModel.setValueAt("9", 2, 1);
		myModel.setValueAt("12", 2, 2);
		myModel.setValueAt("6", 2, 3);
		myModel.setValueAt("14", 3, 0);
		myModel.setValueAt("4", 3, 1);
		myModel.setValueAt("1", 3, 2);
		myModel.setValueAt("15", 3, 3);
		
		myModel.clear();
		
		//checking for SquareModel interface
		assertTrue(myModel.getValueAt(1, 1) == "");
		/*assertTrue(myModel.getValueAt(2, 1) == null);
		assertTrue(myModel.getValueAt(3, 1) == null);
		assertTrue(myModel.getValueAt(3, 2) == null);
		assertTrue(myModel.getValueAt(3, 3) == null);*/
		
	}

}

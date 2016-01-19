package scribble3;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.MenuBar;
import java.awt.Point;
import java.awt.Shape;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSlider;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class scibbleJunitTest {
	
	Scribble s1 = new Scribble(null);
	Point point;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testPoint() {
		// TODO
		point = new Point();
		Stroke stroke = new Stroke();
		
		point.x= 50;
		point.y= 50;
		
		assertEquals(50,point.x);
		assertEquals(50,point.y);
		
	}
	
	@Test
	public void testLoadingButton(){
		
		JButton b1 =new JButton();
		b1 = s1.bgc;
		assertNotNull("Can't access the JButton",b1);
		
		
	}
	@Test
	public void testLoadingBOX(){
		
		Box b2 =new Box(0);
		b2 = s1.theBox;
		assertNotNull("Can't access the Box",b2);
		
		
	}
	@Test
	public void testLoadingMenuBar(){
		
		JMenuBar mb =new JMenuBar();
		mb = s1.menuBar;
		assertNotNull("Can't access the MenuBar",mb);
		
		
	}
	@Test
	public void testLoadingSlider(){
		
		JSlider slide =new JSlider();
		slide = s1.slider;
		assertNotNull("Can't access the Slider",slide);
		
		
	}
	@Test
	public void testFileComponents(){
		
		JMenu file = new JMenu();
		file = s1.menu;
		assertNotNull("Can't access the Slider",file);
		
	}
	
	
}

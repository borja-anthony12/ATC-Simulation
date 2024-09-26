package test;

import org.junit.Assert;




import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.Before;
	
class UnitTestTest {
	UnitTest UnitTest;
	@BeforeEach
	public void setUp() throws Exception {
		UnitTest = new UnitTest(7, 10);
	}

	@Test
	public void testAdd() {
		Assert.assertEquals(17,  UnitTest.add());
	}

}

package test;

import org.junit.Assert;




import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.Before;
import src.main.Tower;
	
class UnitTestsTower {
	Tower tower = new Tower(3);
	@BeforeEach
	public void setUp() throws Exception {

		
	}

	@Test
	public void unitTestCheckPlaneCollision() {
		tower.checkPlaneCollision();
	}

}

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.main.Tower;

class TowerTest {

	Tower tower;
	
	@BeforeEach
	void setUp() throws Exception {
		tower.checkPlaneCollision();
	}

	@Test
	void test() {
		
	}

}

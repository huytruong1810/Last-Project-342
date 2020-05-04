import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GuessTest {

	@Test
	void initTest() {
		WordGuessClient wgc = new WordGuessClient();
		assertEquals("WordGuessClient", wgc.getClass().getName());
	}

	@Test // test different object classes
	void test2() {
		WordGuessClient wgc = new WordGuessClient();
		assertNotEquals("WordGuessServer", wgc.getClass().getName());
	}

	@Test // test the same object reference
	void test3() {
		WordGuessClient wgc = new WordGuessClient();
		WordGuessClient temp = wgc;
		WordGuessClient temp2 = temp;
		assertSame(wgc, temp);
		assertSame(temp2, temp);
	}

	@Test // test different object reference
	void test4() {
		WordGuessClient wgc = new WordGuessClient();
		WordGuessClient temp = new WordGuessClient();
		assertNotSame(wgc, temp);
	}

}

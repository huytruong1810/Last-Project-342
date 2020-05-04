import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GuessTest {

	@Test
	void initTest() {
		WordGuessServer wgs = new WordGuessServer();
		assertEquals("WordGuessServer", wgs.getClass().getName());
	}

	@Test
	void trueWinTest() {
		ClientInfo clientInfo = new ClientInfo(1);
		clientInfo.givenWord = "    ";
		WordGuessServer wgs = new WordGuessServer();
		assertTrue(wgs.playerHasWon(clientInfo));
	}

	@Test
	void falseWinTest() {
		ClientInfo clientInfo = new ClientInfo(1);
		clientInfo.givenWord = "  B  ";
		WordGuessServer wgs = new WordGuessServer();
		assertFalse(wgs.playerHasWon(clientInfo));
	}

	@Test
	void trueLoseTest() {
		ClientInfo clientInfo = new ClientInfo(1);
		for (int i = 0; i < 6; ++i)
			clientInfo.numGuessLeft -= 1;
		WordGuessServer wgs = new WordGuessServer();
		assertTrue(wgs.playerHasLost(clientInfo));
	}

	@Test
	void falseLoseTest() {
		ClientInfo clientInfo = new ClientInfo(1);
		WordGuessServer wgs = new WordGuessServer();
		assertFalse(wgs.playerHasLost(clientInfo));
	}

	@Test
	void categoryNameTest() {
		WordGuessServer wgs = new WordGuessServer();
		String categoryName = wgs.categoryName('E');
		assertEquals("Error", categoryName);
	}

	@Test // test different object classes
	void differentObjectTest() {
		WordGuessServer wgs = new WordGuessServer();
		assertNotEquals("WordGuessClient", wgs.getClass().getName());
	}

	@Test
	void SameObjectRefTest() {
		WordGuessServer wgs = new WordGuessServer();
		WordGuessServer temp = wgs;
		WordGuessServer temp2 = temp;
		assertSame(wgs, temp);
		assertSame(temp2, temp);
	}

	@Test
	void DifObjectRefTest() {
		WordGuessServer wgs = new WordGuessServer();
		WordGuessServer temp = new WordGuessServer();
		assertNotSame(wgs, temp);
	}

}

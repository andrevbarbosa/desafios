package br.com.phzero.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TextAlignmentTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected=IllegalArgumentException.class)
	public void justifyToLineSize_NullTextGiven_ShouldThrowException() {
		TextAlignment.justifyToLineSize(null, 1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void justifyToLineSize_ZeroSizeGiven_ShouldThrowException() {
		TextAlignment.justifyToLineSize("abc", 0);
	}
	
	@Test
	public void justifyToLineSize_SmallTextFor40ColumnsGiven_ShouldReturnSameText() {
		String ori = "A small text.";
		String result = TextAlignment.justifyToLineSize(ori, 40);
		
		Assert.assertTrue(ori.equals(result));
	}

	@Test
	public void justifyToLineSize_LongTextFor40ColumnsGiven_ShouldReturnJustifiedText() {
		String ori = "A (not so) long text. We could talk here about God and the light and dark, but wife is Buddhist... "
				+ "Nichiren Buddhist in fact, so, I don't want to displease anyone, especially my wife."; 
		
		String mustBeTheResult = "A (not so) long text. We could talk here\n"
							   + "about God and the light  and  dark,  but\n"
				               + "wife is Buddhist... Nichiren Buddhist in\n"
				               + "fact, so,  I  don't  want  to  displease\n"
				               + "anyone, especially my wife.";
				
		String result = TextAlignment.justifyToLineSize(ori, 40);
		
		Assert.assertTrue(result.equals(mustBeTheResult));
	}

	@Test
	public void justifyToLineSize_TestGivenByIdWall_ShouldReturnJustifiedText() {
		// 8 lines
		String ori = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, "
				+ "darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n\n"
				+ "And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he "
				+ "separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And "
				+ "there was evening, and there was morning - the first day."; 
		
		String mustBeTheResult = "In the beginning God created the heavens\n"+
								 "and  the  earth.  Now  the   earth   was\n"+
								 "formless and empty,  darkness  was  over\n"+
							 	 "the surface of the deep, and the  Spirit\n"+
								 "of God was hovering over the waters.\n\n"+
								 "And God said, \"Let there be light,\"  and\n"+
							  	 "there was light. God saw that the  light\n"+
								 "was good, and  he  separated  the  light\n"+
								 "from the darkness. God called the  light\n"+
								 "\"day,\"  and  the  darkness   he   called\n"+
								 "\"night.\"  And  there  was  evening,  and\n"+
								 "there was morning - the first day.";
				
		String result = TextAlignment.justifyToLineSize(ori, 40);
		
		Assert.assertTrue(result.equals(mustBeTheResult));
	}

	@Test(expected=IllegalArgumentException.class)
	public void organizeToLineSize_NullTextGiven_ShouldThrowException() {
		TextAlignment.organizeToLineSize(null, 1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void organizeToLineSize_ZeroSizeGiven_ShouldThrowException() {
		TextAlignment.organizeToLineSize("abc", 0);
	}
	
	@Test
	public void organizeToLineSize_SmallTextFor40ColumnsGiven_ShouldReturnSameText() {
		String ori = "A small text.";
		String result = TextAlignment.organizeToLineSize(ori, 40);
		
		Assert.assertTrue(ori.equals(result));
	}

	@Test
	public void organizeToLineSize_LongTextFor40ColumnsGiven_ShouldReturnOrganizedText() {
		// 183 columns, 5 lines
		String ori = "A (not so) long text. We could talk here about God and the light and dark, but wife is Buddhist... "
				+ "Nichiren Buddhist in fact, so, I don't want to displease anyone, especially my wife."; 
		
		String mustBeTheResult = "A (not so) long text. We could talk here\n"
				+ "about God and the light and dark, but\n"
				+ "wife is Buddhist... Nichiren Buddhist in\n"
				+ "fact, so, I don't want to displease\n"
				+ "anyone, especially my wife.";
				
		String result = TextAlignment.organizeToLineSize(ori, 40);
		
		Assert.assertTrue(result.equals(mustBeTheResult));
	}


	@Test
	public void organizeToLineSize_LongLineFor40ColumnsGiven_ShouldReturnOrganizedText() {
		String ori = "A text with a line-that-make_no-sense-to-exist-just-because-I-have-to-test-this-with-more_than-40-characters. "
				+ "And a little bit more."; 
		
		String mustBeTheResult = "A text with a\n"
				+ "line-that-make_no-sense-to-exist-just-be\n"
				+ "cause-I-have-to-test-this-with-more_than\n"
				+ "-40-characters. And a little bit more.";
				
		String result = TextAlignment.organizeToLineSize(ori, 40);
		
		Assert.assertTrue(result.equals(mustBeTheResult));
	}
	
	@Test
	public void organizeToLineSize_LongTextFor40ColumnsWithLineSeparatorGiven_ShouldReturnOrganizedText() {
		// 8 lines
		String ori = "A (not so) long text. We could talk here about God and the light and dark, but wife is Buddhist... "
				+ "Nichiren Buddhist in fact, so, I don't want to displease anyone, especially my wife.\n"
				+ "And here, independently (I had to ask google how to write this word) how long is the last line, we "
				+ "started a new one."; 
		
		String mustBeTheResult = "A (not so) long text. We could talk here\n"
				+ "about God and the light and dark, but\n"
				+ "wife is Buddhist... Nichiren Buddhist in\n"
				+ "fact, so, I don't want to displease\n"
				+ "anyone, especially my wife.\n"
				+ "And here, independently (I had to ask\n"
				+ "google how to write this word) how long\n"
				+ "is the last line, we started a new one.";
				
		String result = TextAlignment.organizeToLineSize(ori, 40);

		Assert.assertTrue(result.equals(mustBeTheResult));
	}

	
	@Test
	public void organizeToLineSize_TestGivenByIdWall_ShouldReturnOrganizedText() {
		// 8 lines
		String ori = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, "
				+ "darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n\n"
				+ "And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he "
				+ "separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And "
				+ "there was evening, and there was morning - the first day."; 
		
		String mustBeTheResult = "In the beginning God created the heavens\n"+
									"and the earth. Now the earth was\n"+
									"formless and empty, darkness was over\n"+
									"the surface of the deep, and the Spirit\n"+
									"of God was hovering over the waters.\n\n"+
									"And God said, \"Let there be light,\" and\n"+
									"there was light. God saw that the light\n"+
									"was good, and he separated the light\n"+
									"from the darkness. God called the light\n"+
									"\"day,\" and the darkness he called\n"+
									"\"night.\" And there was evening, and\n"+
									"there was morning - the first day.";
				
		String result = TextAlignment.organizeToLineSize(ori, 40);

		Assert.assertTrue(result.equals(mustBeTheResult));
	}
}

















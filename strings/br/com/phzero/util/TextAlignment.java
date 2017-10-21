package br.com.phzero.util;

public class TextAlignment {
	private static final char SPACE_CHAR = ' ';
	private static final String LINE_SEPARATOR = System.getProperty("line.separator"); 

	public static void main(String[] args) {
//		System.out.println(organizeToLineSize("In the beginning God created the heavens and the earth. "
//				+ "Now the earth was formless and empty, darkness was over the surface of the deep, and "
//				+ "the Spirit of God was hovering over the waters.\n\nAnd God said, \"Let there be light,\" and there was light. God "
//				+ "saw that the light was good, and he separated the light from the darkness. God called "
//				+ "the light \"day,\" and the darkness he called \"night.\" And there was evening, "
//				+ "and there was morning - the first day.", 40));
////
	
//		System.out.println(organizeToLineSize("In the beginning God created the heavens and the earth. "
//				+ "Nowtheearthwasformlessandempty,darknesswasoverthesurfaceofthedeep,and"
//				+ "theSpiritofGodwashoveringoverthewaters.\n\nAnd God said, \"Let there be light,\" and there was light. God "
//				+ "saw that the light was good, and he separated the light from the darkness. God called "
//				+ "the light \"day,\" and the darkness he called \"night.\" And there was evening, "
//				+ "and there was morning - the first day.", 40));

//		System.out.println(organizeToLineSize("56 1256 7801 256\n78901234 56789 01234 56789 01234 56789 01234 56"
//				+ "7890123456 7890123456789012 3456789012345 678901234567890123456 7890123456789012345678901234567890123456789012345"
//				+ "678901234567890123456789012345678901234567890123456 78901234567890123456789012345678901234"
//				+ "56789012345678901234567890123456789 012345678901234567890123456789 0123456789012345678901234567890123456789", 40));

		System.out.println(justifyToLineSize("In the beginning God created the heavens and the earth. "
				+ "Now the earth was formless and empty, darkness was over the surface of the deep, and "
				+ "the Spirit of God was hovering over the waters.\n\nAnd God said, \"Let there be light,\" and there was light. God "
				+ "saw that the light was good, and he separated the light from the darkness. God called "
				+ "the light \"day,\" and the darkness he called \"night.\" And there was evening, "
				+ "and there was morning - the first day.", 80));
		
	}
	
	public static String justifyToLineSize(String text, int limit) {
		if (text==null || limit <1) 
			throw new IllegalArgumentException("Text must not be null and limit must be a positive value");
		
		String organizedText = organizeToLineSize(text, limit);
		
		String[] lines = organizedText.split("\n");
		
		StringBuilder newText = new StringBuilder(limit * lines.length);
		
		for (int l = 0; l<lines.length-1; l++) {
			if (lines[l].length()==limit || lines[l].length()<=1 || lines[l+1].length()==0) { 
				newText.append(lines[l]).append(LINE_SEPARATOR);
				continue;
			}
			
			StringBuilder line = new StringBuilder(lines[l]);
			int diff = limit - line.length();
			
			int cursor = line.length()-1;
			while(diff>0) {
				if (cursor<=1)
					cursor = line.length()-1;
				
				boolean space = false;
				
				while (!space && cursor>1) {
					if (line.charAt(cursor) == SPACE_CHAR) 
						space = true;
					else
						cursor--;
				}
				
				if (space) {
					line.insert(cursor, SPACE_CHAR);
					cursor--;	
				}
				
				if (cursor<1)
					cursor = line.length()-1;
				
				while (cursor>1 && " ".equals(line.substring(cursor, cursor+1))) {
					cursor--;
				}
				
				diff = limit - line.length();
			}
			newText.append(line).append(LINE_SEPARATOR);;
		}
		newText.append(lines[lines.length-1]);
		return newText.toString();
	}
		
	
	public static String organizeToLineSize(String text, int limit) {
		if (text==null || limit <1) 
			throw new IllegalArgumentException("Text must not be null and limit must be a positive value");
		
		if (text.length() <= limit) return text;
		
		StringBuilder newText = new StringBuilder(text);
		
		int cursor = 0;
		while (cursor<newText.length()) {
			cursor = cursor + limit;
			if (cursor >= newText.length()) break;
			//System.out.println(newText + "\n\n");
			
			if (newText.substring(cursor-limit+1, cursor).indexOf(LINE_SEPARATOR)>-1) {
				cursor = cursor - limit + newText.substring(cursor-limit+1, cursor).indexOf(LINE_SEPARATOR)+1;
				continue;
			}
			
			if (newText.charAt(cursor) == SPACE_CHAR) {
				newText.replace(cursor, cursor+1, LINE_SEPARATOR);
			}
			else {
				if (newText.substring(cursor, cursor+1).equals(LINE_SEPARATOR)) continue;
				
				// back some chars - max to limit - until find a space; 
				// if reach limit, put a line separator in the middle of big word.
				int checkpoint = cursor;
				while(checkpoint-cursor < limit) {
					if (newText.substring(cursor, cursor+1).equals(LINE_SEPARATOR)) break;
					if (newText.charAt(cursor) == SPACE_CHAR) {
						newText.replace(cursor, cursor+1, LINE_SEPARATOR);
						cursor = cursor + 1;
						break;
					}
					cursor--; 
				}
				if (checkpoint-cursor >= limit) {
					cursor = checkpoint;
					newText.insert(cursor, LINE_SEPARATOR);
					cursor = cursor + 1;
				}
			}
		}
		
		return newText.toString();
	}

}

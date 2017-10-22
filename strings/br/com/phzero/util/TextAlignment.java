package br.com.phzero.util;

public class TextAlignment {
	private static final char SPACE_CHAR = ' ';
	private static final String LINE_SEPARATOR = System.getProperty("line.separator"); 

	/**
	 * Justify a text within the number of columns.
	 * 
	 * @param text - The text to be justified. Must not be null.
	 * @param columns - The number of columns 
	 * @return String - the justified text.
	 */
	public static String justifyToLineSize(String text, int columns) {
		if (text==null || columns <1) 
			throw new IllegalArgumentException("Text must not be null and columns must be a positive value");
		
		String organizedText = organizeToLineSize(text, columns);
		
		String[] lines = organizedText.split("\n");
		
		StringBuilder newText = new StringBuilder(columns * lines.length);
		
		for (int l = 0; l<lines.length-1; l++) {
			if (lines[l].length()==columns || lines[l].length()<=1 || lines[l+1].length()==0) { 
				newText.append(lines[l]).append(LINE_SEPARATOR);
				continue;
			}
			
			StringBuilder line = new StringBuilder(lines[l]);
			int diff = columns - line.length();
			
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
				
				diff = columns - line.length();
			}
			newText.append(line).append(LINE_SEPARATOR);;
		}
		newText.append(lines[lines.length-1]);
		return newText.toString();
	}
		
	
	/**
	 * Organize a text within a maximum limit number of columns.
	 * 
	 * @param text - The text to be organized. Must not be null.
	 * @param limit - The limit of columns 
	 * @return String - the organized text in lines.
	 */
	public static String organizeToLineSize(String text, int limit) {
		if (text==null || limit <1) 
			throw new IllegalArgumentException("Text must not be null and limit must be a positive value");
		
		if (text.length() <= limit) return text;
		
		StringBuilder newText = new StringBuilder(text);
		
		int cursor = 0;
		while (cursor<newText.length()) {
			cursor = cursor + limit;
			if (cursor >= newText.length()) break;
			
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
	
	
	public static void main(String[] args) {
		
		System.out.println(justifyToLineSize("In the beginning God created the heavens and the earth. "
				+ "Now the earth was formless and empty, darkness was over the surface of the deep, and "
				+ "the Spirit of God was hovering over the waters.\n\nAnd God said, \"Let there be light,\" and there was light. God "
				+ "saw that the light was good, and he separated the light from the darkness. God called "
				+ "the light \"day,\" and the darkness he called \"night.\" And there was evening, "
				+ "and there was morning - the first day.", 40));
		
	}


}

package me.quickScythe.eridaunicore.utils;

public class ScrollingString {
	private String original;
	private int width;
	private int position;
 
	public ScrollingString(String original, int width) {
		if (width <= 0)
			throw new IllegalArgumentException("Width value has to be greater than 0");
		else if (original.length() < width)
			throw new IllegalArgumentException("String length has to be greater than the width value");
		this.original = original;
		this.width = width;
	}
 
	public void scrollForward() {
		position++;
		if (position == original.length())
			reset();
	}
 
	public void scrollBackward() {
		position--;
		if (position < 0)
			position = original.length() - 1;
	}
 
	public void reset() {
		position = 0;
	}
 
	public void setOriginal(String original) {
		if (original.length() < width)
			throw new IllegalArgumentException("String length has to be greater than the width value");
		this.original = original;
		reset();
	}
 
	public void append(String s) {
		original += s;
	}
 
	public String getOriginal() {
		return this.original;
	}
 
	public String getScrolled() {
		int e = position + width;
		return e > original.length() ? original.substring(position, original.length()) + original.substring(0, width - (original.length() - position)) : original.substring(position, e);
	}
 
	public int getWidth() {
		return this.width;
	}
 
	public int getPosition() {
		return this.position;
	}
}
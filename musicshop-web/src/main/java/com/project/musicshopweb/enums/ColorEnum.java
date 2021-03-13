package com.project.musicshopweb.enums;


public enum ColorEnum {
	RED(1, "rgba(240, 0, 0, 1)"),
	ORANGE(2, "rgba(240, 68, 0, 1)"),
	YELLOW(3, "rgba(240, 208, 0, 1)"),
	LIGHTGREEN(4, "rgba(72, 240, 0, 1)"),
	DARKGREEN(5, "rgba(32, 107, 0, 1)"),
	BLUESKY(6, "rgba(15, 255, 211, 1)"),
	BLUE(7, "rgba(15, 27, 255, 1)"),
	PURPLE(8, "rgba(159, 15, 255, 1)"),
	MAGENTA(9, "rgba(255, 15, 227, 1)"),
	ROSE(10, "rgba(255, 112, 162, 1)"),
	WHITE(11, "rgba(255, 255, 255, 1)"),
	BLACK(12, "rgba(0, 0, 0, 1)");
	private int i;
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	private String string;
	ColorEnum(int i, String string) {
		this.i = i;
		this.string = string;
	}
	
}

package model;

import java.io.Serializable;

public class Scream implements Serializable{

	private String userName;
	private String text;

	public Scream(){
	}

	public Scream(String userName, String text){
		this.userName = userName;
		this.text = text;
	}

	public String getUserName(){
		return this.userName;
	}
	public String getText(){
		return this.text;
	}
}

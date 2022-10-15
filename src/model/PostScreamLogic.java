package model;

import java.util.List;

public class PostScreamLogic {

	public void execute(Scream scream, List<Scream> screamList)
	{
		screamList.add(0, scream);
	}
}

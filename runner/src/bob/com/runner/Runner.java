package bob.com.runner;

import bob.com.screens.GameScreen;

import com.badlogic.gdx.Game;

public class Runner extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}

package bob.com.runner;



import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Main {
	public static void main(String[] args) {
		new LwjglApplication(new Runner(), "Runnar", 480, 320, true);
	}
}

import java.awt.Color;

import core.Window;
import sandbox.Test;

public class Main {

	public static void main(String[] args) {
		Window window = new Window("Window", 800, 600);
		window.addLayer(new Test("Bottom", Color.LIGHT_GRAY));
		window.addLayer(new Test("Top", Color.DARK_GRAY));
	}
	
}
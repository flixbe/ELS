package sandbox;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import events.Dispatcher;
import events.Event;
import events.types.MouseMotionEvent;
import events.types.MousePressedEvent;
import events.types.MouseReleasedEvent;
import layers.Layer;

public class Test extends Layer {

	private static final Random random = new Random();

	private boolean dragging = false;

	private String name;
	private Color color;
	private Rectangle rect;
	private int px, py;
	
	public Test(String name, Color color) {
		this.name = name;
		this.color = color;
		
		rect = new Rectangle(random.nextInt(100) + 100, random.nextInt(100) + 200, 100, 100);
	}
	
	public void onEvent(Event event) {
		Dispatcher dispatcher = new Dispatcher(event);
		dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onPressed((MousePressedEvent)e));
		dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onReleased((MouseReleasedEvent)e));
		dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> onMoved((MouseMotionEvent)e));
	}
	
	public void onRender(Graphics g) {
		g.setColor(color);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
		g.setColor(Color.WHITE);
		g.drawString(name, rect.x + 15, rect.y + 30);
	}
	
	private boolean onPressed(MousePressedEvent event) {
		System.out.println("Mouse pressed: " + event.getKeyCode());

		if (rect.contains(new Point(event.getX(), event.getY())))
			dragging = true;
		
		return dragging;
	}

	private boolean onReleased(MouseReleasedEvent event) {
		System.out.println("Mouse released: " + event.getKeyCode());
		
		dragging = false;
		
		return dragging;
	}

	private boolean onMoved(MouseMotionEvent event) {
		System.out.println("Mouse moved X: " + event.getX() + " | Y: " + event.getY());
		
		if (dragging) {
			rect.x += event.getX() - px;
			rect.y += event.getY() - py;
		}
		
		px = event.getX();
		py = event.getY();
		
		return dragging;
	}
	
}
//Testing the GitHub uploading.

package doctord;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public interface Actable {
	
	/*
	 * Moves the Actable relative to its current location
	 * @param delta;
	 */
    void move(Vector2f delta);
	
	/*
	 * Moves the Actable to a specific location
	 * @param location;
	 */
    void moveTo(Vector2f location);
	
	/*
	 * Updates the Actable based on any changes 
	 * that have happened, logic goes here
	 */
    void update();
	
	/*
	 * Draws the Actable to the Graphics object g
	 * @param g;
	 */
    void render(Graphics g);
	
	/*
	 * Prepare the Actable to be removed from the game
	 * @param time;
	 */
    void die(int time);
	
	/*
	 * Flags the Actable to be removed from the game
	 */
    boolean isDead();
	
	/*
	 * Handle a potential collision with another Actable
	 * @param a;
	 */
    boolean collide(Actable a);
	
	/*
	 * Returns the current location of the Actable as a Vector2f
	 */
    Vector2f getLocation();
	
	/*
	 * Returns the Animation of the Actable
	 */
    Animation getAnimation();
}

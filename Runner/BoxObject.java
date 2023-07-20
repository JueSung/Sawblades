
/**
 * Write a description of interface BoxObject here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface BoxObject
{
    /**
     * isInside
     * if the hitbox of an object is overlapping another
     */
    public abstract boolean isInside(BoxObject o);
    public abstract int[] getxs();
    public abstract int[] getys();
    public abstract int getX();
    public abstract int getY();
}

package Physics;

public class Vector2D {
    public float x;
    public float y;

    public Vector2D(float x, float y){
        this.x = x;
        this.y = y;
    }
    public Vector2D(){
        this.x = 0;
        this.y = 0;
    }

    public void normalize(){
        double length = Math.sqrt((x*x) + (y*y));
        if (length == 0) {
            x = 0;
            y = 0;
            return;
        }
        x = (float) (x/length);
        y = (float) (y/length);
    }

    public String toString(){
        return "x: " + x + ", y: " + y;
    }
}

public class Sites 
{
    String name;
    int x;
    int y;

    public Sites()
    {
        this.name = "";
        this.x = 0;
        this.y = 0;
    }
    
    public Sites(String name, int x, int y)
    {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName()
    {
        return name;
    }

    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }

    @Override
    public String toString()
    {
        return name + " " + x + " " + y;
    }
}

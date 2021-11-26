package cat.dam.alex.radiogroup2;

public class TextViewConfigurable {
    int backgroundColor;
    int size;
    String color;

    public TextViewConfigurable(int backgroundColor, int size, String color) {
        this.backgroundColor = backgroundColor;
        this.size = size;
        this.color = color;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

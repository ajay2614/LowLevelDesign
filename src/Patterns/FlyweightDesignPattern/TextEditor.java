package Patterns.FlyweightDesignPattern;


public class TextEditor {

    private char character;
    private String font;
    private String charCase;


    public TextEditor(char character, String font, String charCase) {
        this.character = character;
        this.font = font;
        this.charCase = charCase;
    }


    public char getCharacter() {
        return character;
    }

    public String getFont() {
        return font;
    }

    public String getCharCase() {
        return charCase;
    }

    public void display(int row, int col) {
        System.out.println("displaying char: " + this.getCharacter() +
                            " font: " + this.getFont() +
                            " charCase: " + this.getCharCase() +
                            " row: " + row +
                            " col: " + col);
    }
}

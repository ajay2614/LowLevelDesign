package Patterns.FlyweightDesignPattern;


import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight Design Pattern Summary:
 *
 * The Flyweight pattern is used to reduce memory usage by sharing objects
 * that have common (repeatable) state. Instead of creating a new object
 * every time, we reuse existing ones.
 *
 * Core Concepts:
 *
 * 1. Intrinsic State:
 *    - Meaning: "Belonging to the object itself"
 *    - It is shared, constant, and stored inside the flyweight object.
 *    - In our case: character, font, charCase
 *    - Example: Every 'A' in Arial Uppercase looks the same → reuse it.
 *
 * 2. Extrinsic State:
 *    - Meaning: "Coming from outside the object"
 *    - It is passed into the object at runtime, and varies by context.
 *    - Not stored in the flyweight; instead, supplied temporarily.
 *    - In our case: row and col (position where the character appears)
 *    - Example: The same 'A' can appear at (0,0), (2,5), etc.
 *
 * Benefit:
 *   - Saves memory by avoiding duplication.
 *   - Clean separation between what varies per instance (extrinsic)
 *     and what stays shared (intrinsic).
 */


public class TextEditorController {
    Map<Character, Map<String, Map<String, TextEditor>>> textMap = new HashMap<>();

    public void displayCharacter(char character, String font, String charCase, Integer row, Integer col) {

        Map<String, Map<String, TextEditor>> fontMap = textMap.get(character);
        if (fontMap != null) {
            Map<String, TextEditor> charCaseMap = fontMap.get(font);
            if (charCaseMap != null && charCaseMap.containsKey(charCase)) {
                TextEditor editor = charCaseMap.get(charCase);
                editor.display(row, col);
            }
        }

        else {
            TextEditor textEditor = new TextEditor(character, font, charCase);
            Map<String, TextEditor> charCaseMap = new HashMap<>();
            charCaseMap.put(charCase, textEditor);
            Map<String, Map<String, TextEditor>> fontMap1 = new HashMap<>();
            fontMap1.put(font, charCaseMap);
            textMap.put(character, fontMap1);
            textEditor.display(row, col);
        }
    }

    public static void main(String[] args) {
        TextEditorController controller = new TextEditorController();

        controller.displayCharacter('H', "Courier", "Upper", 0, 0);
        controller.displayCharacter('e', "Courier", "Lower", 0, 1);
        controller.displayCharacter('H', "Courier", "Upper", 1, 0);
    }
}

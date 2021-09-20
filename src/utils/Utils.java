/*=============================================================================
Code Breaker
Terry Zha and Jonathan Xie
September 23, 2020
Java 13.0.2
The class of utility methods
===============================================================================
*/

package utils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {

    /**loadFileAsArrayList method
     * This functional method loads the file from a specified path as an
     * ArrayList of String, with each line of the content of the files as
     * an element of the ArrayList
     *
     * List of Local Variables:
     * lines - the ArrayList that stores the content of the file </type ArrayList>
     * br - a BufferedReader object that reads the file </type BufferedReader>
     * line - a line of the content of the files </type String>
     *
     * @param path - the path of the file </type String>
     * @return lines - the content of the file </type ArrayList>
     */
    public static ArrayList<String> loadFileAsArrayList(String path){
        ArrayList<String> lines = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null){
                if(!line.startsWith("//")){
                    lines.add(line);
                }
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return lines;
    }

    /**loadFileAsString method
     * This functional method loads the file from a specified path as a String
     *
     * List of Local Variables:
     * builder - an object that assists in concatenating Strings </type StringBuilder>
     * br - a BufferedReader object that reads the file </type BufferedReader>
     * line - a line of the content of the files </type String>
     *
     * @param path - the path of the file </type String>
     * @return builder.toString() - the content of the file </type String>
     */
    static String loadFileAsString(String path){

        StringBuilder builder = new StringBuilder();

        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null){
                String newLine = line + "\n";
                if(!newLine.startsWith("//")){
                    builder.append(newLine);
                }
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return builder.toString();
    }

    /**toIntArrayColour method
     * This functional method converts a colour combination from
     * its String form to its integer array form
     *
     * List of Local Variables:
     * colours - the int array form of the colour combination </type int[]>
     *
     * @param coloursStr - the String form of the color combination </type String>
     * @return colours - the int array form of the colour combination </type int[]>
     */
    public static int[] toIntArrayColour(String coloursStr){
        int[] colours = new int[4];
        for(int i = 0; i < 4; i++){
            switch (coloursStr.charAt(i)) {
                case 'B':
                    colours[i] = 0;
                    break;
                case 'G':
                    colours[i] = 1;
                    break;
                case 'O':
                    colours[i] = 2;
                    break;
                case 'P':
                    colours[i] = 3;
                    break;
                case 'R':
                    colours[i] = 4;
                    break;
                case 'Y':
                    colours[i] = 5;
                    break;
            }
        }
        return colours;
    }

    /**drawText method
     * This procedural method renders text on screen
     *
     * List of Local Variables:
     * fm - the dimension status of a given front, used to measure the
     *      space the text is going to take on the screen </type FontMetrics>
     * x - the coordinate of the left most pixel of the text </type int>
     * y - the coordinate of the upper most pixel of the text </type int>
     *
     * @param graphics - a graphics context for drawing buffer </type Graphics>
     * @param text - the text that is to be rendered on the screen </type String>
     * @param xPos - the centre x position of the text </type int>
     * @param yPos - the centre y position of the text </type int>
     * @param colour - the colour of the text </type Colour>
     * @param font - the font of the text used </type Font>
     */
    public static void drawText(Graphics graphics, String text, int xPos, int yPos, Color colour, Font font){
        graphics.setColor(colour);
        graphics.setFont(font);
        FontMetrics fm = graphics.getFontMetrics(font);
        int x = xPos - fm.stringWidth(text)/2;
        int y = (yPos - fm.getHeight()/2) + fm.getAscent();
        graphics.drawString(text, x, y);
    }

    /**splitString method
     * This functional method split Strings into substrings without
     * damaging the word structure of the sentence. It is used for
     * text wrapping.
     *
     * List of Local Variables:
     * builder - an object that assists in concatenating Strings </type StringBuilder>
     * output - the sentence split into multiple lines </type ArrayList>
     * size - an integer which counts the length of the current line </type int>
     *
     * @param string - the original sentence </type String>
     * @param substringSize - the maximum length of a subString </type int>
     * @return output - the sentence split into multiple lines </type ArrayList>
     */
    public static ArrayList<String> splitString(String string, int substringSize){
        StringBuilder builder = new StringBuilder();
        ArrayList<String> output = new ArrayList<>();
        int size = 0;
        for(String str: string.split("\\s+")){
            if(size >= substringSize){
                output.add(builder.toString());
                builder = new StringBuilder();
            }
            builder.append(str).append(" ");
            size = builder.length();
        }
        output.add(builder.toString());
        return output;
    }


}

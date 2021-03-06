import java.util.ArrayList;
       
public class IndentationTools {    
    
    // Go through the source code line by line and fix intendation
    public ArrayList<String> correctIndentation(ArrayList sourceCode) {
        ArrayList<String> fixedCode = new ArrayList<>();
        int lineNumber = 0;
        int level = 0;
        
        System.out.println("\nFixing indentation...");

        while (lineNumber < sourceCode.size() - 1) {
            
            System.out.println("Processing line " + lineNumber + "...");
            String line = (String) sourceCode.get(lineNumber);
            String nextLine = (String) sourceCode.get(lineNumber + 1);
            nextLine = nextLine.trim();
            String fixedLine = printIndentation(level) + line.trim();
            fixedCode.add(fixedLine);
            
            // Check if the next line alone justifies lowering the indentation level
            if (nextLine.equals("}")) {
                if (level > 0) {
                    level--;
                }
            }                   
            
            // See if the current line is empty
            else if (fixedLine.length() == 0) {
                level = level;
            }
                
            // See if the current line is a single character
            else if (fixedLine.length() == 1) {
                if (fixedLine.equals("{")) {
                    level++;
                }
            }
            
            // See if the current line is a single line comment
            else if (fixedLine.trim().length() > 1 && fixedLine.trim().substring(0,2).equals("//")) {
                level = level;
            } 
            
            // See if the current line is a beginning of a multi line comment
            else if (fixedLine.trim().length() > 1 && fixedLine.trim().substring(0,1).equals("/") && fixedLine.trim().substring(1,2).equals("/")) {
                while (true) {
                    line = (String) sourceCode.get(lineNumber);
                    fixedLine = printIndentation(level) + line.trim();
                    fixedCode.add(fixedLine);
                    if (fixedLine.trim().length() > 1 && fixedLine.trim().substring(fixedLine.length() - 2).equals("*/")) {
                        break;
                    }
                    lineNumber++;
                }
            }
            
            // See if the current line ends in { *
            else if (fixedLine.substring(fixedLine.length() - 1).equals("{")) {
                level++;
            }       
            
            // See if the next line begins with } *
            else if (nextLine.length() > 0) {
                if (nextLine.substring(0, 1).equals("}")) {
                    if (level > 0) {
                        level--;
                    }
                }
            }
                
            lineNumber++;
        }
        
        // Fix the last line
        String line = (String) sourceCode.get(lineNumber);
        String fixedLine = line.trim();
        fixedCode.add(fixedLine);        
       
        System.out.println("Indentation succesfully fixed.");
        
        return fixedCode;
        
    }
    
    // Add indentation in the beginning of the line
    public String printIndentation(int level) {
        int i = 0;
        String indentation = "";
        while (i < level) {
            indentation = indentation.concat("    ");
            i++;          
        }
        
        return indentation;
    }
    
}

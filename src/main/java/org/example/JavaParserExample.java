package org.example;

import java.io.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.YamlPrinter;
import com.github.javaparser.printer.XmlPrinter;
import com.github.javaparser.printer.DotPrinter;

public class JavaParserExample {

    public static void parseProgramComprehension() {
        // Path to the source code file to parse
        String sourceFilePath = "src/main/java/org/example/ProgramComprehension.java";

        // Create a new JavaParser instance
        JavaParser javaParser = new JavaParser();

        // Parse the file
        CompilationUnit cu = null;
        try {
            // Use the JavaParser instance to parse the file
            cu = javaParser.parse(new FileInputStream(sourceFilePath)).getResult().orElse(null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Generate files for AST in different formats
        if (cu != null) {
            try {
                // Save YAML representation
                YamlPrinter yamlPrinter = new YamlPrinter(true);
                PrintWriter yamlWriter = new PrintWriter(new File("AST.yaml"));
                yamlWriter.print(yamlPrinter.output(cu));
                yamlWriter.close();

                // Save XML representation
                XmlPrinter xmlPrinter = new XmlPrinter(true);
                PrintWriter xmlWriter = new PrintWriter(new File("AST.xml"));
                xmlWriter.print(xmlPrinter.output(cu));
                xmlWriter.close();

                // Save Dot representation
                DotPrinter dotPrinter = new DotPrinter(true);
                PrintWriter dotWriter = new PrintWriter(new File("AST.dot"));
                dotWriter.print(dotPrinter.output(cu));
                dotWriter.close();

                System.out.println("AST representations saved to files.");
            } catch (FileNotFoundException e) {
                System.out.println("Error writing to the file: " + e.getMessage());
            }
        }
    }
}

package org.example;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

        // Print the AST
        if (cu != null) {
            System.out.println("打印yaml格式");
            YamlPrinter yamlPrinter = new YamlPrinter(true);
            System.out.println(yamlPrinter.output(cu));
            System.out.println("打印xml格式");
            XmlPrinter xmlPrinter = new XmlPrinter(true);
            System.out.println(xmlPrinter.output(cu));
            System.out.println("打印dotviz格式");
            DotPrinter dotPrinter = new DotPrinter(true);
            System.out.println(dotPrinter.output(cu));
        }
    }
}




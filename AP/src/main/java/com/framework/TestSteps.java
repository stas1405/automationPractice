package main.java.com.framework;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TestSteps {
    private HashMap<String, List<Statement>> testSteps = new HashMap<>();

    public void setTestSteps(String className) {
        try {

            String cn =  "src\\\\"+className.replaceAll("\\.","\\\\")+".java";
            FileInputStream in = new FileInputStream(cn);
            CompilationUnit cu;
            try {
                // parse the file
                cu = JavaParser.parse(in);
                new MethodVisitor().visit(cu, null);
            } finally {
                in.close();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
 //       return null;
    }

    public HashMap<String, List<Statement>> getTestSteps() {
        return testSteps;
    }

private class MethodVisitor extends VoidVisitorAdapter<Object> {
    @Override
    public void visit(MethodDeclaration n, Object arg) {
       testSteps.put(n.getName(), n.getBody().getStmts());
        super.visit(n, arg);
    }
}}
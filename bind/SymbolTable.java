package bind;

import java.util.HashMap;
import java.util.LinkedList;
import ast.ASTNode;

// Implementamos el "scope manager" como un singleton

public class SymbolTable {
    private static SymbolTable instance;
    private LinkedList<HashMap<String, ASTNode>> scopes;

    private SymbolTable() {
        scopes = new LinkedList<>();
    }

    public static SymbolTable getSymbolTable() {
        if (instance == null) {
            instance = new SymbolTable();
        }
        return instance;
    }

    public static void reset() {
        instance = new SymbolTable();
    }

    public void openScope() {
        scopes.push(new HashMap<>());
    }

    public void closeScope() {
        scopes.pop();
    }

    public void insert(String name, ASTNode declaration) {
        scopes.peek().put(name, declaration);
    }

    public ASTNode lookup(String name) {
        for (HashMap<String, ASTNode> scope : scopes) {
            if (scope.containsKey(name)) {
                return scope.get(name);
            }
        }
        return null;
    }
}

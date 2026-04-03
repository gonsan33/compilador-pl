#!/bin/bash
javac -cp ".:cup.jar" tests/tests_ast/ASTTest.java && java -cp ".:cup.jar" tests.tests_ast.ASTTest

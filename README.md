# JLOX INTERPRETER

## FRONT-END

- scanning/lexing/lexical analysis: when you read through words and compose expressions and stmts
- parsing: when you compose the AST
- static analysis: where we bind and resolve identifiers. If the language is statically typed, we type-check.

## MIDDLE-END

- intermediate representations: it represents the frontend of a language and the idea is to make easier to implement the backend for different architectures (an x86 will compile your code differently from a ARM CPU)


# AGENTS.md

## Context

This project involves building **custom language support for IntelliJ Platform IDEs** using the IntelliJ SDK.

The implementation includes (but is not limited to):

- Lexer (JFlex)
- Parser (Grammar-Kit / BNF)
- PSI (Program Structure Interface)
- Syntax Highlighting
- Code Completion
- Inspections
- Formatting
- Language Injection (if needed)

---

## Source of Truth (MANDATORY)

All decisions, implementations, and suggestions **MUST** be aligned with the latest official JetBrains documentation:

👉 https://plugins.jetbrains.com/docs/intellij/custom-language-support.html

### Rules:
- Always prioritize **official documentation over assumptions**
- Do not rely on outdated blog posts or StackOverflow answers if they conflict with official docs
- If unsure, **default to documentation-backed patterns**

---

## Development Principles

### 1. Follow IntelliJ Platform Best Practices
- Use PSI-based architecture correctly
- Avoid hacks or shortcuts that bypass PSI/AST
- Respect separation of concerns (Lexer → Parser → PSI → Features)

---

### 2. Production-Quality Code Only
- Code must be clean, structured, and maintainable
- Follow naming conventions used in IntelliJ SDK
- Avoid experimental or fragile approaches unless explicitly required

---

### 3. Grammar & Parsing Standards
- BNF must be:
  - Deterministic
  - Non-ambiguous
  - Maintainable
- Avoid left recursion unless properly handled
- Ensure lexer and parser tokens are always in sync

---

### 4. Lexer Guidelines (JFlex)
- Keep rules explicit and ordered correctly
- Avoid overly greedy patterns
- Ensure all tokens used in BNF are defined in lexer
- Handle whitespace and comments properly

---

### 5. PSI Design
- Generate PSI via Grammar-Kit wherever possible
- Do not manually implement PSI unless necessary
- Keep PSI tree minimal but expressive

---

### 6. Error Handling
- Parser must recover gracefully from errors
- Avoid breaking the PSI tree on minor syntax issues
- Use IntelliJ error elements where appropriate

---

### 7. Incremental Development Approach
Always suggest development in phases:

1. Lexer validation
2. Parser structure
3. PSI generation
4. Syntax highlighting
5. Completion
6. Advanced features (inspections, references, etc.)

---

### 8. Compatibility
- Ensure compatibility with latest stable IntelliJ Platform SDK
- Avoid deprecated APIs
- Prefer modern APIs over legacy ones

---

## When Assisting

When acting as an agent:

- Always assume the user is building a **real plugin**, not a demo
- Provide **complete, working solutions**
- Highlight **edge cases and pitfalls**
- Cross-check with official documentation before answering
- Prefer **clarity over cleverness**

---

## Anti-Patterns (STRICTLY AVOID)

- ❌ Guessing IntelliJ internals
- ❌ Mixing XML PSI with custom language PSI incorrectly
- ❌ Hardcoding logic that should be grammar-driven
- ❌ Ignoring token mismatches between lexer and parser
- ❌ Providing partial or pseudo-code solutions

---

## Goal

The goal is to build a **robust, scalable, and production-ready custom language plugin** for IntelliJ using correct architecture and best practices.

---

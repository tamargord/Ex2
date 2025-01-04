# Ex2:
## Spreadsheet class:
provides a basic implementation of a spreadsheet.
It  evaluate values and formulas within the cells.

### Methods:
 the method Cell get-returns the cell at the specified coordinates.

 the method set-sets a cell at the specified coordinates.

 the method width()-returns the width of the spreadsheet.

 the method height()-returns the height of the spreadsheet.

 the method xCell-converts a column into an integer index.

 the method yCell-converts a row into an integer index.

 the method eval-calculates the cell value at (x, y).

 the method evalAll()- calculates the cells in the spreadsheet and returns a 2D array of results.

 the method depth()-returns a 2D array indicating the depth of cell dependencies.
## Cell class:
The Cell class is  designed to represent and evaluate the values within a spreadsheet.
 It supports text, numeric values, and formulas.

### Methods:
 the method setValue-Sets the value of the cell and validates the input to ensure it is valid.

 the method isNumber-checks if the given text represents a numeric value.

 the method isText-Checks if the given text is valid.

 the method boolean isForm-Determines if the given text is a valid formula.

  the method  computeForm-Computes the result of a given formula.

 the method countBracket(String brackets)-checks the correctness of parentheses in a formula.

 the method  evaluateExpression-a recursive method handling parentheses.

 the method evaluateSimpleExpression-calculates basic expressions without parentheses.

  the method processMultiplicationAndDivision-does multiplication and division operations.

  the method processAdditionAndSubtraction-does addition and subtraction operations.

 the method findFirstOperatorIndex-finds  the index of the first occurrence of specified operators.



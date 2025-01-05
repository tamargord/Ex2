# Ex2:
## Spreadsheet class:
provides a basic implementation of a spreadsheet.
It  evaluate values and formulas within the cells.

### Methods:
`the method Cell get`- it returns the cell at the specified coordinates.

 `the method set`-it sets a cell at the specified coordinates.

 `the method width()`- it returns the width of the spreadsheet.

` the method height()`- it returns the height of the spreadsheet.

 `the method xCell`- it converts a column into an integer index.

 `the method yCell`- it converts a row into an integer index.

 `the method eval`-it calculates the cell value at (x, y).

 `the method evalAll()`-it  calculates the cells in the spreadsheet and returns a 2D array of results.

` the method depth()`-it returns a 2D array indicating the depth of cell dependencies.
## Cell class:
The Cell class is  designed to represent and evaluate the values within a spreadsheet.
 It supports text, numeric values, and formulas.

### Methods:
 `the method setValue`- it sets the value of the cell and validates the input to ensure it is valid.

 `the method isNumber`-it checks if the given text represents a numeric value.

 `the method isText`- it checks if the given text is valid.

 `the method boolean isForm`- it determines if the given text is a valid formula.

 ` the method  computeForm`- it computes the result of a given formula.

 `the method countBracket(String brackets)`-it checks the correctness of parentheses in a formula.

` the method  evaluateExpression`-it's a recursive method handling parentheses.

 `the method evaluateSimpleExpression`-it calculates basic expressions without parentheses.

  `the method processMultiplicationAndDivision`-it does multiplication and division operations.

   `the method processAdditionAndSubtraction`-it does addition and subtraction operations.

`the method findFirstOperatorIndex`-it finds the index of the first occurrence of specified operators.



# Sudoku puzzle validation

## Conditions of satisfaction(ACs)
1. Given a sudoku grid, system should print VALID if:
   - supplied grid is standard 9x9 Sudoku puzzle
   - all the element of the grid are less than 9
   - each row contains unique values from 1-9
   - each column contains unique values from 1-9
   - each of the 9 sub-squares, of size 3x3, contains a unique value from 1-9
2. Given a sudoku grid, system should print INVALID if:
   - supplied filepath as argument does not exist
   - supplied grid is not standard 9x9 Sudoku puzzle
   - any grid element with value greater than 9 (element > 9) 
   - any row with duplicate values from 1-9
   - any column with duplicate values from 1-9
3. Given an INVALID grid, system should print error text

## Assumptions
- Grid cells would be separated by comma
- Empty cells will be marked as 0 (number zero)

## Usage
- Package: `mvn clean package`
- Reports: `mvn surefire-report:report`
- Test: `mvn test`
- Execute app jar `java -jar ./target/sudoku-1.0-SNAPSHOT.jar ./src/test/resources/valid.txt`
- Run script `./validate.sh_rename ./src/test/resources/invalid.txt`
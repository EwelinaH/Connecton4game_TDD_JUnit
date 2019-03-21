**Connecton4game_TDD_JUnit**  
Created using Java, Gradle, JUnit, Hamcrest, JaCoCo, based on TDD.  

A simple game in which two players fills the 6x7 board columns with their own discs.
Each player has his own color.
When one color fills 3 or more than 3 fields vertically, horizontally or diagonally, this player wins.

**Requirement1**
The board is composed by 7 horizontal and 6 vertical empty positions

**Requirement2**
Players introduce discs on the top of the columns.
Introduced disc drops down the board if the column is empty.
Future discs introduced in the same column will stack over previous ones

**Requirement3**
It is a two-person game so there is one colour for each player.
One player uses red ('R'), the other one uses green ('G').
Players alternate turns, inserting one disc every time

**Requirement4**
We want feedback when either, event or error occur within the game.
The output shows the status of the board on every move

**Requirement5**
When no more discs can be inserted, the game finishes and it is considered a draw

**Requirement6**
If a player inserts a disc and connects more than 3 discs of his colour 
in a straight vertical line then that player wins

**Requirement7**
If a player inserts a disc and connects more than 3 discs of his colour
in a straight horizontal line then that player wins

**Requirement8**
If a player inserts a disc and connects more than 3 discs of his colour
in a straight diagonal line then that player wins




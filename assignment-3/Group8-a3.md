# Assignment 3

## Exercise 1

### Task

Choose two design patterns among those that we cover in class until the lecture on Nov 23 (included),
excluding the pattern you already implemented in Assignment 2 (e.g., Singleton). For each chosen design
pattern, you must have a corresponding implementation in your code. If not, refactor your code to include
it. Then, complete the following points:

### Design Pattern 1

#### Task 1

Write a natural language description of why and how the pattern is implemented in your code.

#### Description of why and how the pattern is implemented

For exercise 3, we would like to implement the undo command. For that we need a way to store
the mutations of the board in a list, that we can undo them later. Currently, we have 3 mutations which
are possible on the board:
1. Move a piece from BoardCoordinates A to BoardCoordinates B.
2. Make a jump move from BoardCoordinates A to BoardCoordinates C, which removes the Piece at BoardCoordinates B between
A and C.
3. When a jump gamble is lost, just remove the piece at the start of the jump move.

Additionally, both of them may result in a pawn being converted to a king.
In this step, instead of directly mutating the Board in the `Board::executeMove` method,
we will construct a command, then execute it. Later in exercise 3, we will store the executed command
at the end of the command list, than we can undo them in the reverse execution order.

We have a special case for the command pattern here, because the Client and the Invoker are the same
class and instance. Board is responsible for creating the correct instance of the Command,
which may be a JumpGambleLostMove, a JumpMove or a SimpleMove. It also sets the Receiver of the Command,
which is the Store, via the constructor of the command. This is the responsibility of the client.
Then it directly executes the command, which is the responsibility of the Invoker.

Below is the image of the command pattern as explained in the lecture to compare with our explanation.

![Command pattern as explained in the lecture](exercise1/command-pattern/command-pattern-as-explained-in-lecture.png)

#### Task 2

Make a sequence diagram of how the pattern works dynamically in your code

#### Sequence diagram

![Command pattern sequence diagram](exercise1/command-pattern/sequence.svg)

#### Task 3

Make a class diagram of how the pattern is structured statically in your code

#### Class diagram

![Command pattern sequence diagram](exercise1/command-pattern/class.svg)

### Design Pattern 2

#### Task 1

Write a natural language description of why and how the pattern is implemented in your code.

#### Description of why and how the pattern is implemented


#### Task 2

Make a sequence diagram of how the pattern works dynamically in your code

#### Sequence diagram

#### Task 3

Make a class diagram of how the pattern is structured statically in your code

#### Class diagram

## Exercise 2

Consider ten important classes in your checkers game.

### Task 1

Describe why these classes are important in your system’s design and what their current responsibilities are
(you must use UML diagrams to support your description)

#### UML Diagram

#### Description of the 10 most important classes and their responsibilities

### Task 2

Since these are important classes, you want to make sure that they are well tested! Write unit tests
to reach at least 80% line coverage in each of them. If this goal is not achievable (or not important),
you can explain why.

### Task 3

Create a testing report for your system with: (1) line coverage overall and (2) a histogram with the
distribution of the line coverage.

#### Overall line coverage report

#### Histogram of the line coverage distribution


## Exercise 3

### Undo Feature

#### Task 1

Google (used to?) ask their employees to spend 20% of their time at Google on a project that their
job description does not cover. As a result of the 20% Project, Google now has services such as
Gmail and AdSense.
This is your occasion to have similar freedom. You can decide what to do next to your game:5 It can
be an extension/improvement from any perspective, such as improved code quality or novel features.
Define your own requirements and get them approved by your tutor (especially in terms of load).
Afterwards you must implement the requirements.

##### Description of the requirements

To be able to improve one's checkers skills with our implementation of checkers, we will implement
the possibility to undo a turn. The simple use case is that Player WHITE makes his turn and hands over
the keyboard. Then Player WHITE realises, that his turn was not a good choice. Player WHITE asks player RED,
if he can try his turn again. If Player RED agrees, he types 'undo' instead of his move, and hands back the keyboard.

The second use case is if 2 Players want to test different strategies starting from a specific situation.
They may make their moves until they reach the specific situation, and then try a strategy to win the game
from this situation. Then they undo their moves again until they reach the situation again, and try a different one.

The undo command has 3 different outcomes depending on the moment when the undo command is used.

1. At the start of the game, there are no moves that can be undone. An error message is displayed and Player RED can
try again to enter a valid move.
2. A Player enters 'undo' instead of his first move in his turn. The last turn of the other player is undone, and the
other player may try other moves for his turn.
3. A Player enters 'undo' instead of a second (or more) jump move, or a second (or more) move for a won jump gamble move.
Then all the previous moves of his turn are undone, and the Player starts his turn again.

#### Task 2

During the analysis and design phases of this extension use responsibility driven design and UML
(push to the repository the single PDF file including all the produced documents)

##### Responsibility Driven Design

We have the following new responsibilities:

1. If the user types 'undo' into the console, we have to trigger the undo
This responsibility was given to the GameLogic class, as it already parses the input and triggers its effects
2. We have to store the executed commands
This responsibility was given to the Board class, as it already creates the correct Move Command
(JumpGambleLostMove, JumpMove, SimpleMove) and executes it.
3. When the undo is triggered, we need to iterate over the commands we need to undo and trigger that they are undone
This responsibility was given to the Board class.
4. For the 3 possible mutations of the board, we need to undo their respective effect.
This responsibility was given to the respective commands JumpGambleLostMove, JumpMove and SimpleMove.

![Undo feature CRC Cards](exercise3/undo-feature/crc.svg)

##### UML

##### Sequence Diagram

![Undo feature sequence diagram](exercise3/undo-feature/sequence.svg)

##### Class Diagram

The changed classes are in green.

![Undo feature class diagram](exercise3/undo-feature/class.svg)

### Balanced Gamble Feature

#### Task 1

Google (used to?) ask their employees to spend 20% of their time at Google on a project that their
job description does not cover. As a result of the 20% Project, Google now has services such as
Gmail and AdSense.
This is your occasion to have similar freedom. You can decide what to do next to your game:5 It can
be an extension/improvement from any perspective, such as improved code quality or novel features.
Define your own requirements and get them approved by your tutor (especially in terms of load).
Afterwards you must implement the requirements.

##### Description of the requirements

#### Task 2

During the analysis and design phases of this extension use responsibility driven design and UML
(push to the repository the single PDF file including all the produced documents)

##### Responsibility Driven Design

##### CRC Cards

##### UML

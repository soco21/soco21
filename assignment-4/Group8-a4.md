# Assignment 4

## Exercise 1

### CRC- Cards
#### Initial CRC-Cards and Design Ideas
Using responsibility driven design, we came up with an initial design on how our blackjack game would look like. 
Since blackjack is a game that involves cards, we took the concepts from the lecture on how cards and decks should
be represented in our system i.e. A deck consists of 52 cards and each card consists of a rank and a suit. So the 
first classes Rank, Suit, Card and Deck were set in stone, where Rank and Suit are to be enum classes.

Next, we discussed on how to design a Hand - the cards that each player has. At first, we thought that a hand is just another deck 
that each player owns hence making it a subclass of the Deck class. However, in a real life scenario a hand does not have all 
the functionalities a deck has, for example, shuffling a hand would be redundant or creating a hand that already has cards
(hand should be empty at the beginning). Thus, we decided to make a Hand class that holds the initial two cards and cards drawn after each hit.
Furthermore, it should return the score that is valued for each hand.

As you can already see in our initial crc cards, the whole game is run in our Main class. In every new round, a loop is started, 
where the player bets a certain amount from their saldo. Different checks ensure that the rules of the game are not violated, e.g.
whether the player has busted, wants to hit or stay, or whether they have won the round. This made the main class hold the most 
responsibilities in our system. Now our aim was to group these responsibilities in separate classes.

#### Final CRC-Cards and Design
Our next step was to establish concepts such as Round, Player action and the very essence of a "Player".
In our simple blackjack game, there were only two players; the dealer(CPU) and the gambler(person interacting with console). Both players have similar 
functions such as: each one of them is dealt a hand and thus also a score and both are able to hit. The only difference is that gambler has a bet on the line and can 
stay by choice.
We will try to make an interface for the general idea of a "Player".

We also acknowledged that a blackjack game consists of rounds. Our first idea was to use a while-loop that terminates after a bust
or a win and the only way to exit this loop was to either go broke (saldo = 0) or with a command like "I do not want to bet". Then we thought,
"why don't make it its own class?", where each player's hand is reset to empty and requests for a bet to be set just like a generic
"Start New Game" button.

Lastly, we wanted to separate the player's actions (hit or stay) from our main class. Since the concept was simple, we decided to
make an enum class called "Play" that has both commands hit and stay as variables are used to ask the gambler whether they want
to hit or stay.





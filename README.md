Evolution Simulator
===================

The game begins by creating a 100 square land board and 10 different animals.
Each square has a food value assigned to it which persists through the game.
Each animal is given a unique color, a location, and charecteristics.
The animals are displayed as squares on screen.

Then the cycle is as follows:
Animals Fight
	-check if there are adjacent animals
	-if(if adjacentAnimal1.Attack>adjacentAnimal2.Defense):
		Adjacent animal will be killed and removed from the board
Animals eat (if the land food value>animal grow):
	-The animals will grow (if there are no adjacent animals)
		-The newly grown animal will have its predecessors stats plus a randomly assigned addition, and will appear on the screen.

Improvements to come:
-Add mutations to randomly change animal stats
-Make characteristics in the form of a genetic code, which will be parsed to assign statistics
	-Mutations will act on this genetic code to change animal stats
-Add functionality to enable the cursor to hover over the animal to get its stats
-Add game mode where user can design animals and add them to the game


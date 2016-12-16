package engine;

public class Design {
/*
Card game base
    only minions, no spells/other
    board limited to 10 minions
    place on the board doesn't matter, may change in the future
    minions have atk health and cost
    minions can have effects which alter the game
    minions can attack other minions or the player
    the defending play can choose to block with their minions or not
    players have life points that determine winner
    
Effects
	effects have an activation condition
	a number of maximum activations per turn
	a cost to activation
	effects can be trigger or manual activation
	effects can make multiple changes
	they must be nerfable and buffable within set limits
Events
	an event is something that happens in the game
	player actions and card effects will trigger different types of events
	such as minions being destroyed or players losing life points
Deck
	players have a deck of 30 cards
	up to 2-3 of a single type of card
	starting hand of 4
	1 draw per turn
buffing and nerfing
	cards will be buffed or nerfed based on popularity and winrate
	buffs could mean enhancing its effect, reducing cost, adding more effects
	reducing trigger conditions or removing negative effects
finding new cards
	the game will generate new cards by combining conditions with effects
	the cost, trigger type, effects and trigger conditions will be weighted
	the weights will be used to keep the cards lightly balanced from creation
	though all card combinations regardless of weight will be possible
Realms
	Legacy realm - contains all cards found that have been buffed/nerfed many times
	stays constant except for card additions once a month or so
	standard realm - allows finding new cards and playing them, old cards 
*/
}

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
    players can choose to allow their minions to be attacked or not**
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
	
	It will be possible to buy cards other players have found
	when bought they keep their card and you get a copy
	the rarity of the card will determine its cost
	the player who found the card gets a small % of the cost
	
	because most cards are bad, players will want to find good cards to sell
	the more a card is bought and used, the more likely it is to be kept in the game
	cards that are not bought or used will be deleted eventually, probably

Aesthetics
	players who find cards can decide on the picture and name of the card
	within some limits, maybe
	
Realms
	Legacy realm - contains all cards found that have been buffed/nerfed many times
	stays constant except for card additions once a month or so
	standard realm - allows finding new cards and playing them, old cards 
*/
}

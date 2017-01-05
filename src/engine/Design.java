package engine;

public class Design {
/*
Philosophy
    We don't want any complex rulings or counterintuitive ideas
    to complicate the game.
    The game needs to be VERY easy to learn this means tutorials
    and early card restriction.
    The complexity of the cards should create a very large pool of
    semi balanced decks.
    Forced deck variety may be introduced with classes and types.
    make sure there is planning that needs to be done each turn
    what creates the need to solve for the best move this turn?
    
Card game base
    only minions, no spells/other
    board limited to 10 minions
    place on the board doesn't matter, may change in the future
    minions have atk health and cost
    minions can have effects which alter the game
    minions can attack other minions or the player
    players can choose to allow their minions to be attacked or not**
    players have life points that determine winner
  
Commanders
	A commander will be selectable d
	
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
	
	crowd source art! make the game look cool and tell people they can
	have their art in the game, make it feel rewarding if they do.
	music probably not this approach
	
Realms
	Legacy realm - contains all cards found that have been buffed/nerfed many times
	stays constant except for card additions once a month or so
	standard realm - allows finding new cards and playing them, old cards 
	
	
Server design
	server will keep a database of accounts, containing their card bank and decks
	server will maintain a list of active games in threads
	a player can create a game and wait for a connection from another player
	the server will do the connecting when the game Id and password is given(if it exists)
	
Client Design
	when opening the client, a connection is made to the server
	and the login page is opened
	UI prompts user for account info which is sent to the server
	if server accepts the account info the player is taken to 
	the main page allowing them to create a game, browse a list
	of available games, or view their card bank
	
	
Cosmetics and personalization - goldmine
*/
}

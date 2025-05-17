import kotlin.random.Random

// Main function to play Poker
fun playPoker() {
    println("You chose Poker!")
    println("Dealing your cards...")

    // Create a deck of cards
    val deck = createDeck()
    // Deal 2 cards to the player
    val playerHand = dealCards(deck, 2)

    // Display the player's hand
    println("Your hand: ${playerHand.joinToString(", ")}")
    // Evaluate the player's hand
    evaluateHand(playerHand)
}

// Function to create a standard 52-card deck
fun createDeck(): MutableList<String> {
    val suits = listOf("Hearts", "Diamonds", "Clubs", "Spades") // Card suits
    val ranks = listOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace") // Card ranks
    val deck = mutableListOf<String>()

    // Combine each rank with each suit to form the full deck
    for (suit in suits) {
        for (rank in ranks) {
            deck.add("$rank of $suit")
        }
    }
    return deck
}

// Function to deal a specified number of cards from the deck
fun dealCards(deck: MutableList<String>, numberOfCards: Int): List<String> {
    val hand = mutableListOf<String>()
    // Randomly select cards from the deck and remove them
    repeat(numberOfCards) {
        val cardIndex = Random.nextInt(deck.size) // Get a random index
        hand.add(deck.removeAt(cardIndex)) // Remove the card from the deck and add it to the hand
    }
    return hand
}

// Function to evaluate the player's hand
fun evaluateHand(hand: List<String>) {
    // Simple evaluation logic: Check if the hand contains an Ace
    if (hand.any { it.contains("Ace") }) {
        println("You have an Ace! Nice hand!")
    } else {
        println("No Ace in your hand. Better luck next time!")
    }
}
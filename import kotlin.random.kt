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

// Main function to play Roulette
fun playRoulette() {
    println("You chose Roulette!")
    println("Place your bet on a number between 0 and 36:")

    // Read the player's bet
    val playerBet = readLine()?.toIntOrNull()
    if (playerBet == null || playerBet !in 0..36) {
        println("Invalid bet. Please restart the game and choose a number between 0 and 36.")
        return
    }

    // Spin the roulette wheel (random number between 0 and 36)
    val rouletteResult = Random.nextInt(37)
    println("The roulette wheel spins... and lands on $rouletteResult!")

    // Check if the player won
    if (playerBet == rouletteResult) {
        println("Congratulations! You won! Your number $playerBet matched the result!")
    } else {
        println("Sorry, you lost. Better luck next time!")
    }
}

// Main function to play Blackjack
fun playBlackjack() {
    println("You chose Blackjack!")
    println("Dealing your cards...")

    // Create a deck of cards
    val deck = createDeck()
    // Deal 2 cards to the player and 2 cards to the dealer
    val playerHand = dealCards(deck, 2)
    val dealerHand = dealCards(deck, 2)

    // Display the player's hand
    println("Your hand: ${playerHand.joinToString(", ")}")
    println("Dealer's visible card: ${dealerHand[0]}")

    // Calculate the initial hand values
    val playerScore = calculateHandValue(playerHand)
    val dealerScore = calculateHandValue(dealerHand)

    // Player's turn
    var playerTurnScore = playerScore
    while (playerTurnScore < 21) {
        println("Your current score: $playerTurnScore. Do you want to 'hit' or 'stand'?")
        val action = readLine()?.lowercase()
        if (action == "hit") {
            val newCard = dealCards(deck, 1)[0]
            println("You drew: $newCard")
            playerHand.add(newCard)
            playerTurnScore = calculateHandValue(playerHand)
        } else if (action == "stand") {
            break
        } else {
            println("Invalid action. Please type 'hit' or 'stand'.")
        }
    }

    // Check if the player busted
    if (playerTurnScore > 21) {
        println("You busted with a score of $playerTurnScore. Dealer wins!")
        return
    }

    // Dealer's turn
    println("Dealer's turn...")
    var dealerTurnScore = dealerScore
    while (dealerTurnScore < 17) {
        val newCard = dealCards(deck, 1)[0]
        println("Dealer drew: $newCard")
        dealerHand.add(newCard)
        dealerTurnScore = calculateHandValue(dealerHand)
    }

    // Check if the dealer busted
    if (dealerTurnScore > 21) {
        println("Dealer busted with a score of $dealerTurnScore. You win!")
        return
    }

    // Compare scores to determine the winner
    println("Your final score: $playerTurnScore")
    println("Dealer's final score: $dealerTurnScore")
    if (playerTurnScore > dealerTurnScore) {
        println("Congratulations! You win!")
    } else if (playerTurnScore < dealerTurnScore) {
        println("Dealer wins. Better luck next time!")
    } else {
        println("It's a tie!")
    }
}

// Function to calculate the value of a hand in Blackjack
fun calculateHandValue(hand: List<String>): Int {
    var value = 0
    var aces = 0

    for (card in hand) {
        val rank = card.split(" ")[0]
        value += when (rank) {
            "2", "3", "4", "5", "6", "7", "8", "9", "10" -> rank.toInt()
            "Jack", "Queen", "King" -> 10
            "Ace" -> {
                aces++
                11 // Assume Ace is 11 initially
            }
            else -> 0
        }
    }

    // Adjust for Aces if the value exceeds 21
    while (value > 21 && aces > 0) {
        value -= 10
        aces--
    }

    return value
}
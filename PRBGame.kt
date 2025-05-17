fun main() {
    println("Welcome to the Game Place!")
    println("Please choose a game to play:")
    println("1. Poker")
    println("2. Roulette")
    println("3. Blackjack")
    println("Enter your choice (1, 2, or 3):")

    val choice = readLine()?.toIntOrNull()

    when (choice) {
        1 -> playPoker()
        2 -> playRoulette()
        3 -> playBlackjack()
        else -> println("Invalid choice. Please restart the program and choose a valid option.")
    }
}

fun playPoker() {
    println("You chose Poker! (Game logic goes here)")
    // Add Poker game logic here
}

fun playRoulette() {
    println("You chose Roulette! (Game logic goes here)")
    // Add Roulette game logic here
}

fun playBlackjack() {
    println("You chose Blackjack! (Game logic goes here)")
    // Add Blackjack game logic here
}
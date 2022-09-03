package machine

val coffeeMachine = CoffeeMachine()
fun main() {
    while (coffeeMachine.state != State.QUIT) {
        coffeeMachine.printPrompt()
        coffeeMachine.action(readln())
    }
}






package machine

class CoffeeMachine {
    var state: State = State.MAIN_MENU
    private var water = 400
    private var milk = 540
    private var beans = 120
    private var cups = 9
    private var money = 550

    fun printPrompt() {
        print(state.prompt)
    }

    fun action(input: String) {
        when (state) {
            State.MAIN_MENU -> mainMenu(input)
            State.BUY -> buy(input)
            State.FILL_WATER, State.FILL_MILK, State.FILL_BEANS, State.FILL_CUPS -> fill(input.toInt())
            else -> print("Illegal State\n")
        }
    }

    private fun quit() {
        state = State.QUIT
    }

    private fun printStatus() {
        println(
            """ 
            
        The coffee machine has:
        $water ml of water
        $milk ml of milk
        $beans g of coffee beans
        $cups disposable cups
        $$money of money 
        
        """.trimIndent()
        )

        state = State.MAIN_MENU
    }

    private fun take() {
        println(
            """
            
            I gave you $$money
            
            """.trimIndent()
        )
        money = 0
        state = State.MAIN_MENU
    }

    private fun fill(input: Int) {
        when (state) {
            State.FILL_WATER -> water += input
            State.FILL_MILK -> milk += input
            State.FILL_BEANS -> beans += input
            State.FILL_CUPS -> cups += input
            else -> print("IllegalState!")
        }
        advanceFillState()
    }

    private fun advanceFillState() {
        state = if (state.ordinal < State.FILL_CUPS.ordinal) {
            State.values()[state.ordinal + 1]
        } else {
            println()
            State.MAIN_MENU
        }
    }

    private fun buy(input: String) {
        if (input != "back") {
            when (input.toInt()) {
                1 -> if (enoughSupplies(Coffees.ESPRESSO)) makeCoffee(Coffees.ESPRESSO)
                2 -> if (enoughSupplies(Coffees.LATTE)) makeCoffee(Coffees.LATTE)
                3 -> if (enoughSupplies(Coffees.CAPPUCCINO)) makeCoffee(Coffees.CAPPUCCINO)
            }
        }
        state = State.MAIN_MENU
    }

    private fun mainMenu(input: String) {
        when (input) {
            "buy" -> state = State.BUY
            "fill" -> state = State.FILL_WATER
            "take" -> take()
            "remaining" -> printStatus()
            "exit" -> quit()
            else -> println("ERROR!")
        }
    }

    private fun enoughSupplies(coffee: Coffees): Boolean {
        if (cups > 0) {
            if (coffee.waterNeeded < water) {
                if (coffee.milkNeeded < milk) {
                    if (coffee.beansNeeded < beans) {
                        return true
                    } else {
                        println("Sorry, not enough coffee beans! I need ${coffee.beansNeeded - beans} g more coffee beans!\n")
                    }
                } else {
                    println("Sorry, not enough milk! I need ${coffee.milkNeeded - milk} ml more milk!\n")
                }
            } else {
                println("Sorry, not enough water! I need ${coffee.waterNeeded - water} ml more water!\n")
            }
        } else {
            println("Sorry, not enough cups! I need at least one cup!\n")
        }
        return false
    }

    private fun makeCoffee(coffee: Coffees) {
        println("I have enough resources, making you a coffee!\n")

        water -= coffee.waterNeeded
        milk -= coffee.milkNeeded
        beans -= coffee.beansNeeded
        cups--
        money += coffee.price
    }

}
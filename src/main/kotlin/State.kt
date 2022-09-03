package machine

enum class State(val prompt:String) {
    MAIN_MENU("Write action (buy, fill, take, remaining, exit): "),
    BUY("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: "),
    FILL_WATER("\nWrite how many ml of water do you want to add: "),
    FILL_MILK("Write how many ml of milk do you want to add: "),
    FILL_BEANS("Write how many grams of coffee beans do you want to add: "),
    FILL_CUPS("Write how many disposable cups of coffee do you want to add: "),
    REMAINING(""),
    TAKE(""),
    QUIT("");
}
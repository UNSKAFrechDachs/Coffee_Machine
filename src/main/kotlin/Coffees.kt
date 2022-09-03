package machine

enum class Coffees(val waterNeeded: Int, val milkNeeded: Int, val beansNeeded: Int, val price: Int) {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6)
}
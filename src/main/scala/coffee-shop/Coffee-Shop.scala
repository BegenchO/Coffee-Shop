/*
Weekly Coding Challenge: Coffee Shop

Write a class called CoffeeShop, which has three instance variables:
    name: a string (basically, of the shop)
    menu: an array of items (of type MenuItem), with each item containing the item (name of the item), type (whether a food or a drink), and price.
    orders: an empty array

and seven methods:
    addOrder: adds the name of the item to the end of the orders array if it exists on the menu. Otherwise, return "This item is currently unavailable!"
    fulfillOrder: if the orders array is not empty, return "The {item} is ready!". If the orders array is empty, return "All orders have been fulfilled!"
    listOrders: returns the list of orders taken, otherwise, an empty array.
    dueAmount: returns the total amount due for the orders taken.
    cheapestItem: returns the name of the cheapest item on the menu.
    drinksOnly: returns only the item names of type drink from the menu.
    foodOnly: returns only the item names of type food from the menu.

IMPORTANT: Orders are fulfilled in a FIFO (first-in, first-out) order.
*/

import scala.collection.mutable.ArrayBuffer


object Main {
    def main(args: Array[String]): Unit = {
        var starbucks = new CoffeeShop("Starbucks", ArrayBuffer(MenuItem("Chai Latte", "Drink", 5.99)))
        println(starbucks.addOrder("Chai Latte"))
    }
}


case class MenuItem(item: String, itemType: String, itemPrice: Double)


class CoffeeShop(shopName: String, shopMenu: ArrayBuffer[MenuItem] = ArrayBuffer[MenuItem](), shopOrders: ArrayBuffer[MenuItem] = ArrayBuffer[MenuItem]()){

    var name: String = shopName
    var menu: ArrayBuffer[MenuItem] = shopMenu
    var orders: ArrayBuffer[MenuItem] = shopOrders

    def addOrder(itemName: String) : String = {
        for (i <- menu) {
            if (i.item == itemName) {
                orders += i
                return (s"${i.item} was added to our order list. It will be ready soon.")
            }
        } // end for
        return s"${itemName} is currently unavailable!"
    } // end add order


} // end class
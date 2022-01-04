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

        var coffeshop = new CoffeeShop("Delighted")
        var menuOn = true
        var response = ""

        while (menuOn) {

            delay
            displayMenu(coffeshop.name)
            response = getResponse("Please select an option")

            response match {
                case "1" => {
                    val newOrder = getResponse("What item would you like to order?")
                    println(coffeshop.addOrder(newOrder))
                }
                case "2" => println(coffeshop.fulfillOrder)
                case "3" => coffeshop.listOrders.foreach(i => println(s"${i.item}: ${i.itemPrice}"))
                case "4" => coffeshop.drinksOnly.foreach(i => println(s"${i.item}: ${i.itemPrice}"))
                case "5" => coffeshop.foodOnly.foreach(i => println(s"${i.item}: ${i.itemPrice}"))
                case "6" => println(coffeshop.dueAmount)
                case "7" => println(coffeshop.cheapestItem)
                case "0" => {
                    println("Thank you for visiting our coffee shop!")
                    menuOn = false
                }
                case _ => println("Invalid option. Please select one of the options from the menu between 0-7.")
            } // end match
            
        } // end while

    } // end main


    def displayMenu(name: String): Unit = {
        
        println("\n---------------------------")
        println(s"Welcome to our Coffee Shop ${name}!\n")
        println("Menu:")
        println("---------------------------")
        println("1. New Order")
        println("2. Complete Next Order")
        println("3. Show All Orders")
        println("4. Show All Drinks Only")
        println("5. Show All Food Only")
        println("6. Total Amount Owed For All Orders")
        println("7. Show Cheapest Item on the Menu")
        println("0. Exit")

    } // end displayMenu


    def getResponse(text: String): String = {

        println(text)
        print("-->")
        val response = scala.io.StdIn.readLine()
        
        return response

    } // end getResponse


    def delay(): Unit = {
        Thread.sleep(2000)
    }


} // end object Main



case class MenuItem(item: String, itemType: String, itemPrice: Double)


class CoffeeShop(shopName: String, shopMenu: ArrayBuffer[MenuItem] = ArrayBuffer[MenuItem](), shopOrders: ArrayBuffer[MenuItem] = ArrayBuffer[MenuItem]()){

    var name: String = shopName

    var menu: ArrayBuffer[MenuItem] = {

        if (shopMenu.length > 0) {
            shopMenu
        } else {
            ArrayBuffer(
                MenuItem("Caffe Americano", "Drink", 4.99),
                MenuItem("Cappuccino", "Drink", 5.49),
                MenuItem("Chai Latte", "Drink", 5.99),
                MenuItem("Caramel Latte", "Drink", 6.49),
                MenuItem("Peppermint Mocha", "Drink", 7.49),
                MenuItem("Glazed Doughnut", "Food", 1.99),
                MenuItem("Coffee Cake", "Food", 3.99),
                MenuItem("Butter Croissant", "Food", 6.49),
                MenuItem("Avocado Spread", "Food", 10.99),
                MenuItem("Cheddar Bagel", "Food", 7.99)
            )
        } // end if
    } // end menu

    var orders: ArrayBuffer[MenuItem] = shopOrders


    /**
     * @method  addOrder
     * @desc    adds the name of the item to the end of the orders array if it exists on the menu. Otherwise, return "This item is currently unavailable!"
     * @params  itemName
     */
    def addOrder(itemName: String) : String = {

        for (i <- menu) {
            if (i.item == itemName) {
                orders += i
                return (s"${i.item} was added to our order list. It will be ready soon.")
            }
        } // end for

        return s"${itemName} is currently unavailable!"

    } // end addOrder


    /**
     * @method  fulfillOrder
     * @desc    if the orders array is not empty, return "The {item} is ready!". If the orders array is empty, return "All orders have been fulfilled!"
     * @params  None
     */
    def fulfillOrder(): String = {

        if (orders.length > 0) {
            val readyItem = orders.remove(0)
            return s"The ${readyItem.item} is ready!"
        } else {
            return "All orders have been fulfilled!"
        }

    } // end fulfillOrder


    /**
     * @method  listOrders
     * @desc    returns the list of orders taken, otherwise, an empty array.
     * @params  None
     */
    def listOrders(): ArrayBuffer[MenuItem] = {

        return orders

    } // end listOrders


    /**
     * @method  dueAmount
     * @desc    returns the total amount due for the orders taken.  
     * @params  None
     */
    def dueAmount(): Double = {

        var total: Double = 0
        orders.foreach(i => total += i.itemPrice)

        return total

    } // end dueAmount


    /**
     * @method  cheapestItem
     * @desc    returns the name of the cheapest item on the menu.
     * @params  
     */
    def cheapestItem(): String = {

        var currentCheapItem = menu(0).item
        var currentLowestPrice: Double = menu(0).itemPrice

        menu.foreach(i => {
            if (i.itemPrice < currentLowestPrice) {
                currentLowestPrice = i.itemPrice
                currentCheapItem = i.item
            }
        })

        return currentCheapItem

    } // end cheapestItem


    /**
     * @method  drinksOnly
     * @desc    returns only the item names of type drink from the menu.
     * @params  None
     */
    def drinksOnly(): ArrayBuffer[MenuItem] = {

        return menu.filter(i => i.itemType == "Drink")

    } // end drinksOnly


    /**
     * @method  foodOnly
     * @desc    returns only the item names of type food from the menu.
     * @params  None
     */
    def foodOnly(): ArrayBuffer[MenuItem] = {

        return menu.filter(i => i.itemType == "Food")

    } // end drinksOnly


} // end class
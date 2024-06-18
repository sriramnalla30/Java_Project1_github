Java Canteen Billing System
Overview
This Java application is a simple canteen billing system that reads items from a CSV file and allows the user to generate bills, access a manager interface, and reset the system. The application keeps track of the total revenue and the number of items sold.
Prerequisites
Java Development Kit (JDK) installed.
CSV file Items.csv with item details in the format item_code,item_name,item_cost.
Directory structure:
D:\gitcode\java\ASSIGNMENT\ for the Items.csv file.
D:\gitcode\java\bills\ for storing generated bills.
How It Works
Reading Items
The Read_items class reads the number of items and their details from the Items.csv file.
The items are stored in a 2D array items_array.
Generating Bills
The application prompts the user to enter item codes and quantities.
It calculates the total cost for each item and the entire bill.
The bill is saved as a text file in the D:\gitcode\java\bills\ directory with a timestamped filename.
Manager Interface
Displays the number of each item sold and the total revenue generated.
Reset Functionality
Resets the bill number, the count of items sold, and the total revenue.
Code Structure
Read_items: Handles reading the item details from the CSV file.
Items: Represents an item with item_code, item_name, and item_cost.
Bill: Represents a bill with item_code, item_name, item_cost, no_of_each_item, and items_cost.
Stock: Tracks the number of items sold and the total revenue.
ready: The main class that contains the logic for the user interface and bill generation.

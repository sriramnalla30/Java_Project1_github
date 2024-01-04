package ASSIGNMENT;

import java.util.*;
import java.io.*;
import java.text.*;

class Read_items{
    int no_of_items = 0;
    String [][] items_array;
    void read_no_of_items(){
        String Path = "D:\\gitcode\\java\\ASSIGNMENT\\Items.csv";
        String line = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(Path));
            while((line = br.readLine())!= null){
                no_of_items++;
            }
            items_array = new String[no_of_items][3];
        }catch(FileNotFoundException e){
            System.out.println("File Not Found.");
        }catch(IOException e){
            System.out.println("IO Exception.");
        }
    }
    void read_items(){
        String Path = "D:\\gitcode\\java\\ASSIGNMENT\\Items.csv";
        String line = "";
        int i = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(Path));
            while(i<no_of_items){
                line = br.readLine();
                items_array[i] = line.split(",");
                i++;
            }
        }catch(FileNotFoundException e){
            System.out.println("File Not Found.");
        }catch(IOException e){
            System.out.println("IO Exception.");
        }
    }
}

class Items{
    int item_code;
    String item_name;
    Float item_cost;
}

class Bill{
    int item_code;
    String item_name;
    Float item_cost;
    int no_of_each_item;
    Float items_cost;
}

class Stock{
    int item_code;
    String item_name;
    int no_of_each_item;
    static Float total_revenue=0.0f;
}

public class ready {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Read_items ri = new Read_items();
        ri.read_no_of_items();
        ri.read_items();
        Items[] i = new Items[ri.no_of_items];
        Stock[] s = new Stock[ri.no_of_items];
        for (int j = 0; j < ri.no_of_items; j++) {
            i[j] = new Items();
            s[j] = new Stock();
        }
        int j = 0;
        for(j=0;j<ri.no_of_items;j++){
            i[j].item_code = Integer.parseInt(ri.items_array[j][0]);
            i[j].item_name = ri.items_array[j][1];
            i[j].item_cost = Float.parseFloat(ri.items_array[j][2]);
            s[j].item_code = Integer.parseInt(ri.items_array[j][0]);
            s[j].item_name = ri.items_array[j][1];
        }
        int exit=0;
        int billno = 0;
        while(exit == 0){
            int choice;
            System.out.println("1) Take a Bill.");
            System.out.println("2) Manager Interface.");
            System.out.println("3) Reset.");
            System.out.println("4) Exit.");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1:
                    int no_of_items_in_a_bill = 0;
                    Float this_bill_amount=0.0f;
                    Bill[] b = new Bill[100];
                    for(j=0;j<100;j++){
                        b[j] = new Bill();
                    }
                    while (true) {
                        int itemcode,noofitems;
                        System.out.print("Enter item code or 'N' to finish order: ");
                        String input = sc.nextLine();
                        if(input.equalsIgnoreCase("N")) {
                            break;
                        }
                        itemcode = Integer.parseInt(input);
                        System.out.print("Enter number of items: ");
                        noofitems = Integer.parseInt(sc.nextLine());
                        for(int k=0;k<ri.no_of_items;k++){
                            if(itemcode == i[k].item_code){
                                b[no_of_items_in_a_bill].item_code = i[k].item_code;
                                b[no_of_items_in_a_bill].item_name = i[k].item_name;
                                b[no_of_items_in_a_bill].item_cost = i[k].item_cost;
                                b[no_of_items_in_a_bill].no_of_each_item = noofitems;
                                s[itemcode].no_of_each_item += noofitems;
                                b[no_of_items_in_a_bill].items_cost = (b[no_of_items_in_a_bill].item_cost)*(b[no_of_items_in_a_bill].no_of_each_item);
                                this_bill_amount += b[no_of_items_in_a_bill].items_cost;
                                Stock.total_revenue += b[no_of_items_in_a_bill].items_cost;
                                no_of_items_in_a_bill++;
                            }
                            
                        }
                    }
                    billno++;
                    String filePath = "D:\\gitcode\\java\\bills\\";
                    Date date = new Date();
                    DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
                    String formattedDate = dateFormat.format(date);
                    try (PrintWriter writer = new PrintWriter(filePath + "Bill_" + formattedDate + ".txt", "UTF-8")) {
                        writer.println("-------Bill--------");
                        writer.println("------Canteen------");
                        writer.println("Date_Time(DDMMYYYY_HHMMSS): " + formattedDate);
                        writer.println("Bill Number: " + billno);
                        for (int k = 0; k < no_of_items_in_a_bill; k++) {
                            writer.println("SNo: " + (k + 1) + ", item: " + b[k].item_name + ", no: " + b[k].no_of_each_item + ", cost: " + b[k].items_cost);
                        }
                        writer.println("");
                        writer.println("Total Bill Amount: " + this_bill_amount);
                        writer.println("GST(5%): " + (0.05 * this_bill_amount));
                        writer.println("Final Amount: " + (1.05 * this_bill_amount));
                        writer.println("-------ThankYou--------");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Bill Successfully generated with file name: Bill_"+formattedDate+"under the Bills folder with path: "+filePath);
                    break;
                case 2:
                    System.out.println("------Manager Interface-------");
                    for(int k=0;k<ri.no_of_items;k++){
                        System.out.println("SNo: "+(k+1)+", item: "+s[k].item_name+", no_of_items_sold: "+s[k].no_of_each_item);
                    }
                    System.out.println(" ");
                    System.out.println("Total Revenue: "+Stock.total_revenue);
                    System.out.println("-------ThankYou--------");
                    System.out.println(" ");
                    break;
                case 3:
                    billno = 0;
                    for(int k=0;k<ri.no_of_items;k++){
                            s[k].no_of_each_item=0;
                    }
                    Stock.total_revenue = 0.0f;
                    System.out.println("Interface Reset Successful.");
                    break;
                case 4:
                    exit = 1;
                    break;
                default:
                    System.out.println("Please enter a valid choice.");
            }
        }
    }
    
}
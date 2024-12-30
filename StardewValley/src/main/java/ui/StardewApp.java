package ui;
import controller.StardewController;
import exceptions.InvalidDaysGrowthException;
import exceptions.InvalidIntInputException;
import exceptions.FailedSearchException;

import java.util.Scanner;

public class StardewApp {

    private Scanner sk;
    private StardewController controller;

    public StardewApp(){
        this.sk = new Scanner(System.in);
        this.controller = new StardewController();
    }

    public static void main(String[] args) {

        StardewApp exec = new StardewApp();

        exec.controller.load();

        boolean flag = true;

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Welcome to Stardew Valley System");
        System.out.println("-------------------------------------------------------------------------------");

        while (flag) {
            int option = exec.menu(); //Show options

            switch (option) {
                case 1:
                    exec.createChest();
                    break;
                case 2:
                    exec.createCrop();
                    break;
                case 3:
                    exec.searchCropInChest();
                    break;
                case 4:
                    System.out.println("Exiting Program...");
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        //Gson glitch. Lets save infinite chests only with one type of crop.
        exec.controller.save();
        exec.controller.deleteAll();
    }

    //Methods -----------------------------------------------------------------------------------------------------

    public int menu(){
        System.out.println("----------------------------- Main Menu ---------------------------------------");
        System.out.println("1. Create a chest");
        System.out.println("2. Create a crop");
        System.out.println("3. Search crops in chests");
        System.out.println("4. Exit");
        System.out.print("Enter one option: ");

        int option = sk.nextInt();
        sk.nextLine();
        return option;
    }

    public void createChest(){
        System.out.println(controller.displayChestClassif());
        System.out.print("Enter one option: ");
        int intClassif = sk.nextInt();
        sk.nextLine();

        System.out.println("Write down the chest's name: ");
        String chestName = sk.nextLine();

        try{
            System.out.println(controller.createChest(chestName, intClassif));
        } catch (InvalidIntInputException e){
            System.out.println(e.getMessage());
        }

    }

    public void createCrop(){

        if(controller.oneMinChest()){
            System.out.println("Currently is " + controller.getSeason() + "! So you can only create the following crops:");
            System.out.println("\t1. " + controller. getSeason() + " Crops\n\t2. Non Seasonal Crops");
            System.out.print("Enter one option: ");

            try{

                int intCropType = sk.nextInt();
                sk.nextLine();
                System.out.println(controller.displayCropNames(intCropType));
                System.out.print("Enter one option: ");
                int intName = sk.nextInt();
                sk.nextLine();

                System.out.print("Enter the days of growth (1 - 28): ");
                int daysGrowth = sk.nextInt();
                sk.nextLine();



                System.out.println(controller.createCrop(intCropType, intName, daysGrowth));

                System.out.println("Now the crop must be saved: ");

                System.out.println(controller.displayAvailableChests());
                System.out.print("Please enter one of the options: ");
                int intChest = sk.nextInt();
                sk.nextLine();

                if(controller.isEmptyChest(intChest-1)){
                    System.out.println(controller.displayChestOrderOrOrganizationTypes(1));
                    System.out.print("Please enter one option: ");
                    int intOrder = sk.nextInt();
                    sk.nextLine();

                    System.out.println(controller.displayChestOrderOrOrganizationTypes(2));
                    System.out.print("Please enter one option: ");
                    int intOrganization = sk.nextInt();
                    sk.nextLine();

                    System.out.println(controller.addCropToChest(intChest, intOrder, intOrganization));
                } else {
                    System.out.println(controller.addCropToChest(intChest, -1, -1));
                }

            } catch (InvalidIntInputException | InvalidDaysGrowthException e){
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("There must be at least one created chest to create a Crop.");
        }


    }

    public void searchCropInChest() {
        if(controller.oneMinChest()){
            System.out.println(controller.displayAvailableChests());
            System.out.print("Enter one of the displayed options please: ");
            int intChest = sk.nextInt();
            sk.nextLine();

            System.out.print("Enter the name of the crop to search: ");
            String cropName = sk.nextLine();

            try {
                System.out.println(controller.searchCrop(intChest, cropName));
            } catch (FailedSearchException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("There must be at least one chest in order to search something.");
        }

    }


}

package controller;

import Structures.LinkedListChest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.google.gson.TypeAdapterFactory;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;


import exceptions.*;
import model.*;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalTime;

public class StardewController {
    //Attributes
    private String season;

    private final String[] SEASONS = {"Spring", "Summer", "Autumn", "Winter"};


    //Associations
    private LinkedListChest chests;
    private Crop lastCrop;

    //Attributes for serialization
    private File data;
    private File listJson;
    private Gson gson;

    //Methods

    //METHODS TO CREATE A CHEST

    public String displayChestClassif(){
        String message = "Available types of chest: ";
        String[] chestClassif = CropType.getCropTypes();

        for(int n =0; n<chestClassif.length; n++){
            message += String.format("\n\t%d: %s", (n+1), chestClassif[n]);
        }

        return message;
    }

    public String createChest(String name, int intClassification) throws InvalidIntInputException {
        String message = "";

            try{
                chests.add(new Chest(name, intClassification));
                message = "Chest successfully created.";
            } catch (InvalidIntInputException e) {
                message = e.getMessage();
            }

        return message;
    }


    //METHODS FOR CREATING A CROP
    public String displayCropNames(int intCropType) throws InvalidIntInputException {
        String message = "";

        switch(intCropType) {
            case 1:
                switch(season) {
                    case "Spring":
                        message = SpringCrop.getStringCropNames();
                        break;
                    case "Summer":
                        message = SummerCrop.getStringCropNames();
                        break;
                    case "Autumn":
                        message = AutumnCrop.getStringCropNames();
                        break;
                    case "Winter":
                        message = WinterCrop.getStringCropNames();
                        break;
                }
                break;
            case 2:
                message = NonSeasonalCrop.getStringNonSeasonalNames();
                break;
            default:
                throw new InvalidIntInputException("Please select a number between 1 and 2.");
        }
        return message;
    }

    public String createCrop(int intCropType, int intName, int daysGrowth) throws InvalidIntInputException, InvalidDaysGrowthException{
        String message = "";

        if(oneMinChest()){
                switch(intCropType) {
                    case 1:
                        switch(season) {
                            case "Spring":
                                lastCrop = new SpringCrop(intName, daysGrowth);
                                break;
                            case "Summer":
                                lastCrop = new SummerCrop(intName, daysGrowth);
                                break;
                            case "Autumn":
                                lastCrop = new AutumnCrop(intName, daysGrowth);
                                break;
                            case "Winter":
                                lastCrop = new WinterCrop(intName, daysGrowth);
                                break;
                        }
                        break;
                    case 2:
                        lastCrop = new NonSeasonalCrop(intName, daysGrowth);
                        break;
                    default:
                        throw new InvalidIntInputException("Please select a number between 1 and 2.");
                }
                message = "Crop successfully created.";
        } else {
            message = "There must be at least one created chest to create a Crop.";
        }

        return message;
    }

    //METHODS TO ADD A CROP TO A CHEST

    public boolean oneMinChest(){
        return chests.getChestCounter() != -1;
    }

    public boolean oneMinCrop(){
        return lastCrop != null;
    }

    public String displayAvailableChests(){
        String message = "Available chests: ";

        for(int n = 0; n <= chests.getChestCounter(); n++){
            try{
                message += String.format("\n\t%d: %s", (n+1), chests.getChestPos(n).getChest().getName());
            } catch (InvalidIntInputException e) {
                //Chest counter makes sure it's the right amount of loops
            }

        }

        return message;
    }

    public String displayChestOrderOrOrganizationTypes(int intType){
        String message = "Available order types for the chest: ";

        String[] types = OrderType.getOrderTypes();

        if(intType == 2){
            message = "Available organization types for the chest";
            types = OrganizationType.getOrganizationTypes();
        }

        for(int n = 0; n< types.length; n++){
            message += String.format("\n\t%d: %s", (n+1), types[n]);
        }

        return message;
    }

    public boolean isEmptyChest(int intChest) throws InvalidIntInputException {
        return chests.getChestPos(intChest).getChest().getSlots().getSlotCounter()==-1;
    }

    public void compareChestCropTypes(Chest chest) throws CropChestMismatchException{
        switch(chest.getClassification()) {
            case CROP:
                //Any type can be added
                break;
            case SPRING:
                if(!(lastCrop instanceof SpringCrop)){
                    throw new CropChestMismatchException("The chest only supports Spring crops.");
                }
                break;
            case SUMMER:
                if(!(lastCrop instanceof SummerCrop)){
                    throw new CropChestMismatchException("The chest only supports Summer crops.");
                }
                break;
            case AUTUMN:
                if(!(lastCrop instanceof AutumnCrop)){
                    throw new CropChestMismatchException("The chest only supports Autumn crops.");
                }
                break;
            case WINTER:
                if(!(lastCrop instanceof WinterCrop)){
                    throw new CropChestMismatchException("The chest only supports Winter crops.");
                }
                break;
            case NONSEASONAL:
                if(!(lastCrop instanceof NonSeasonalCrop)){
                    throw new CropChestMismatchException("The chest only supports Non-Seasonal crops.");
                }
                break;
        }
    }

    public String addCropToChest(int intChest, int intOrder, int intOrganization){
        String message = "";

        if(oneMinChest()){
            if(oneMinCrop()){
                //Phase the list to the actual order of the indexes
                intChest--;

                try{
                    Chest temp = chests.getChestPos(intChest).getChest();
                    if(temp.getOrder() == null){
                        compareChestCropTypes(temp);
                        temp.setOrder(intOrder);
                        temp.setOrganization(intOrganization);
                    }
                    try{
                        message = temp.addCrop(lastCrop);
                    } catch (FullChestException e){
                        message = e.getMessage();
                    }

                } catch (CropChestMismatchException | InvalidIntInputException e){
                    message = e.getMessage();
                }

            } else {
                message = "There must be at least one created crop to be added to the chest.";
            }
        } else {
            message = "There must be at least one created chest to add a crop it.";
        }

        return message;
    }

    //Methods to search a crop in a chest
    public boolean oneCropInChest(int intChest) throws InvalidIntInputException {
        return chests.getChestPos(intChest).getChest().getSlots().getFirst() != null;
    }

    public String searchCrop(int intChest, String cropName) throws FailedSearchException {
        String message = "There must be at least one chest to search for a crop.";

        Chest selectedChest = null;

        if(oneMinChest()){
            try{
                if(oneCropInChest(intChest-1)){
                    selectedChest = chests.getChestPos(intChest-1).getChest();
                    selectedChest.sortChest();
                    message = selectedChest.searchCrop(cropName);
                } else {
                    message = "There must be at least one crop in the chest to search for it.";
                }
            } catch(InvalidIntInputException | NullPointerException e){
                message = e.getMessage();
            }
        }

        return message;
    }

    //To save the information added in a Json
    public void createResources() throws IOException {
        if(!data.exists()){
            data.mkdir();

            if(!listJson.exists()){
                listJson.createNewFile();
            }
        }
    }

    public void save() {
        // Convert the chests object into a JSON string
        String dataJson = gson.toJson(chests);

        try {
            // Ensure the resources (file) are created if not already
            createResources();

            // Write the JSON string into the file
            FileOutputStream fos = new FileOutputStream(listJson);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(dataJson);  // Write the serialized JSON data
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

//    public void save() {
//        // Use the gson instance with the crop adapter
//        String dataJson = gson.toJson(chests);
//
//        try {
//            createResources();
//            FileOutputStream fos = new FileOutputStream(this.listJson);
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
//            writer.write(dataJson);
//            writer.flush();
//            writer.close();
//        } catch (FileNotFoundException e) {
//            System.err.println(e.getMessage());
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
//    }

    //To load the information from a Json file

    public void load() {
        try {
            FileInputStream fis = new FileInputStream(this.listJson);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder data = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                data.append(line);
            }

            reader.close();

            // Use gson with the adapter for Crop
            Type listType = new TypeToken<LinkedListChest>() {}.getType();
            chests = gson.fromJson(data.toString(), listType);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void load(){
//        Gson gson = new Gson();
//
//        try {
//            //To link or connect the information with the object
//            FileInputStream fis = new FileInputStream(this.listJson);
//
//            //Create a reader that is linked to the InputStream
//            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
//
//            //To read the information
//            String line = "";
//            String data = "";
//
//            while((line = reader.readLine()) != null) {
//                data += line;
//            }
//
//            reader.close();
//
//            Type listType = new TypeToken<LinkedListChest>(){}.getType();
//
//            chests = gson.fromJson(data, listType);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    //To delete every object from the model
    public void deleteAll(){
        this.chests = null;
    }

    //To Update the season
    public void updateSeasonBasedOnTime() {
        int minute = LocalTime.now().getMinute();
        int index = minute % SEASONS.length;
        season = SEASONS[index];
    }

    //Constructor
    public StardewController() {
        this.chests = new LinkedListChest();
        updateSeasonBasedOnTime();
        this.data = new File("D:\\AAUniversity\\Semestre 9\\APO 2\\tareaintegradora1-20242-actualizado-livies\\StardewValley\\src\\main\\data");
        this.listJson = new File(data.getAbsolutePath() + "\\Chests.json");
        // Create a RuntimeTypeAdapterFactory for Crop
        RuntimeTypeAdapterFactory<Crop> cropAdapterFactory = RuntimeTypeAdapterFactory
                .of(Crop.class, "type")  // This "type" is the discriminator field that will be added to the JSON
                .registerSubtype(SpringCrop.class, "SpringCrop")
                .registerSubtype(SummerCrop.class, "SummerCrop")
                .registerSubtype(AutumnCrop.class, "AutumnCrop")
                .registerSubtype(WinterCrop.class, "WinterCrop")
                .registerSubtype(NonSeasonalCrop.class, "NonSeasonalCrop");

        // Register the adapter in Gson
        this.gson = new GsonBuilder()
                .registerTypeAdapterFactory(cropAdapterFactory)
                .create();
    }

    //Getter
    public LinkedListChest getChests() {
        return chests;
    }

    public String getSeason() {
        updateSeasonBasedOnTime();
        return season;
    }

    public Crop getLastCrop() {
        return lastCrop;
    }

}

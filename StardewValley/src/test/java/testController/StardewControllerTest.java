package testController;

import controller.StardewController;
import exceptions.FailedSearchException;
import exceptions.InvalidDaysGrowthException;
import exceptions.InvalidIntInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StardewControllerTest {
    //Attributes

    //Associations
    private StardewController controller;

    //Setups
    private void defaultSetup(){
        this.controller = new StardewController();
    }

    private void oneChestSetup(){
        this.controller = new StardewController();

        try{
            controller.createChest("chest1", 1);
        } catch(InvalidIntInputException e){
            System.err.println(e.getMessage());
        }

    }

    private void oneCropSetup(){
        this.controller = new StardewController();
        try{
            controller.createChest("chest1", 1);
            controller.createCrop(2,1,2);
        } catch(InvalidIntInputException | InvalidDaysGrowthException e){
            //Not happening
        }
        controller.addCropToChest(1,1,1);
    }

    private void threeCropSetup(){
        this.controller = new StardewController();
        try{
            controller.createChest("chest1", 1);
            controller.createCrop(1,1,2);
            controller.addCropToChest(1,1,1);
            controller.createCrop(2,1,14);
            controller.addCropToChest(1,1,1);
            controller.createCrop(2,2,100);
            controller.addCropToChest(1,1,1);
        } catch(InvalidIntInputException | InvalidDaysGrowthException e){
            //Not happening
        }
    }

    //Tests
    @Test
    public void testCreateCropChest(){
        //Init
        defaultSetup();

        //Act
        String message = "";
        try{
            message = controller.createChest("chest1", 1);
        } catch(InvalidIntInputException e){
            System.err.println(e.getMessage());
        }

        //Assert
        assertEquals("Chest successfully created.", message);
        assertNotNull(controller.getChests().getFirst());
    }

    @Test
    public void testCreateTwoChests() {
        //Init
        oneChestSetup();

        //Act
        try{
            controller.createChest("chest2", 2);
        } catch(InvalidIntInputException e){
            System.err.println(e.getMessage());
        }

        //Assert
        assertNotNull(controller.getChests().getFirst().getNext());
        assertEquals("chest1", controller.getChests().getFirst().getChest().getName());
        assertEquals("chest2", controller.getChests().getFirst().getNext().getChest().getName());
    }

    @Test
    public void testCreateErrorChest(){
        //Init
        defaultSetup();

        //Act
        String message = "";

        try {
            message = controller.createChest("chest99", 99);
        } catch (InvalidIntInputException e) {

        }

        //Assert
        assertEquals("Please enter a number from the provided list of order types.", message);
    }

    @Test
    public void testCreate01Chest(){
        //Init
        defaultSetup();

        //Act
        String message = "";

        try {
            message = controller.createChest("Chest01", 01);
        } catch (InvalidIntInputException e) {

        }

        //Assert
        assertEquals("Chest successfully created.", message);
    }


    @Test
    public void testCreateOneCrop(){
        //Init
        oneChestSetup();

        //Act
        String message = "";

        try{
            message = controller.createCrop(1,1,1);
        } catch(InvalidIntInputException | InvalidDaysGrowthException e){

        }

        //Assert
        assertEquals("Crop successfully created.",message);
        assertNotNull(controller.getLastCrop());
    }

    @Test
    public void testCreateAncientCrop(){
        //Init
        oneChestSetup();

        //Act
        String message = "";

        try{
            message = controller.createCrop(2,2,100);
        } catch(InvalidIntInputException |InvalidDaysGrowthException e){

        }

        //Assert
        assertEquals( "Crop successfully created.",message);
        assertNotNull(controller.getLastCrop());
    }

    @Test
    public void testCreateNonAncient100Crop(){
        //Init
        oneChestSetup();

        //Act
        String message = "";

        try{
            controller.createCrop(2,1,100);
        } catch(InvalidIntInputException | InvalidDaysGrowthException e){
            message = e.getMessage();
        }

        //Assert
        assertEquals("The days of growth must be between 1 and 28.", message);
    }

    @Test
    public void testCreateCropNoChest(){
        //Init
        defaultSetup();

        //Act
        String message = "";

        try{
            message = controller.createCrop(1,1,1);
        } catch(InvalidIntInputException |InvalidDaysGrowthException e){

        }

        //Assert
        assertEquals("There must be at least one created chest to create a Crop.", message);
    }

    @Test
    public void testAddCropToChestNoChest(){
        //Init
        defaultSetup();

        //Act

        String message = controller.addCropToChest(1,1,1);


        //Assert
        assertEquals("There must be at least one created chest to add a crop it.", message);
    }

    @Test
    public void testAddCropToChestNoCrop(){
        //Init
        oneChestSetup();

        //Act
        String message = controller.addCropToChest(1,1,1);


        //Assert
        assertEquals("There must be at least one created crop to be added to the chest.", message);
    }


    @Test
    public void testAddCropChest() {
        //Init
        oneChestSetup();

        //Act
        try{
            controller.createCrop(1,1,1);
        } catch(InvalidIntInputException |InvalidDaysGrowthException e){
            //Wont happen
        }

        String message = controller.addCropToChest(1,1, 1);

        //Assert
        assertEquals("The crop has been added successfully.", message);
        assertNotNull(controller.getChests().getFirst().getChest().getSlots().getFirst().getStack().getFirst());
    }

    @Test
    public void testAddNonExistingChest() {
        // Init
        oneCropSetup();

        // Act
        String message = controller.addCropToChest(2,1,1);


        // Assert
        assertEquals("Invalid chest position: 2.", message);
    }

    @Test
    public void testSearchNoChest() {
        //Init
        defaultSetup();

        //Act
        String message = "";

        try{
            message = controller.searchCrop(1, "Poppy");
        } catch (FailedSearchException e){

        }

        //Assert
        assertEquals("There must be at least one chest to search for a crop.", message);
    }

    @Test
    public void testSearchOneCrop(){
        //Init
        oneCropSetup();

        //Act
        String message = "";

        try{
            message = controller.searchCrop(1,"Fiber");
        } catch (FailedSearchException e){
            message = e.getMessage();
        }


        //Assert
        assertEquals("\n" + "\tCrop Type: Non-Seasonal Crop\n" + "\tDays of Growth: 2\n" + "\tName: Fiber", message);
    }

    @Test
    public void testSearchMiddleCrop() {
        //Init
        threeCropSetup();

        //Act
        String message = "";
        try{
            message = controller.searchCrop(1, "Fiber");
        } catch (FailedSearchException e){
            message = e.getMessage();
        }

        //Assert
        assertEquals("\n" + "\tCrop Type: Non-Seasonal Crop\n" + "\tDays of Growth: 14\n" + "\tName: Fiber", message);
    }

    @Test
    public void testSearchLastCrop(){
        //Init
        threeCropSetup();

        //Act
        String message = "";
        try{
            message = controller.searchCrop(1, "Ancient Fruit");
        } catch (FailedSearchException e){
            message = e.getMessage();
        }

        //Assert
        assertEquals("\n" + "\tCrop Type: Non-Seasonal Crop\n" + "\tDays of Growth: 100\n" +
                "\tName: Ancient Fruit", message);
    }

    @Test
    public void testCropMismatchException(){
        //Init
        defaultSetup();

        //Act
        String message = "";

        try{
            controller.createChest("SummerChest", 3);
            controller.createCrop(2,1,1);
            message = controller.addCropToChest(1,1,1);
        } catch(InvalidIntInputException | InvalidDaysGrowthException e){
            //Doesn't happen
        }


        //Assert
        assertEquals("The chest only supports Summer crops.", message);
    }

    @Test
    public void testFailedSearchException(){
        //Init
        oneCropSetup();

        //Act
        String message = "";
        try{
            controller.searchCrop(1, "Garlic");
        } catch (FailedSearchException e){
            message = e.getMessage();
        }

        //Assert
        assertEquals("The searched crop wasn't found in the selected chest.", message);
    }

    @Test
    public void testInvalidDaysGrowthException(){
        //Init
        oneChestSetup();

        //Act
        String message = "";
        try{
            controller.createCrop(2,1, 200);
        } catch(InvalidIntInputException | InvalidDaysGrowthException e){
            message = e.getMessage();
        }

        //Assert
        assertEquals("The days of growth must be between 1 and 28.", message);
    }

    @Test
    public void testInvalidIntInputException(){
        //Init
        oneChestSetup();

        //Act
        String message1 = "";

        try{
            message1 = controller.createChest("1",-1);
        } catch(InvalidIntInputException e){

        }

        String message2 = "";

        try{
            controller.createCrop(99,1,1);
        } catch(InvalidIntInputException |InvalidDaysGrowthException e){
            message2 = e.getMessage();
        }

        //Assert
        assertEquals("Please enter a number from the provided list of order types.", message1);
        assertEquals("Please select a number between 1 and 2.", message2);
    }

    @Test
    public void testFullChestException() {
        //Init
        oneCropSetup();

        //Act

        for(int n = 1; n <= 1250; n++){
            controller.addCropToChest(1,1,1);
        }

        String message = controller.addCropToChest(1,1,1);

        //Assert
        assertEquals("The chest is full, it can't save anything else.", message);
    }

}
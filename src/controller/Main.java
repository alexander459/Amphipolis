package controller;
import model.exceptions.TileDoesNotExistException;

public class Main {
    public static void main(String[] args) {
        try {
            new Controller();
        }catch (TileDoesNotExistException e){
            System.out.println("failed");
            return;
        }
    }
}

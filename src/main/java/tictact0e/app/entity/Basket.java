package tictact0e.app.entity;

import java.util.ArrayList;

public class Basket {
    private static Basket ourInstance = new Basket();

    private ArrayList<Book> booksBasket = new ArrayList<>();

    public static Basket getInstance() {
        return ourInstance;
    }

    private Basket() {
    }

    public ArrayList<Book> getBooksBasket() {
        return booksBasket;
    }
}

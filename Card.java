public class Card {
    private Value values; 
    private Suit suits;

    public int getValue(){
        return this.values.getValues();
    }

    public Suit getSuit(){
        return this.suits;
    }

    public String toString(){
        String card = this.values + " of " + this.suits;
        return card;
    }

    public Card(Value values, Suit suits){
        this.values = values;
        this.suits = suits;
    }
}
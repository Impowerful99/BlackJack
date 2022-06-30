import java.util.Random;
public class Deck {
    public DynamicCardArray fullDECK;
    public Random random;

    Random rand = new Random();

    public Deck(){
        this.fullDECK = new DynamicCardArray();

        for (Suit suits : Suit.values()) {
            for (Value values : Value.values()) {
                Card card = new Card(values,suits);
                fullDECK.add(card);
            }
        }
    }

    public String toString(){
        String Dprint = "The deck holds the cards in this order: ";

        for(int i = 0; i<this.fullDECK.length(); i++){
            if(i == this.fullDECK.length()){
                Dprint += fullDECK.get(i);
            }
            Dprint += fullDECK.get(i) + ", ";
        }

        return Dprint;
    }

    public void Shuffle(){
        DynamicCardArray Replace = new DynamicCardArray();
        for(int i = 0; i<this.fullDECK.length(); i++){
            int randomCard = rand.nextInt(this.fullDECK.length());
            Card tempHold = this.fullDECK.get(randomCard);
            this.fullDECK.remove(randomCard);
            Replace.add(tempHold);
        }  
        for(int i = 0; i< Replace.length();i++){
        Card inserts = Replace.get(i);
        this.fullDECK.add(inserts);
        }
    }
    
    public DynamicCardArray getDeck(){
        return fullDECK;
    }
    
    public Card getCardFromTop(){
       return this.fullDECK.getCardFromTop();
    }
}
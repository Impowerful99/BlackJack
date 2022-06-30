public class Dealer {
    
    private DynamicCardArray hand;

    public Dealer(){
        this.hand = new DynamicCardArray();
    }

    public String toString(){
        String phand = "The Dealer's hand contained: ";
        
        for(int i = 0; i<this.hand.length(); i++){
        phand += this.hand.get(i) + ", ";   
        }

        return phand;
    }

    public int CalculateValue(){
        int sum = 0;

        for(int i = 0; i<this.hand.length(); i++){
            int CardValue = this.hand.get(i).getValue();
            if(CardValue == 1){
                if(sum <= 10){
                    sum += 11;
                }
                else{
                    sum += 1;
                }
            }
            else{
                sum += CardValue;
            }
        }
        return sum;
    }

    public void Draw(Card c){
        this.hand.add(c);
    }

    public boolean isDHandBust(int playerBet, int totalMoney){
        boolean Result = false;
        if(CalculateValue() > 21){
            System.out.println("You won the gamble Dealer hand is a bust.");
            playerBet = playerBet*2;
            totalMoney += playerBet;
            System.out.println("You have doubled your profits and your new total amount of money is: " + totalMoney);
            Result = true;
        }
        /*else{
            System.out.println("The Dealer's hand is a: " + CalculateValue());
        }*/
        return Result;
    }

}

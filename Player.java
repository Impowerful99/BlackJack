public class Player {

    private DynamicCardArray hand;

    public Player(){
        this.hand = new DynamicCardArray();
    }

    public String toString(){
        String phand = "Your hand contains: ";
        
        for(int i = 0; i<this.hand.length(); i++){
        phand += this.hand.get(i) + ", ";   
        }

        return phand;
    }

    //It returns a number instead without a input and a switch method
   
    public int CalculateValue(String Pchoice){
        int sum = 0;

        for(int i = 0; i<this.hand.length(); i++){
            int CardValue = this.hand.get(i).getValue();
            if(CardValue == 1){
                switch(Pchoice){
                    case "One": 
                        sum++;
                        break;
                    case "Eleven":
                        sum += 11;
                        break;
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

    public Boolean isPHandBust(String Pchoice, int amountMoney){
        Boolean Result = false;
        if(CalculateValue(Pchoice) > 21){
            System.out.println(toString());
            System.out.println("Your hand is a bust you lost the gamble.");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(" [|*|]          Better Luck Next Time Please Come Again And Play Again          [|*|] ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("You have lost your bet and now your new total amount of money is: " + amountMoney);
            Result = true;
        }
        else{
            System.out.println("Your Total Sum is: " + CalculateValue(Pchoice));
        }
        return Result;
        
    }
}
public class Test{
	public static void main(String[]args){
		Dealer dealer = new  Dealer();
		Deck deck = new Deck();
		int totalMoney = 100;
		int playerBet = 1;
		int howMuchD = 0;
		
		
            deck.Shuffle();
            deck.Shuffle();
            deck.Shuffle();
            deck.Shuffle();
            deck.Shuffle();
		
		dealer.Draw(deck.getCardFromTop());
            dealer.Draw(deck.getCardFromTop());
		
		boolean isdealerTurn = false;
                    while (!isdealerTurn){
                            howMuchD = dealer.CalculateValue();

                        if(!(howMuchD >= 17)){
							System.out.println(howMuchD);
                            dealer.Draw(deck.getCardFromTop());
                            isdealerTurn = dealer.isDHandBust(playerBet, totalMoney);
							System.out.println("Yes");
							System.out.println(dealer.CalculateValue());
                            continue;
                        }
                        else{
							System.out.println("No");
							System.out.println(howMuchD);
                            break;
                        }
                    }
	}
}
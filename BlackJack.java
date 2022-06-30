import java.util.Scanner;
public class BlackJack {
    public static void main(String[] args) {
        Scanner Scan = new Scanner(System.in);
        Scanner intScan = new Scanner(System.in);
        String stringInput = "";
        int playerBet = 0;
        int totalMoney = 100;
        boolean notPlaying = false;
       
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(" [|*|]                      Welcome to the game of BLACKJACK                    [|*|] ");
        System.out.println(" [|*|] If you would like to read the Rules and the Values of Cards enter Y or N [|*|] ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        stringInput = Scan.nextLine();
        if(stringInput.equals("y") || stringInput.equals("Y")){
            System.out.println(rulesAndScoringSystem() + "\n");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(" [|*|]                           Please Enjoy The Game                          [|*|] ");
        System.out.println(" [|*|]                        MADE BY: YU HUA YANG 2133677                      [|*|] ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        //The Game
        while (!notPlaying) {
            
            Deck deck = new Deck();
        
            deck.Shuffle();
            deck.Shuffle();
            deck.Shuffle();
            deck.Shuffle();
            deck.Shuffle();

            Player player = new Player();
            Dealer dealer = new Dealer();

            boolean isPlayOver = false;

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(" [|*|]   How much would you like to bet out of the " + totalMoney + "$ you have input number   [|*|] ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            //Validation and taking input of amount for bet and subtracting from total Money
            playerBet = intScan.nextInt();
            if(playerBet > totalMoney){
                throw new IllegalArgumentException("You don't have the amount you are betting left.");
            }
            else{
                totalMoney -= playerBet;
            }

            //Informing of player money they bet and how much they have left to bet
            System.out.println("You have " + totalMoney + "$ left to bet");
            System.out.println("You have placed a " + playerBet + "$ bet");

            //Dealing Cards to both Player and Dealer before turn
            player.Draw(deck.getCardFromTop());
            player.Draw(deck.getCardFromTop());
            dealer.Draw(deck.getCardFromTop());
            dealer.Draw(deck.getCardFromTop());

            //initializing variables to check how much Dealer and Player has so can calculate who wins
            int howMuchD = 0;
            int howMuchP = 0;
            String ifAce = "";

            //Player turns followed by the dealers turn
            while (!isPlayOver) {              
                System.out.println(player.toString());
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(" [|*|]       Do you want to HIT or STAND enter H for HIT and S for STAND        [|*|] ");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                stringInput = Scan.nextLine();

                if(stringInput.equals("h") || stringInput.equals("H")){
                    player.Draw(deck.getCardFromTop());

                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(" [|*|]      If you have ACES enter One or Eleven for it to be either value      [|*|] ");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                    ifAce = Scan.nextLine();
                    isPlayOver = player.isPHandBust(ifAce, totalMoney);
                    howMuchP = player.CalculateValue(ifAce);

                    continue;
                }
                else if (stringInput.equals("s") || stringInput.equals("S")){
                    howMuchP = player.CalculateValue(ifAce);

                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(" [|*|]                Now it is the dealer's turn to hit or stand               [|*|] ");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                    boolean isdealerTurn = false;
                    while (!isdealerTurn){
                            howMuchD = dealer.CalculateValue();

                        if(howMuchD < 17){
                            dealer.Draw(deck.getCardFromTop());
                            isdealerTurn = dealer.isDHandBust(playerBet, totalMoney);
                            continue;
                        }
                        else{
                            break;
                        }
                    }
                }

                if(howMuchD > howMuchP && !(howMuchD > 21) && !(howMuchP > 21)){
                    System.out.println("You lost the Dealer had a bigger number then you, they had " + howMuchD + " you have " + howMuchP);
                    String lost = "Lost";
                    totalMoney = outcomeOfBet(lost, playerBet, totalMoney);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(" [|*|]          Better Luck Next Time Please Come Again And Play Again          [|*|] ");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    break;
                }
                else if(howMuchD < howMuchP && !(howMuchD > 21) && !(howMuchP > 21)){
                    System.out.println("You won the Dealer had a smaller number then you, you have " + howMuchP + " they had " + howMuchD);
                    String win = "Win";
                    totalMoney = outcomeOfBet(win, playerBet, totalMoney);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(" [|*|]       Congradualtions On Winning Please Come Again And Play Again        [|*|] ");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    break;
                }
                else if(howMuchD == howMuchP && !(howMuchD > 21) && !(howMuchP > 21)){
                    System.out.println("You won because the Casino's rules if the Dealer and the player have ");
                    System.out.println("the same number you win, you had " + howMuchP + " they had " + howMuchD);
                    String win = "Win";
                    totalMoney = outcomeOfBet(win, playerBet, totalMoney);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(" [|*|]       Congradualtions On Winning Please Come Again And Play Again        [|*|] ");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    break;
                }
            }

            notPlaying = isGameOver(totalMoney);

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(" [|*|]     Now That The Game Is Over You May Choose To Play Again or Leave      [|*|] ");
            System.out.println(" [|*|]                     Enter P for Play or L for Leave                      [|*|] ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            stringInput = Scan.nextLine();

            if(stringInput.equals("s") || stringInput.equals("S")){
                System.out.println("You have chosen to play another one best of luck to you");
                continue;
            }
            else if(stringInput.equals("l") || stringInput.equals("L")){
                System.out.println("You have chosen to leave have a good day");
                break;
            }
        }
    }
    public static String rulesAndScoringSystem() {
        StringBuilder builder = new StringBuilder();

        builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        builder.append("[|*|]                    Rules and Scoring System of BlackJack                   [|*|]\n");
        builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        builder.append("[[|]]                    Rules: You start with 100 dollars                       [[|]]\n");
        builder.append(" | |                     and you lose when you have no money                      | | \n");
        builder.append(" | |                     left or when you feel like leaving                       | | \n");
        builder.append(" | |                     the game as a whole. You will recieve                    | | \n");
        builder.append(" | |                     2 cards alongside the Dealer's 2 cards                   | | \n");
        builder.append(" | |                     and you have to have a sum of 21 or under.               | | \n");
        builder.append(" | |                     any higher will result in a bust and                     | | \n");
        builder.append(" | |                     if your number is less then the dealer                   | | \n");
        builder.append(" | |                     you will automatically lose your bet                     | | \n");
        builder.append(" | |                     because the dealer won. Though you                       | | \n");
        builder.append(" | |                     will get the option to either hit or stand               | | \n");
        builder.append("[[|]]                    or for the ace to be a 1 or a 11.                       [[|]]\n");
        builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        builder.append("[[|]]                    HIT: you will be dealt another card                     [[|]]\n");
        builder.append(" | |                     STAND: you hold position and let the                     | | \n");
        builder.append("[[|]]                    dealer choose if he hits or stands.                     [[|]]\n");
        builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        builder.append("[[|]]                               VALUES OF CARDS                              [[|]]\n");
        builder.append(" | |                     [ACE]-----------------------[1 or 11]                    | | \n");
        builder.append(" | |                     [TWO]-----------------------------[2]                    | | \n");
        builder.append(" | |                     [THREE]---------------------------[3]                    | | \n");
        builder.append(" | |                     [FOUR]----------------------------[4]                    | | \n");
        builder.append(" | |                     [FIFTH]---------------------------[5]                    | | \n");
        builder.append(" | |                     [SIX]-----------------------------[6]                    | | \n");
        builder.append(" | |                     [SEVEN]---------------------------[7]                    | | \n");
        builder.append(" | |                     [EIGHT]---------------------------[8]                    | | \n");
        builder.append(" | |                     [NINE]----------------------------[9]                    | | \n");
        builder.append(" | |                     [TEN]----------------------------[10]                    | | \n");
        builder.append(" | |                     [JACK]---------------------------[10]                    | | \n");
        builder.append(" | |                     [QUEEN]--------------------------[10]                    | | \n");
        builder.append("[[|]]                    [KING]---------------------------[10]                   [[|]]\n");
        builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        return builder.toString();
    }

    public static int outcomeOfBet(String outcome, int playerBet, int totalMoney){
        if(outcome.equals("Win")){
            playerBet = playerBet*2;
            totalMoney += playerBet;
            System.out.println("You have doubled your profits and your new total amount of money is: " + totalMoney);
        }
        else if(outcome.equals("Lost")){
            System.out.println("You have lost your bet and now your new total amount of money is: " + totalMoney);
        }
        return totalMoney; 
    }

    public static boolean isGameOver(int totalMoney){
        boolean isOver = false;
        
        if(totalMoney == 0){
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(" [|*|]         You no longer have money to bet please leave the table           [|*|] ");
            System.out.println(" [|*|]                    Come back when you have more money                    [|*|] ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            isOver = true;
        }
        return isOver;
    }
}   

//Fix that it updates your max amount of money
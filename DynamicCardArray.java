public class DynamicCardArray {
    private Card[] cards;
	private int next;

	public DynamicCardArray() {
		this.cards = new Card[200];
		this.next = 0;
	}
	public void add(Card s)
    {
        if(this.next >= this.cards.length){
            tooSmall();
        }
        this.cards[this.next] = s;
        this.next++;
    }
    public int length(){
        return this.next;
    }
    public boolean contains(Card s)
    {
        for(int i = 0; i < this.next; i++){
            if(this.cards[i] == (s))
            {
               return true; 
            }
        }
        return false;
    }
    public Card get(int i){
        if(i >= this.next)
        {
            throw new ArrayIndexOutOfBoundsException("no value");
        }
        return this.cards[i];
    }
    public void set(int i, Card newValue){
        this.cards[i] = newValue;
    }
    public void remove(int i){
        if(i > this.next){
            throw new ArrayIndexOutOfBoundsException("no value");
        }
        else{
            for(int j = i; j < this.next; j++){
            this.cards[j] = this.cards[j+1];
            }
            this.next--;
        }
    }
    public void insert(int i, Card inserted){
        if(i > this.next){
            throw new ArrayIndexOutOfBoundsException("no value");
        }
        else{
            for(int j = this.next-1; j >= i; j--){
                this.cards[j+1] = this.cards[j];
            }
            this.cards[i] = inserted;
            this.next++;
        }
    }
    private void tooSmall(){
            Card[] bigger = new Card[this.cards.length*2];
            for(int i=0; i > this.cards.length; i++){
                bigger[i] = this.cards[i];
            }
            this.cards = bigger;
    }  

    public Card getCardFromTop(){
        Card FirstCard = this.cards[0];
        remove(0); 
        return FirstCard;
    }
}
    



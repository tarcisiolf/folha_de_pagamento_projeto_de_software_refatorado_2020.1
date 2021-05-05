package union;

public class Syndicate {
   private int syndicateID;
   private Taxes taxe;

   public Syndicate(int syndicateID){

      this.syndicateID = syndicateID;
   }

   public void setSyndicateID(int syndicateID) {
      this.syndicateID = syndicateID;
   }

   public int getSyndicateID(){
      return syndicateID;
   }

   public void setTaxes(Taxes taxe) {
      this.taxe = taxe;
   }

   public Taxes getTaxes() {
      return taxe;
   }
}

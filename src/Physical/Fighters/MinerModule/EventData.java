/*    */ package Physical.Fighters.MinerModule;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ 
/*    */ public class EventData
/*    */ {
/*    */   public AbilityBase ab;
/*  8 */   public int parameter = 0;
/*    */   
/* 10 */   public EventData(AbilityBase ab) { this(ab, 0); }
/*    */   
/* 12 */   public EventData(AbilityBase ab, int parameter) { this.ab = ab;
/* 13 */     this.parameter = parameter;
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\MinerModule\EventData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
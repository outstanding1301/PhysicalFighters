/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import Physical.Fighters.PhysicalFighters;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*    */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*    */ 
/*    */ public class Anorexia extends AbilityBase
/*    */ {
/*    */   public Anorexia()
/*    */   {
/* 19 */     if (!PhysicalFighters.Specialability) {
/* 20 */       InitAbility("거식증", AbilityBase.Type.Passive_AutoMatic, AbilityBase.Rank.A, new String[] {
/* 21 */         "캐릭터의 배고픔이 꽉 찬 상태로 고정됩니다.", "모든 종류의 체력 회복량을 3배로 받습니다." });
/* 22 */       InitAbility(0, 0, true);
/* 23 */       EventManager.onFoodLevelChange.add(new EventData(this, 0));
/* 24 */       EventManager.onEntityRegainHealth.add(new EventData(this, 1));
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 30 */     Player p = null;
/* 31 */     switch (CustomData) {
/*    */     case 0: 
/* 33 */       FoodLevelChangeEvent Event0 = (FoodLevelChangeEvent)event;
/* 34 */       p = (Player)Event0.getEntity();
/* 35 */       if (PlayerCheck(p)) {
/* 36 */         return 0;
/*    */       }
/*    */       break;
/*    */     case 1: 
/* 40 */       EntityRegainHealthEvent Event1 = (EntityRegainHealthEvent)event;
/* 41 */       if (PlayerCheck(Event1.getEntity()))
/* 42 */         return 1;
/*    */       break;
/*    */     }
/* 45 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 50 */     switch (CustomData) {
/*    */     case 0: 
/* 52 */       FoodLevelChangeEvent Event0 = (FoodLevelChangeEvent)event;
/* 53 */       Player p = (Player)Event0.getEntity();
/* 54 */       p.setFoodLevel(20);
/* 55 */       p.setSaturation(0.0F);
/* 56 */       Event0.setCancelled(true);
/* 57 */       break;
/*    */     case 1: 
/* 59 */       EntityRegainHealthEvent Event1 = (EntityRegainHealthEvent)event;
/* 60 */       if (PhysicalFighters.NoFoodMode) {
/* 61 */         Event1.setAmount((int) (Event1.getAmount() * 3.0D));
/*    */       } else {
/* 63 */         Event1.setAmount((int) (Event1.getAmount() * 3.0D));
/*    */       }
/*    */       break;
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Anorexia.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
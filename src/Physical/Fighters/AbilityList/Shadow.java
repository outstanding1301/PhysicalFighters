/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import java.util.ArrayList;

import org.bukkit.entity.Damageable;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.event.entity.EntityTargetEvent;
/*    */ 
/*    */ public class Shadow extends Physical.Fighters.MainModule.AbilityBase
/*    */ {
/*    */   public Shadow()
/*    */   {
/* 14 */     InitAbility("그림자", Physical.Fighters.MainModule.AbilityBase.Type.Passive_AutoMatic, Physical.Fighters.MainModule.AbilityBase.Rank.C, new String[] {
/* 15 */       "몬스터가 절대로 공격을 하지 않습니다. 생명체로부터", "공격받을시 5% 확률로 데미지를 받지않고", 
/* 16 */       "체력을 3 회복합니다." });
/* 17 */     InitAbility(0, 0, true);
/* 18 */     EventManager.onEntityTarget.add(new Physical.Fighters.MinerModule.EventData(this, 0));
/* 19 */     EventManager.onEntityDamage.add(new Physical.Fighters.MinerModule.EventData(this, 1));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 24 */     switch (CustomData) {
/*    */     case 0: 
/* 26 */       EntityTargetEvent Event0 = (EntityTargetEvent)event;
/* 27 */       if (PlayerCheck(Event0.getTarget())) {
/* 28 */         return 0;
/*    */       }
/*    */       
/*    */       break;
/*    */     case 1: 
/* 33 */       EntityDamageEvent Event1 = (EntityDamageEvent)event;
/* 34 */       if ((PlayerCheck(Event1.getEntity())) && 
/* 35 */         (Event1.getCause() == org.bukkit.event.entity.EntityDamageEvent.DamageCause.ENTITY_ATTACK) && 
/* 36 */         (Math.random() <= 0.05D)) {
/* 37 */         return 1;
/*    */       }
/*    */       break;
/*    */     }
/*    */     
/* 42 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 47 */     switch (CustomData) {
/*    */     case 0: 
/* 49 */       EntityTargetEvent Event0 = (EntityTargetEvent)event;
/* 50 */       Event0.setTarget(null);
/* 51 */       Event0.setCancelled(true);
/* 52 */       break;
/*    */     case 1: 
/* 54 */       EntityDamageEvent Event1 = (EntityDamageEvent)event;
/* 55 */       Event1.setDamage(0);
/* 56 */       if (((Damageable)GetPlayer()).getHealth() <= 18.0D) {
/* 57 */         GetPlayer().setHealth(((Damageable)GetPlayer()).getHealth() + 3);
/*    */       }
/*    */       break;
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Shadow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
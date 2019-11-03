/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import Physical.Fighters.PhysicalFighters;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*    */ 
/*    */ public class Blaze extends AbilityBase
/*    */ {
/*    */   public Blaze()
/*    */   {
/* 19 */     if (!PhysicalFighters.Specialability) {
/* 20 */       InitAbility("블레이즈", AbilityBase.Type.Passive_AutoMatic, AbilityBase.Rank.C, new String[] {
/* 21 */         "용암과 불 데미지를 입지 않습니다.", "능력에서 파생되는 화염 데미지도 막습니다.", 
/* 22 */         "모든 종류의 폭발 데미지를 50%로 줄여 받습니다." });
/* 23 */       InitAbility(0, 0, true);
/* 24 */       EventManager.onEntityDamage.add(new EventData(this));
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 30 */     EntityDamageEvent Event = (EntityDamageEvent)event;
/* 31 */     if (PlayerCheck(Event.getEntity())) {
/* 32 */       if ((Event.getCause() == EntityDamageEvent.DamageCause.LAVA) || 
/* 33 */         (Event.getCause() == EntityDamageEvent.DamageCause.FIRE) || 
/* 34 */         (Event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK))
/* 35 */         return 0;
/* 36 */       if ((Event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) || 
/* 37 */         (Event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
/* 38 */         return 1;
/*    */       }
/*    */     }
/* 41 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 46 */     EntityDamageEvent Event = (EntityDamageEvent)event;
/* 47 */     switch (CustomData) {
/*    */     case 0: 
/* 49 */       Event.setCancelled(true);
/* 50 */       Event.getEntity().setFireTicks(0);
/* 51 */       break;
/*    */     case 1: 
/* 53 */       Event.setDamage((int) (Event.getDamage() / 2.0D));
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Blaze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*    */ 
/*    */ public class Zombie extends Physical.Fighters.MainModule.AbilityBase
/*    */ {
/*    */   public Zombie()
/*    */   {
/* 15 */     InitAbility("좀비", Physical.Fighters.MainModule.AbilityBase.Type.Passive_AutoMatic, 
/* 16 */       Physical.Fighters.MainModule.AbilityBase.Rank.B, new String[] { "모든 데미지의 반을 흡수합니다.", 
/* 17 */       "불공격의 데미지를 8배로 받습니다." });
/* 18 */     InitAbility(0, 0, true);
/* 19 */     EventManager.onEntityDamage.add(new Physical.Fighters.MinerModule.EventData(this));
/* 20 */     EventManager.onEntityDamageByEntity.add(new Physical.Fighters.MinerModule.EventData(this, 1));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData) {
/* 24 */     if (CustomData == 0) {
/* 25 */       EntityDamageEvent Event = (EntityDamageEvent)event;
/* 26 */       if (PlayerCheck(Event.getEntity())) {
/* 27 */         if ((Event.getCause() == EntityDamageEvent.DamageCause.LAVA) || 
/* 28 */           (Event.getCause() == EntityDamageEvent.DamageCause.FIRE) || 
/* 29 */           (Event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK))
/* 30 */           return 0;
/* 31 */         if ((Event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) || 
/* 32 */           (Event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION))
/* 33 */           return 1;
/* 34 */         if ((Event.getCause() == EntityDamageEvent.DamageCause.FALL) || 
/* 35 */           (Event.getCause() == EntityDamageEvent.DamageCause.POISON) || 
/* 36 */           (Event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE))
/* 37 */           return 2;
/*    */       }
/* 39 */     } else if (CustomData == 1) {
/* 40 */       EntityDamageByEntityEvent Event1 = (EntityDamageByEntityEvent)event;
/* 41 */       if ((PlayerCheck(Event1.getEntity())) && 
/* 42 */         (((HumanEntity)Event1.getDamager()).getItemInHand()
/* 43 */         .getType().getId() != Physical.Fighters.MinerModule.ACC.DefaultItem)) {
/* 44 */         if (((HumanEntity)Event1.getDamager()).getItemInHand()
/* 45 */           .getType().getId() != Material.GOLD_INGOT.getId()) {
/* 46 */           return 2;
/*    */         }
/*    */       }
/*    */     }
/* 50 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData) {
/* 54 */     switch (CustomData) {
/*    */     case 0: 
/* 56 */       EntityDamageEvent Event = (EntityDamageEvent)event;
/* 57 */       Event.setDamage((int)Event.getDamage() * 8);
/* 58 */       break;
/*    */     case 1: 
/* 60 */       EntityDamageEvent Event2 = (EntityDamageEvent)event;
/* 61 */       Event2.setDamage((int)Event2.getDamage() * 4);
/* 62 */       break;
/*    */     case 2: 
/* 64 */       EntityDamageByEntityEvent Event3 = (EntityDamageByEntityEvent)event;
/* 65 */       Event3.setDamage((int)Event3.getDamage() / 2);
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Zombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
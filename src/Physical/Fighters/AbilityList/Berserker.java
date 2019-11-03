/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;

import org.bukkit.entity.Damageable;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ 
/*    */ public class Berserker extends AbilityBase
/*    */ {
/*    */   public Berserker()
/*    */   {
/* 17 */     InitAbility("광전사", AbilityBase.Type.Passive_Manual, AbilityBase.Rank.A, new String[] {
/* 18 */       "체력이 70% 이하로 떨어지면 데미지가 1.5배로 증폭되며", 
/* 19 */       "체력이 40% 이하로 떨어지면 데미지가 2배로 증폭됩니다.", 
/* 20 */       "체력이 20% 이하로 떨어지면 데미지가 3배로 증폭됩니다.", 
/* 21 */       "체력이 5% 이하로 떨어지면 데미지가 5배로 증폭됩니다." });
/* 22 */     InitAbility(0, 0, true);
/* 23 */     EventManager.onEntityDamageByEntity.add(new EventData(this));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 28 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 29 */     if (PlayerCheck(Event.getDamager())) {
/* 30 */       Player p = (Player)Event.getDamager();
/* 31 */       if ((((Damageable)p).getHealth() <= 14.0D) && (((Damageable)p).getHealth() > 8.0D))
/* 32 */         return 0;
/* 33 */       if (((Damageable)p).getHealth() <= 8.0D)
/* 34 */         return 1;
/* 35 */       if (((Damageable)p).getHealth() <= 4.0D)
/* 36 */         return 2;
/* 37 */       if (((Damageable)p).getHealth() <= 1.0D)
/* 38 */         return 3;
/*    */     }
/* 40 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 45 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 46 */     switch (CustomData) {
/*    */     case 0: 
/* 48 */       Event.setDamage((int)(Event.getDamage() * 1.5D));
/* 49 */       break;
/*    */     case 1: 
/* 51 */       Event.setDamage((int) (Event.getDamage() * 2.0D));
/* 52 */       break;
/*    */     case 2: 
/* 54 */       Event.setDamage((int) (Event.getDamage() * 3.0D));
/* 55 */       break;
/*    */     case 3: 
/* 57 */       Event.setDamage((int) (Event.getDamage() * 5.0D));
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Berserker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
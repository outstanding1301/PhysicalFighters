/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import Physical.Fighters.PhysicalFighters;
/*    */ import java.util.ArrayList;

import org.bukkit.entity.Damageable;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class Demigod extends AbilityBase
/*    */ {
/*    */   public Demigod()
/*    */   {
/* 20 */     if (PhysicalFighters.SRankUsed)
/*    */     {
/* 22 */       InitAbility("데미갓", AbilityBase.Type.Passive_AutoMatic, AbilityBase.Rank.S, new String[] {
/* 23 */         "반은 인간, 반은 신인 능력자입니다.", 
/* 24 */         "데미지를 받으면 일정 확률로 10초간 랜덤 버프가 발동됩니다." });
/* 25 */       InitAbility(0, 0, true);
/* 26 */       EventManager.onEntityDamage.add(new EventData(this));
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 32 */     EntityDamageEvent Event = (EntityDamageEvent)event;
/* 33 */     if ((!EventManager.DamageGuard) && 
/* 34 */       (PlayerCheck(Event.getEntity()))) {
/* 35 */       return 0;
/*    */     }
/* 37 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 42 */     EntityDamageEvent Event = (EntityDamageEvent)event;
/* 43 */     Player p1 = (Player)Event.getEntity();
/* 44 */     if (Math.random() <= 0.05D) {
/* 45 */       p1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0), true);
/* 46 */     } else if (Math.random() <= 0.05D) {
/* 47 */       p1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 0), true);
/* 48 */     } else if (Math.random() <= 0.1D) {
/* 49 */       p1.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 0), true);
/* 50 */     } else if (Math.random() <= 0.1D) {
/* 51 */       p1.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 0), true);
/* 52 */     } else if (Math.random() <= 0.1D) {
/* 53 */       p1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0), true);
/* 54 */     } else if ((Math.random() <= 0.05D) && 
/* 55 */       (((Damageable)p1).getHealth() < 20.0D)) {
/* 56 */       p1.setHealth(((Damageable)p1).getHealth() + 1);
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Demigod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
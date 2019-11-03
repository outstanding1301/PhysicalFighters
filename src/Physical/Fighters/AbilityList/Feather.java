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
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class Feather extends AbilityBase
/*    */ {
/*    */   public Feather()
/*    */   {
/* 21 */     if (!PhysicalFighters.Specialability) {
/* 22 */       InitAbility("깃털", AbilityBase.Type.Passive_AutoMatic, AbilityBase.Rank.C, new String[] {
/* 23 */         "낙하 데미지와 물속에서의 질식 데미지를 받지 않습니다.", 
/* 24 */         "90% 확률로 폭발,번개 데미지를 1로 줄여받으며 미러링 능력을", 
/* 25 */         "회피할 수 있습니다, 낙하시 1분간 속도, 점프력이 빨라지는 버프를 받습니다." });
/* 26 */       InitAbility(0, 0, true);
/* 27 */       EventManager.onEntityDamage.add(new EventData(this));
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 33 */     EntityDamageEvent Event = (EntityDamageEvent)event;
/* 34 */     if (PlayerCheck(Event.getEntity())) {
/* 35 */       if ((Event.getCause() == EntityDamageEvent.DamageCause.FALL) || 
/* 36 */         (Event.getCause() == EntityDamageEvent.DamageCause.DROWNING))
/* 37 */         return 0;
/* 38 */       if ((Math.random() <= 0.9D) && (
/* 39 */         (Event.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) || 
/* 40 */         (Event
/* 41 */         .getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION))) {
/* 42 */         return 1;
/*    */       }
/*    */     }
/* 45 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 50 */     EntityDamageEvent Event = (EntityDamageEvent)event;
/* 51 */     ((Player)Event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1200, 1), 
/* 52 */       true);
/* 53 */     ((Player)Event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 1), 
/* 54 */       true);
/* 55 */     Event.setCancelled(true);
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Feather.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
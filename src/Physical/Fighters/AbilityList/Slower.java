/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ 
/*    */ public class Slower extends AbilityBase
/*    */ {
/*    */   public Slower()
/*    */   {
/* 16 */     InitAbility("슬로워", Physical.Fighters.MainModule.AbilityBase.Type.Passive_AutoMatic, AbilityBase.Rank.C, new String[] {
/* 17 */       "자신에게 공격받은 사람은 그 1.5초간 느려집니다," });
/* 18 */     InitAbility(0, 0, true);
/* 19 */     EventManager.onEntityDamageByEntity.add(new EventData(this));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 24 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 25 */     if ((PlayerCheck(Event.getDamager())) && !EventManager.DamageGuard && 
/* 26 */       ((Event.getEntity() instanceof Player))) {
/* 27 */       return 0;
/*    */     }
/* 29 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 34 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 35 */     Player p = (Player)Event.getEntity();
/* 36 */     p.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.SLOW, 30, 0), true);
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Slower.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
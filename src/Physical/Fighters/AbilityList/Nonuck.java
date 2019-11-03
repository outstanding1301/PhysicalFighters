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
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*    */ 
/*    */ public class Nonuck extends AbilityBase
/*    */ {
/*    */   public Nonuck()
/*    */   {
/* 19 */     if (PhysicalFighters.SRankUsed) {
/* 20 */       InitAbility("무통증", AbilityBase.Type.Passive_AutoMatic, AbilityBase.Rank.S, new String[] {
/* 21 */         "플레이어에게 타격당할시에 80%확률로 넉백을 무시합니다." });
/* 22 */       InitAbility(0, 0, true);
/* 23 */       EventManager.onEntityDamageByEntity.add(new EventData(this));
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 29 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 30 */     if ((PlayerCheck(Event.getEntity())) && 
/* 31 */       ((Event.getCause() == DamageCause.ENTITY_ATTACK) || (Event.getCause() == DamageCause.PROJECTILE)) && 
/* 32 */       (Math.random() <= 0.8D) && 
/* 33 */       (!EventManager.DamageGuard)) {
/* 34 */       return 0;
/*    */     }
/* 36 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 41 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 42 */     Player p = (Player)Event.getEntity();
/* 43 */     int damage = (int)Event.getDamage();
/* 44 */     p.damage(damage);
/* 45 */     Event.setCancelled(true);
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Nonuck.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
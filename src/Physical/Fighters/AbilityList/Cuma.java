/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import Physical.Fighters.PhysicalFighters;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class Cuma extends AbilityBase
/*    */ {
/*    */   public Cuma()
/*    */   {
/* 21 */     if (PhysicalFighters.SRankUsed)
/*    */     {
/* 23 */       InitAbility("바솔로뮤 쿠마", AbilityBase.Type.Passive_AutoMatic, AbilityBase.Rank.S, new String[] {
/* 24 */         "일정 확률르 받은 공격을 상대에게 되돌려주며, 공격받을시 상대를 뒤로 넉백시킵니다." });
/* 25 */       InitAbility(0, 0, true);
/* 26 */       EventManager.onEntityDamageByEntity.add(new EventData(this));
/*    */     }
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 32 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 33 */     if (PlayerCheck(Event.getEntity()) && !EventManager.DamageGuard) {
/* 34 */       return 0;
/*    */     }
/* 36 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 41 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 42 */     Player p = (Player)Event.getEntity();
/* 43 */     Player pn = (Player)Event.getDamager();
/* 44 */     if (Math.random() <= 0.15D) {
/* 45 */       pn.damage(Event.getDamage());
/* 46 */       Event.setCancelled(true);
/*    */     }
/* 48 */     pn.getWorld().createExplosion(pn.getLocation(), 0.0F);
/* 49 */     pn.setVelocity(pn.getVelocity().add(
/* 50 */       p.getLocation().toVector()
/* 51 */       .subtract(pn.getLocation().toVector()).normalize()
/* 52 */       .multiply(-1)));
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Cuma.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
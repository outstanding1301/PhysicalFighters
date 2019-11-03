/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.ACC;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class NuclearPunch extends AbilityBase
/*    */ {
/*    */   public NuclearPunch()
/*    */   {
/* 22 */     InitAbility("핵 펀치", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.A, new String[] {
/* 23 */       "철괴로 타격을 당한 상대가 매우 멀리 넉백당합니다.", "동시에 데미지 20을 받습니다." });
/* 24 */     InitAbility(45, 0, true);
/* 25 */     EventManager.onEntityDamageByEntity.add(new EventData(this));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 30 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 31 */     if ((PlayerCheck(Event.getDamager())) && (ItemCheck(ACC.DefaultItem))) {
/* 32 */       return 0;
/*    */     }
/* 34 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 39 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 40 */     Event.setDamage(8);
/* 41 */     Event.getEntity().getWorld()
/* 42 */       .createExplosion(Event.getEntity().getLocation(), 0.0F);
/* 43 */     int knockback = -24;
/* 44 */     if ((Event.getEntity() instanceof Player)) {
/* 45 */       Player p = (Player)Event.getEntity();
/* 46 */       if (p.isBlocking()) {
/* 47 */         knockback = -12;
/*    */       }
/*    */     }
/* 50 */     Event.getEntity().setVelocity(
/* 51 */       Event.getEntity()
/* 52 */       .getVelocity()
/* 53 */       .add(Event
/* 54 */       .getDamager()
/* 55 */       .getLocation()
/* 56 */       .toVector()
/* 57 */       .subtract(
/* 58 */       Event.getEntity().getLocation()
/* 59 */       .toVector()).normalize()
/* 60 */       .multiply(knockback)));
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\NuclearPunch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
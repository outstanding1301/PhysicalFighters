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
/*    */ 
/*    */ public class Roclee extends AbilityBase
/*    */ {
/*    */   public Roclee()
/*    */   {
/* 21 */     InitAbility("록리", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/* 22 */       "철괴로 상대를 타격할시 맞은사람을 매우빠른속도로 높이 띄웁니다." });
/* 23 */     InitAbility(20, 0, true);
/* 24 */     EventManager.onEntityDamageByEntity.add(new EventData(this));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 29 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 30 */     if ((PlayerCheck(Event.getDamager())) && (ItemCheck(ACC.DefaultItem)) && !EventManager.DamageGuard) {
/* 31 */       return 0;
/*    */     }
/* 33 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 38 */     EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent)event;
/* 39 */     Player p = (Player)Event.getEntity();
/* 40 */     Location l1 = Event.getEntity().getLocation();
/* 41 */     Location l2 = Event.getEntity().getLocation();
/* 42 */     Location l = GetPlayer().getLocation();
/* 43 */     Location ll = GetPlayer().getLocation();
/* 44 */     Event.getEntity().getWorld()
/* 45 */       .createExplosion(Event.getEntity().getLocation(), 0.0F);
/* 46 */     l2.setY(l1.getY() + 8.0D);
/* 47 */     p.teleport(l2);
/* 48 */     Event.getEntity().getWorld().createExplosion(l2, 1.0F);
/* 49 */     ll.setY(l.getY() + 8.0D);
/* 50 */     p.damage(8);
/* 51 */     GetPlayer().teleport(ll);
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Roclee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
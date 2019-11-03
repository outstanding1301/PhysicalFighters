/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.ProjectileHitEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class Fireball extends AbilityBase
/*    */ {
/*    */   public Fireball()
/*    */   {
/* 18 */     InitAbility("파이어볼", Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, AbilityBase.Rank.B, new String[] {
/* 19 */       "바라보는 방향에 약한 화염구를 날립니다." });
/* 20 */     InitAbility(15, 0, true);
/* 21 */     RegisterLeftClickEvent();
/* 22 */     EventManager.onProjectileHitEvent.add(new Physical.Fighters.MinerModule.EventData(this, 1));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 27 */     switch (CustomData)
/*    */     {
/*    */     case 0: 
/* 30 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 31 */       if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem)) && !EventManager.DamageGuard)
/* 32 */         return 0;
/*    */       break;
/*    */     case 1: 
/* 35 */       ProjectileHitEvent Event1 = (ProjectileHitEvent)event;
/* 36 */       if ((Event1.getEntity() instanceof org.bukkit.entity.Fireball))
/*    */       {
/* 38 */         Event1.getEntity().getWorld().createExplosion(Event1.getEntity().getLocation(), 3.0F, true);
/* 39 */         Event1.getEntity().getWorld().createExplosion(Event1.getEntity().getLocation(), 2.5F, true);
/* 40 */         Event1.getEntity().getWorld().createExplosion(Event1.getEntity().getLocation(), 3.0F, true);
/*    */       }
/*    */       break;
/*    */     }
/*    */     
/* 45 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 50 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 51 */     Player p = Event.getPlayer();
/* 52 */     World a = p.getWorld();
/* 53 */     Location loc = p.getLocation();
/* 54 */     Location l = p.getLocation();
/* 55 */     l.setY(p.getLocation().getY() + 2.0D);
/* 56 */     double degrees = Math.toRadians(-(l.getYaw() % 360.0F));
/* 57 */     double ydeg = Math.toRadians(-(l.getPitch() % 360.0F));
/* 58 */     loc.setX(l.getX() + 1.2D * (
/* 59 */       Math.sin(degrees) * Math.cos(ydeg)));
/* 60 */     loc.setY(l.getY() + 1.2D * 
/* 61 */       Math.sin(ydeg));
/* 62 */     loc.setZ(l.getZ() + 1.2D * (
/* 63 */       Math.cos(degrees) * Math.cos(ydeg)));
/* 64 */     a.spawn(loc, org.bukkit.entity.Fireball.class);
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Fireball.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
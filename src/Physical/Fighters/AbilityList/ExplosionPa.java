/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MinerModule.EventData;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.LinkedList;
/*    */ import java.util.Timer;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
/*    */ 
/*    */ public class ExplosionPa extends AbilityBase
/*    */ {
/* 23 */   LinkedList<String> gigong = new LinkedList();
/* 24 */   HashMap<String, Location> pLoc = new HashMap();
/*    */   
/*    */   public ExplosionPa() {
/* 27 */     InitAbility(
/* 28 */       "기공파", 
/* 29 */       Physical.Fighters.MainModule.AbilityBase.Type.Active_Immediately, 
/* 30 */       AbilityBase.Rank.S, 
/* 31 */       new String[] { "바라보는 방향으로 강한폭발을 여러차례 일으킵니다. 시전시간은 약 5초정도 되며, 5초간 움직일 수 없습니다." });
/* 32 */     InitAbility(40, 0, true);
/* 33 */     RegisterLeftClickEvent();
/* 34 */     EventManager.onPlayerMoveEvent.add(new EventData(this, 1));
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData) {
/* 38 */     switch (CustomData) {
/*    */     case 0: 
/* 40 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 41 */       if ((PlayerCheck(Event.getPlayer())) && 
/* 42 */         (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem)) && !EventManager.DamageGuard)
/* 43 */         return 0;
/*    */       break;
/*    */     case 1: 
/* 46 */       PlayerMoveEvent Event2 = (PlayerMoveEvent)event;
/* 47 */       if ((PlayerCheck(Event2.getPlayer())) && 
/* 48 */         (this.gigong.contains(Event2.getPlayer().getName()))) {
/* 49 */         Event2.getPlayer().teleport((Location)this.pLoc.get(Event2.getPlayer().getName()));
/*    */       }
/*    */       break;
/*    */     }
/* 53 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData) {
/* 57 */     this.gigong.add(GetPlayer().getName());
/* 58 */     this.pLoc.put(GetPlayer().getName(), GetPlayer().getLocation());
/* 59 */     Timer timer = new Timer();
/* 60 */     timer.schedule(new giTimer(), 500L, 500L);
/*    */   }
/*    */   
/*    */   public class giTimer extends java.util.TimerTask {
/* 64 */     private int ab = 10;
/* 65 */     private int i = 0;
/*    */     
/*    */     public giTimer() {}
/*    */     
/* 69 */     public void run() { Location l = ExplosionPa.this.GetPlayer().getLocation();
/* 70 */       Location l2 = ExplosionPa.this.GetPlayer().getLocation();
/* 71 */       double degrees = Math.toRadians(-(l.getYaw() % 360.0F));
/* 72 */       double ydeg = Math.toRadians(-(l.getPitch() % 360.0F));
/* 73 */       l2.setX(l.getX() + (2 * this.i + 2) * (
/* 74 */         Math.sin(degrees) * Math.cos(ydeg)));
/* 75 */       l2.setY(l.getY() + (2 * this.i + 2) * Math.sin(ydeg));
/* 76 */       l2.setZ(l.getZ() + (2 * this.i + 2) * (
/* 77 */         Math.cos(degrees) * Math.cos(ydeg)));
/* 78 */       l2.getWorld().createExplosion(l2, 0.0F);
/* 79 */       Player[] List = Bukkit.getOnlinePlayers();
/* 80 */       Player[] arrayOfPlayer1; int k = (arrayOfPlayer1 = List).length; for (int j = 0; j < k; j++) { Player pp = arrayOfPlayer1[j];
/* 81 */         if (pp != ExplosionPa.this.GetPlayer())
/*    */         {
/* 83 */           Location loc = pp.getLocation();
/* 84 */           if (l2.getWorld().getBlockAt(l2).getLocation().distance(loc) <= 4.0D)
/*    */           {
/* 86 */             pp.damage(10, ExplosionPa.this.GetPlayer());
/*    */           }
/*    */         }
/*    */       }
/* 90 */       this.ab -= 1;
/* 91 */       this.i += 1;
/* 92 */       if (this.ab <= 0) {
/* 93 */         ExplosionPa.this.gigong.remove(ExplosionPa.this.GetPlayer().getName());
/* 94 */         ExplosionPa.this.pLoc.remove(ExplosionPa.this.GetPlayer().getName());
/* 95 */         cancel();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\ExplosionPa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
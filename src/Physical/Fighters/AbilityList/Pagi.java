/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.OtherModule.Vector;
/*    */ import java.util.Collection;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class Pagi extends AbilityBase
/*    */ {
/*    */   public Pagi()
/*    */   {
/* 24 */     InitAbility("패기", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.SS, new String[] {
/* 25 */       "능력 사용시 20초간 10칸 안에 있는 적에게 5초마다 강한데미지를 줍니다." });
/* 26 */     InitAbility(160, 0, true);
/* 27 */     RegisterLeftClickEvent();
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 32 */     if (!EventManager.DamageGuard) {
/* 33 */       PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 34 */       if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(Physical.Fighters.MinerModule.ACC.DefaultItem)))
/* 35 */         return 0;
/*    */     }
/* 37 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 42 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 43 */     Timer timer = new Timer();
/* 44 */     timer.schedule(new Pauck(Event.getPlayer().getName()), 500L, 1500L);
/*    */   }
/*    */   
/*    */   class Pauck extends TimerTask
/*    */   {
/* 49 */     private int num = 0;
/* 50 */     private String name = null;
/*    */     
/* 52 */     public Pauck(String name1) { this.name = name1; }
/*    */     
/*    */ 
/*    */     public void run()
/*    */     {
/* 57 */       Player[] p1 = Bukkit.getOnlinePlayers();
/* 58 */       Player p = Bukkit.getPlayer(this.name);
/* 59 */       if (p != null) {
/* 60 */         for (int i = 0; i < (Bukkit.getOnlinePlayers()).length; i++)
/* 61 */           if ((p1[i] != p) && (p1[i].getGameMode() != GameMode.CREATIVE)) {
/* 62 */             Location loc = p1[i].getLocation();
/* 63 */             Location l = p.getLocation();
/*    */             
/* 65 */             Vector targetvec = new Vector(loc.getX(), loc.getY(), loc.getZ());
/* 66 */             Vector playervec = new Vector(l.getX(), l.getY(), l.getZ());
/* 67 */             if ((playervec.distance(targetvec) <= 10.0D) && (loc.getY() != 0.0D)) {
/* 68 */               p1[i].damage(5, p);
/* 69 */               p1[i].addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 30, 0), true);
/*    */             }
/*    */           }
/* 72 */         if (this.num > 20) cancel();
/* 73 */         this.num += 1;
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Pagi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
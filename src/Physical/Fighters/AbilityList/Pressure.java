/*    */ package Physical.Fighters.AbilityList;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
import Physical.Fighters.MainModule.EventManager;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.MinerModule.ACC;
/*    */ import java.util.Collection;
/*    */ import java.util.LinkedList;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class Pressure extends AbilityBase
/*    */ {
/*    */   public Pressure()
/*    */   {
/* 22 */     InitAbility("압력", AbilityBase.Type.Active_Immediately, AbilityBase.Rank.S, new String[] {
/* 23 */       "철괴로 왼쪽클릭시 20칸 이내의 모든 적을 강한 압력으로 압축시킵니다.", 
/* 24 */       "대상플레이어는 데미지와 디버프를 받습니다." });
/* 25 */     InitAbility(40, 0, true);
/* 26 */     RegisterLeftClickEvent();
/*    */   }
/*    */   
/*    */   public int A_Condition(Event event, int CustomData)
/*    */   {
/* 31 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 32 */     if ((PlayerCheck(Event.getPlayer())) && (ItemCheck(ACC.DefaultItem)) && !EventManager.DamageGuard) {
/* 33 */       return 0;
/*    */     }
/* 35 */     return -1;
/*    */   }
/*    */   
/*    */   public void A_Effect(Event event, int CustomData)
/*    */   {
/* 40 */     PlayerInteractEvent Event = (PlayerInteractEvent)event;
/* 41 */     Player p = Event.getPlayer();
/* 42 */     LinkedList<Player> ts = new LinkedList();
/* 43 */     for (int i = 0; i < (Bukkit.getOnlinePlayers()).length; i++)
/*    */     {
/* 45 */       if ((p.getLocation().distance((Bukkit.getOnlinePlayers())[i].getLocation()) < 20.0D) && 
/* 46 */         ((Bukkit.getOnlinePlayers())[i] != p)) {
/* 47 */         ts.add((Bukkit.getOnlinePlayers())[i]);
/*    */       }
/*    */     }
/* 50 */     if (!ts.isEmpty()) {
/* 51 */       for (int i = 0; i < ts.size(); i++) {
/* 52 */         Player t = (Player)ts.get(i);
/* 53 */         Timer timer = new Timer();
/* 54 */         timer.schedule(new DTim(p, t), 100L, 100L);
/* 55 */         t.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2), true);
/* 56 */         t.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 2), true);
/* 57 */         t.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 2), true);
/* 58 */         t.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 2), true);
/* 59 */         t.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 2), true);
/*    */       }
/* 61 */       ts.clear();
/*    */     }
/*    */   }
/*    */   
/*    */   public class DTim extends TimerTask {
/*    */     Player pp;
/*    */     Player tt;
/* 68 */     private int a = 15;
/*    */     
/* 70 */     DTim(Player p, Player t) { this.pp = p;
/* 71 */       this.tt = t;
/*    */     }
/*    */     
/*    */     public void run() {
/* 75 */       this.tt.damage(8, this.pp);
/* 76 */       this.tt.playEffect(this.tt.getLocation(), org.bukkit.Effect.SMOKE, 20);
/* 77 */       this.a -= 1;
/* 78 */       if (this.a <= 0) {
/* 79 */         cancel();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Pressure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
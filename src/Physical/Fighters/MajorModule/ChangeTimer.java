/*    */ package Physical.Fighters.MajorModule;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MinerModule.AUC;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Random;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class ChangeTimer
/*    */ {
/* 17 */   public static Timer ct = new Timer();
/*    */   
/* 19 */   public static void start() { ct.schedule(new TimerTask()
/*    */     {
/*    */       public void run()
/*    */       {
/* 23 */         Bukkit.broadcastMessage(ChatColor.RED + "모든 플레이어의 능력이 랜덤으로 재추첨됩니다.");
/* 24 */         Player[] pl = Bukkit.getOnlinePlayers();
/* 25 */         for (AbilityBase ab : AbilityList.AbilityList) {
/* 26 */           ab.SetPlayer(null, true);
/*    */         }
/* 28 */         Random r = new Random();
/* 29 */         Player[] arrayOfPlayer1; int j = (arrayOfPlayer1 = pl).length; for (int k = 0; k < j; k++) { Player p = arrayOfPlayer1[k];
/*    */           AbilityBase a;
/* 31 */           do { int i; do { i = r.nextInt(AbilityList.AbilityList.size());
/* 32 */             } while (i == 0);
/* 33 */             a = 
/* 34 */               (AbilityBase)AbilityList.AbilityList.get(i);
/* 35 */           } while (a.GetPlayer() != null);
/* 36 */           a.SetPlayer(p, true);
/* 37 */           a.SetRunAbility(true);
/* 38 */           AUC.InfoTextOut(p);
/*    */ 
/*    */         }
/*    */         
/*    */       }
/*    */       
/*    */ 
/* 45 */     }, 600000L, 600000L);
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\MajorModule\ChangeTimer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
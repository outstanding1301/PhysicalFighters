/*    */ package Physical.Fighters.Script;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MajorModule.AbilityList;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ 
/*    */ public class S_GameWarnning
/*    */ {
/* 11 */   private S_ScriptTimer stimer = new S_ScriptTimer();
/*    */   private MainScripter ms;
/*    */   
/*    */   public S_GameWarnning(MainScripter ms)
/*    */   {
/* 16 */     this.ms = ms;
/*    */   }
/*    */   
/*    */   public void GameWarnningStart() {
/* 20 */     this.stimer.StartTimer(99999999);
/*    */   }
/*    */   
/*    */   public void GameWarnningStop() {
/* 24 */     this.stimer.EndTimer();
/*    */   }
/*    */   
/*    */   public final class S_ScriptTimer extends Physical.Fighters.MinerModule.TimerBase
/*    */   {
/*    */     public S_ScriptTimer() {}
/*    */     
/*    */     public void EventStartTimer() {}
/*    */     
/*    */     public void EventRunningTimer(int count) {
/* 34 */       if ((count >= 20) && (count % 20 == 0)) {
/* 35 */         Bukkit.broadcastMessage(ChatColor.RED + 
/* 36 */           "경고, 게임이 올바르게 시작되지 않았습니다.");
/* 37 */         Bukkit.broadcastMessage(ChatColor.RED + 
/* 38 */           "/va yes나 /va no 명령으로 능력을 확정하세요.");
/* 39 */         for (int l = 0; l < AbilityList.AbilityList.size(); l++) {
/* 40 */           if (((AbilityBase)AbilityList.AbilityList.get(l)).GetPlayer() != null) {
/* 41 */             AbilityBase tempab = (AbilityBase)AbilityList.AbilityList.get(l);
/* 42 */             if (!S_GameWarnning.this.ms.OKSign.contains(tempab.GetPlayer())) {
/* 43 */               tempab.GetPlayer().sendMessage(
/* 44 */                 "당신의 능력이 올바르게 확정되지 않았습니다.");
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     
/*    */     public void EventEndTimer() {}
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\Script\S_GameWarnning.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
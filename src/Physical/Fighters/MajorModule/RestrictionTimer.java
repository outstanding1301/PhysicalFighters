/*    */ package Physical.Fighters.MajorModule;
/*    */ 
/*    */ import Physical.Fighters.MinerModule.TimerBase;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class RestrictionTimer
/*    */   extends TimerBase
/*    */ {
/*    */   public void EventStartTimer() {}
/*    */   
/*    */   public void EventRunningTimer(int count) {}
/*    */   
/*    */   public void EventEndTimer()
/*    */   {
/* 19 */     Bukkit.broadcastMessage(ChatColor.GREEN + "일부 능력의 제약이 해제되었습니다.");
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\MajorModule\RestrictionTimer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
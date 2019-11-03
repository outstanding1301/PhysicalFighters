/*    */ package Physical.Fighters.MajorModule;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.ShowText;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public final class CoolDownTimer extends Physical.Fighters.MinerModule.TimerBase
/*    */ {
/*    */   private AbilityBase ab;
/*    */   
/*    */   public CoolDownTimer(AbilityBase ab)
/*    */   {
/* 14 */     this.ab = ab;
/*    */   }
/*    */   
/*    */   public void EventStartTimer()
/*    */   {
/* 19 */     this.ab.A_CoolDownStart();
/*    */   }
/*    */   
/*    */   public void EventRunningTimer(int count)
/*    */   {
/* 24 */     if (((count <= 3) && (count >= 1) && (this.ab.GetShowText() == AbilityBase.ShowText.All_Text)) || (this.ab.GetShowText() == AbilityBase.ShowText.No_DurationText)) {
/* 25 */       this.ab.GetPlayer().sendMessage(String.format(ChatColor.RED + "%d초 뒤" + ChatColor.WHITE + " 능력사용이 가능합니다.", new Object[] { Integer.valueOf(count) }));
/*    */     }
/*    */   }
/*    */   
/*    */   public void EventEndTimer() {
/* 30 */     this.ab.A_CoolDownEnd();
/* 31 */     if (this.ab.GetShowText() != AbilityBase.ShowText.Custom_Text) {
/* 32 */       this.ab.GetPlayer().sendMessage(Physical.Fighters.MinerModule.ACC.DefaultCoolDownEnd);
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\MajorModule\CoolDownTimer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
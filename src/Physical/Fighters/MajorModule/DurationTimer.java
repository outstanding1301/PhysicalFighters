/*    */ package Physical.Fighters.MajorModule;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.ShowText;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public final class DurationTimer extends Physical.Fighters.MinerModule.TimerBase
/*    */ {
/*    */   private AbilityBase ab;
/*    */   private CoolDownTimer ctimer;
/*    */   
/*    */   public DurationTimer(AbilityBase ab, CoolDownTimer ctimer)
/*    */   {
/* 15 */     this.ab = ab;
/* 16 */     this.ctimer = ctimer;
/*    */   }
/*    */   
/*    */   public void EventStartTimer()
/*    */   {
/* 21 */     this.ab.A_DurationStart();
/*    */   }
/*    */   
/*    */   public void EventRunningTimer(int count)
/*    */   {
/* 26 */     if (((count <= 3) && (count >= 1) && (this.ab.GetShowText() == AbilityBase.ShowText.All_Text)) || (this.ab.GetShowText() == AbilityBase.ShowText.No_CoolDownText)) {
/* 27 */       this.ab.GetPlayer().sendMessage(String.format(ChatColor.GREEN + "지속 시간" + ChatColor.WHITE + " %d초 전", new Object[] { Integer.valueOf(count) }));
/*    */     }
/*    */   }
/*    */   
/*    */   public void EventEndTimer() {
/* 32 */     this.ab.A_DurationEnd();
/* 33 */     if (this.ab.GetShowText() != AbilityBase.ShowText.Custom_Text)
/* 34 */       this.ab.GetPlayer().sendMessage(Physical.Fighters.MinerModule.ACC.DefaultDurationEnd);
/* 35 */     this.ctimer.StartTimer(this.ab.GetCoolDown(), true);
/*    */   }
/*    */   
/*    */   public void FinalEventEndTimer()
/*    */   {
/* 40 */     this.ab.A_FinalDurationEnd();
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\MajorModule\DurationTimer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
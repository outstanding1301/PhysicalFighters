/*     */ package Physical.Fighters.Script;
/*     */ 
/*     */ import Physical.Fighters.MajorModule.RestrictionTimer;
/*     */ import Physical.Fighters.MinerModule.TimerBase;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ 
/*     */ public class S_GameProgress
/*     */ {
/*     */   private MainScripter ms;
/*  12 */   private S_ScriptTimer stimer = new S_ScriptTimer();
/*  13 */   private int EarlyInvincibleTime = 300;
/*  14 */   private boolean gcon = false;
/*     */   
/*     */   public S_GameProgress(MainScripter ms) {
/*  17 */     this.ms = ms;
/*  18 */     this.EarlyInvincibleTime = (PhysicalFighters.EarlyInvincibleTime * 60);
/*     */   }
/*     */   
/*     */   public void GameProgress() {
/*  22 */     this.stimer.StartTimer(99999999);
/*     */   }
/*     */   
/*     */   public void GameProgressStop() {
/*  26 */     this.gcon = false;
/*  27 */     this.stimer.StopTimer();
/*     */   }
/*     */   
/*     */   public final class S_ScriptTimer extends TimerBase
/*     */   {
/*     */     public S_ScriptTimer() {}
/*     */     
/*     */     public void EventStartTimer() {}
/*     */     
/*     */     public void EventRunningTimer(int count)
/*     */     {
/*  38 */       if (PhysicalFighters.PrintTip) {
/*  39 */         PrintTip(count);
/*     */       }
/*  41 */       if ((count > 20) && (count % 15 == 0)) {
/*  42 */         S_GameProgress.this.ms.gameworld.setStorm(false);
/*  43 */         if (S_GameProgress.this.gcon) {
/*  44 */           System.gc();
/*     */         }
/*     */       }
/*  47 */       if ((count > 20) && (count % 600 == 0)) {
/*  48 */         Bukkit.broadcastMessage(String.format(ChatColor.DARK_RED + 
/*  49 */           "Physical Fighters", new Object[0]));
/*     */         
/*  51 */         Bukkit.broadcastMessage(String.format(ChatColor.GRAY + 
/*  52 */           "빌드 넘버 : %d", new Object[] {
/*  53 */           Integer.valueOf(PhysicalFighters.BuildNumber) }));
/*     */       }
/*     */       
/*  56 */       if ((PhysicalFighters.Invincibility) && (S_GameProgress.this.EarlyInvincibleTime > 60) && 
/*  57 */         (S_GameProgress.this.EarlyInvincibleTime - 60 == count)) {
/*  58 */         Bukkit.broadcastMessage(ChatColor.YELLOW + "초반 무적이 " + 
/*  59 */           ChatColor.WHITE + "1분후 해제됩니다.");
/*     */       }
/*  61 */       if ((PhysicalFighters.Invincibility) && 
/*  62 */         (S_GameProgress.this.EarlyInvincibleTime - 5 == count)) {
/*  63 */         Bukkit.broadcastMessage(ChatColor.YELLOW + "5초 후" + 
/*  64 */           ChatColor.WHITE + " 초반무적이 해제됩니다.");
/*     */       }
/*  66 */       if ((PhysicalFighters.Invincibility) && 
/*  67 */         (S_GameProgress.this.EarlyInvincibleTime - 4 == count)) {
/*  68 */         Bukkit.broadcastMessage(ChatColor.YELLOW + "4초 후" + 
/*  69 */           ChatColor.WHITE + " 초반무적이 해제됩니다.");
/*     */       }
/*  71 */       if ((PhysicalFighters.Invincibility) && 
/*  72 */         (S_GameProgress.this.EarlyInvincibleTime - 3 == count)) {
/*  73 */         Bukkit.broadcastMessage(ChatColor.YELLOW + "3초 후" + 
/*  74 */           ChatColor.WHITE + " 초반무적이 해제됩니다.");
/*     */       }
/*  76 */       if ((PhysicalFighters.Invincibility) && 
/*  77 */         (S_GameProgress.this.EarlyInvincibleTime - 2 == count)) {
/*  78 */         Bukkit.broadcastMessage(ChatColor.YELLOW + "2초 후" + 
/*  79 */           ChatColor.WHITE + " 초반무적이 해제됩니다.");
/*     */       }
/*  81 */       if ((PhysicalFighters.Invincibility) && 
/*  82 */         (S_GameProgress.this.EarlyInvincibleTime - 1 == count)) {
/*  83 */         Bukkit.broadcastMessage(ChatColor.YELLOW + "1초 후" + 
/*  84 */           ChatColor.WHITE + " 초반무적이 해제됩니다.");
/*     */       }
/*  86 */       if ((PhysicalFighters.Invincibility) && (S_GameProgress.this.EarlyInvincibleTime == count)) {
/*  87 */         Bukkit.broadcastMessage(ChatColor.GREEN + 
/*  88 */           "초반 무적이 해제되었습니다. 이제 데미지를 입습니다.");
/*  89 */         Physical.Fighters.MainModule.EventManager.DamageGuard = false;
/*  90 */         Physical.Fighters.MainModule.AbilityBase.restrictionTimer.StartTimer();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     public void EventEndTimer() {}
/*     */     
/*     */     public void PrintTip(int c)
/*     */     {
/*  99 */       switch (c)
/*     */       {
/*     */       case 30: 
/* 102 */         Bukkit.broadcastMessage("TIP. 본 플러그인은 제온님이 배포하신 VisualAbility의 모듈을 사용하고있습니다.");
/* 103 */         break;
/*     */       case 60: 
/* 105 */         Bukkit.broadcastMessage("TIP. http://cafe.naver.com/craftproducer/1165 이 카페 정말 좋아요");
/* 106 */         break;
/*     */       case 120: 
/* 108 */         Bukkit.broadcastMessage("TIP. 그저 게임을 재밌게 즐겨주셨으면 합니다.");
/* 109 */         break;
/*     */       case 150: 
/* 111 */         Bukkit.broadcastMessage("TIP. 패시브 능력은 가만히 있으셔도 능력이 적용됩니다.");
/* 112 */         break;
/*     */       case 160: 
/* 114 */         Bukkit.broadcastMessage("TIP. 액티브 능력은 철괴나 금괴를 이용해 적용 하실 수 있습니다.");
/* 115 */         break;
/*     */       case 190: 
/* 117 */         Bukkit.broadcastMessage("TIP. 게임 시작 후 10분마다 플레이어들의 좌표가 공개됩니다.");
/* 118 */         break;
/*     */       case 250: 
/* 120 */         Bukkit.broadcastMessage("TIP. 본 플러그인을 이용하시면서 불편한점이나 건의사항은 스카이프 ApplepieMod로 건의해주세요.");
/* 121 */         break;
/*     */       case 310: 
/* 123 */         Bukkit.broadcastMessage("TIP. 낙법이란 낙하데미지를 받지 않기위해 나갔다 들어오는 것을 말합니다.");
/* 124 */         break;
/*     */       case 340: 
/* 126 */         Bukkit.broadcastMessage("TIP. 싸움 도중에 나갈시에 경고없이 밴을 당하실 수 있습니다.");
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\Script\S_GameProgress.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
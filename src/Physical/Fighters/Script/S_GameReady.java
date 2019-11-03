/*     */ package Physical.Fighters.Script;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MajorModule.AbilityList;
/*     */ import Physical.Fighters.MinerModule.TimerBase;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public final class S_GameReady
/*     */ {
/*     */   private MainScripter mainscripter;
/*  18 */   private S_ScriptTimer stimer = new S_ScriptTimer();
/*  19 */   private int peoplecount = 0;
/*     */   
/*     */   public S_GameReady(MainScripter mainscripter) {
/*  22 */     this.mainscripter = mainscripter;
/*     */   }
/*     */   
/*     */   public void GameReady(Player p) {
/*  26 */     if (p.isOp()) {
/*  27 */       if (MainScripter.Scenario == MainScripter.ScriptStatus.NoPlay) {
/*  28 */         MainScripter.Scenario = MainScripter.ScriptStatus.ScriptStart;
/*  29 */         Bukkit.broadcastMessage(ChatColor.YELLOW + "(!)잠시 후 게임을 시작합니다.");
/*  30 */         this.stimer.StartTimer(9);
/*     */       } else {
/*  32 */         p.sendMessage(ChatColor.RED + "(!)이미 게임이 시작되어있습니다.");
/*     */       }
/*     */     } else
/*  35 */       p.sendMessage(ChatColor.RED + "(!)당신은 권한이 없습니다.");
/*     */   }
/*     */   
/*     */   public void GameReadyStop() {
/*  39 */     this.stimer.StopTimer();
/*     */   }
/*     */   
/*     */   public final class S_ScriptTimer
/*     */     extends TimerBase
/*     */   {
/*     */     public S_ScriptTimer() {}
/*     */     
/*     */     public void EventStartTimer() {}
/*     */     
/*     */     public void EventRunningTimer(int count)
/*     */     {
/*  51 */       switch (count) {
/*     */       case 0: 
/*  53 */         Bukkit.broadcastMessage(ChatColor.AQUA + "인식된 플레이어 목록");
/*  54 */         Bukkit.broadcastMessage(ChatColor.GOLD + "==========");
/*  55 */         Player[] templist = Bukkit.getOnlinePlayers();
/*  56 */         for (int l = 0; l < templist.length; l++)
/*     */         {
/*  58 */           if (!S_GameReady.this.mainscripter.ExceptionList.contains(templist[l])) {
/*  59 */             if (l < AbilityBase.GetAbilityCount()) {
/*  60 */               MainScripter.PlayerList.add(templist[l]);
/*  61 */               Bukkit.broadcastMessage(String.format(
/*  62 */                 ChatColor.GREEN + "%d. " + ChatColor.WHITE + 
/*  63 */                 "%s", 
/*  64 */                 new Object[] { Integer.valueOf(l), 
/*  65 */                 templist[l].getName() }));
/*     */             } else {
/*  67 */               Bukkit.broadcastMessage(String.format(ChatColor.RED + 
/*  68 */                 "%d. %s (Error)", 
/*  69 */                 new Object[] { Integer.valueOf(l), 
/*  70 */                 templist[l].getName() }));
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*  75 */         S_GameReady.this.peoplecount = 
/*  76 */           (templist.length - S_GameReady.this.mainscripter.ExceptionList.size());
/*     */         
/*  78 */         if (S_GameReady.this.peoplecount <= AbilityBase.GetAbilityCount()) {
/*  79 */           Bukkit.broadcastMessage(String.format(ChatColor.YELLOW + 
/*  80 */             "총 인원수 : %d명 ", new Object[] {
/*  81 */             Integer.valueOf(S_GameReady.this.peoplecount) }));
/*     */         } else {
/*  83 */           Bukkit.broadcastMessage(String.format(ChatColor.RED + 
/*  84 */             "총 인원수 : %d명 ", new Object[] {
/*  85 */             Integer.valueOf(S_GameReady.this.peoplecount) }));
/*  86 */           Bukkit.broadcastMessage("인원이 능력의 갯수보다 많습니다. Error 처리된분들은 능력을");
/*  87 */           Bukkit.broadcastMessage("받을수 없으며 모든 게임 진행 대상에서 제외됩니다.");
/*     */         }
/*  89 */         Bukkit.broadcastMessage(ChatColor.GOLD + "==========");
/*  90 */         if (MainScripter.PlayerList.size() == 0) {
/*  91 */           Bukkit.broadcastMessage(ChatColor.RED + 
/*  92 */             "경고, 실질 플레이어가 없습니다. 게임 강제 종료.");
/*  93 */           MainScripter.Scenario = MainScripter.ScriptStatus.NoPlay;
/*  94 */           S_GameReady.this.stimer.StopTimer();
/*  95 */           Bukkit.broadcastMessage(ChatColor.GRAY + "모든 설정이 취소됩니다.");
/*  96 */           MainScripter.PlayerList.clear(); return;
/*     */         }
/*     */         
/*     */         break;
/*     */       case 3: 
/* 101 */         Bukkit.broadcastMessage(String.format(ChatColor.DARK_RED + 
/* 102 */           "Physical Fighters", new Object[0]));
/*     */         
/* 104 */         Bukkit.broadcastMessage(String.format(
/* 105 */           ChatColor.RED + "VER. %d", new Object[] {
/* 106 */           Integer.valueOf(PhysicalFighters.BuildNumber) }));
/* 107 */         Bukkit.broadcastMessage(ChatColor.GREEN + "제작 : " + 
/* 108 */           ChatColor.WHITE + "염료");
/* 109 */         break;
/*     */       case 7: 
/* 111 */         if (!PhysicalFighters.NoAbilitySetting) {
/* 112 */           Bukkit.broadcastMessage(ChatColor.GRAY + 
/* 113 */             "능력 설정 초기화 및 추첨 준비...");
/* 114 */           for (AbilityBase ab : AbilityList.AbilityList) {
/* 115 */             ab.SetRunAbility(false);
/* 116 */             ab.SetPlayer(null, false);
/*     */           }
/*     */         } else {
/* 119 */           Bukkit.broadcastMessage(ChatColor.GOLD + "능력을 추첨하지 않습니다.");
/* 120 */           Bukkit.broadcastMessage("시작전에 능력이 이미 부여되었다면 보존됩니다.");
/* 121 */           S_GameReady.this.mainscripter.OKSign.clear();
/* 122 */           for (Player pl : MainScripter.PlayerList) {
/* 123 */             S_GameReady.this.mainscripter.OKSign.add(pl);
/*     */           }
/* 125 */           for (AbilityBase ab : AbilityList.AbilityList) {
/* 126 */             ab.SetRunAbility(true);
/*     */           }
/* 128 */           S_GameReady.this.mainscripter.s_GameStart.GameStart();
/* 129 */           StopTimer();
/*     */         }
/* 131 */         break;
/*     */       case 9: 
/* 133 */         MainScripter.Scenario = MainScripter.ScriptStatus.AbilitySelect;
/*     */         
/* 135 */         if (S_GameReady.this.peoplecount < AbilityBase.GetAbilityCount()) {
/* 136 */           for (Player p : MainScripter.PlayerList)
/* 137 */             if (RandomAbility(p) == null) {
/* 138 */               p.sendMessage(ChatColor.RED + "경고, 능력의 갯수가 부족합니다.");
/*     */             } else {
/* 140 */               p.sendMessage(ChatColor.GRAY + "(!)/va help " + 
/* 141 */                 ChatColor.WHITE + "= 능력 확인");
/* 142 */               p.sendMessage(ChatColor.YELLOW + "(!)/va yes " + 
/* 143 */                 ChatColor.WHITE + "= 능력 사용.");
/* 144 */               p.sendMessage(ChatColor.YELLOW + "(!)/va no " + 
/* 145 */                 ChatColor.WHITE + "= 능력 재추첨.(1회)");
/*     */             }
/* 147 */           for (Player p : S_GameReady.this.mainscripter.ExceptionList) {
/* 148 */             p.sendMessage(ChatColor.GREEN + "능력 추첨중입니다");
/*     */           }
/*     */           
/* 151 */           S_GameReady.this.mainscripter.s_GameWarnning.GameWarnningStart();
/*     */         } else {
/* 153 */           Bukkit.broadcastMessage(ChatColor.AQUA + 
/* 154 */             "능력 갯수보다 플레이어 수가 같거나 많으므로 즉시 확정됩니다.");
/* 155 */           for (Player p : MainScripter.PlayerList)
/* 156 */             if (RandomAbility(p) == null) {
/* 157 */               p.sendMessage(ChatColor.RED + "경고, 능력의 갯수가 부족합니다.");
/*     */             } else {
/* 159 */               S_GameReady.this.mainscripter.OKSign.add(p);
/* 160 */               p.sendMessage(ChatColor.GREEN + 
/* 161 */                 "당신에게 능력이 부여되었습니다. " + ChatColor.YELLOW + 
/* 162 */                 "/va help" + ChatColor.WHITE + "로 확인하세요.");
/*     */             }
/* 164 */           for (Player p : S_GameReady.this.mainscripter.ExceptionList) {
/* 165 */             p.sendMessage(ChatColor.GREEN + "능력 추첨 완료");
/*     */           }
/* 167 */           S_GameReady.this.mainscripter.s_GameStart.GameStart();
/*     */         }
/*     */         
/*     */ 
/*     */         break;
/*     */       }
/*     */       
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void EventEndTimer() {}
/*     */     
/*     */ 
/*     */     private AbilityBase RandomAbility(Player p)
/*     */     {
/* 183 */       ArrayList<AbilityBase> Alist = new ArrayList();
/* 184 */       Random r = new Random();
/* 185 */       int Findex = r.nextInt(AbilityList.AbilityList.size() - 1);
/* 187 */       int saveindex; if (Findex == 0) {
/* 188 */         saveindex = AbilityList.AbilityList.size();
/*     */       } else {
/* 190 */         saveindex = Findex - 1;
/*     */       }
/* 192 */       for (int i = 0; i < AbilityList.AbilityList.size(); i++) {
/* 193 */         if ((((AbilityBase)AbilityList.AbilityList.get(Findex)).GetPlayer() == null) && (
/* 194 */           (MainScripter.PlayerList.size() > 6) || 
/* 195 */           (AbilityList.AbilityList.get(Findex) != AbilityList.mirroring)))
/*     */         {
/* 197 */           Alist.add((AbilityBase)AbilityList.AbilityList.get(Findex));
/*     */         }
/*     */         
/* 200 */         Findex++;
/*     */         
/* 202 */         if (Findex == saveindex)
/*     */           break;
/* 204 */         if (Findex == AbilityList.AbilityList.size())
/* 205 */           Findex = 0;
/*     */       }
/* 207 */       if (Alist.size() == 0) {
/* 208 */         return null;
/*     */       }
/* 210 */       if (Alist.size() == 1) {
/* 211 */         ((AbilityBase)Alist.get(0)).SetPlayer(p, false);
/* 212 */         return (AbilityBase)Alist.get(0);
/*     */       }
/*     */       
/* 215 */       int ran2 = r.nextInt(Alist.size() - 1);
/* 216 */       ((AbilityBase)Alist.get(ran2)).SetPlayer(p, false);
/* 217 */       return (AbilityBase)Alist.get(ran2);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\Script\S_GameReady.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
/*     */ package Physical.Fighters.Script;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MajorModule.AbilityList;
/*     */ import Physical.Fighters.MajorModule.RestrictionTimer;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import java.util.logging.Logger;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ 
/*     */ public final class S_GameStart
/*     */ {
/*     */   private MainScripter ms;
/*  19 */   private S_ScriptTimer stimer = new S_ScriptTimer();
/*  20 */   public static int PlayDistanceBuffer = 0;
/*     */   
/*     */   public S_GameStart(MainScripter ms) {
/*  23 */     this.ms = ms;
/*     */   }
/*     */   
/*     */   public void GameStart() {
/*  27 */     this.stimer.StartTimer(15);
/*     */   }
/*     */   
/*     */   public void GameStartStop() {
/*  31 */     this.stimer.StopTimer();
/*  32 */     AbilityBase.restrictionTimer.StopTimer();
/*     */   }
/*     */   
/*     */   private void RespawnTeleport() {
/*  36 */     Location l = this.ms.gameworld.getSpawnLocation();
/*  37 */     l.setY(this.ms.gameworld.getHighestBlockYAt((int)l.getX(), 
/*  38 */       (int)l.getZ()));
/*     */     
/*  40 */     for (Player p : MainScripter.PlayerList) {
/*  41 */       p.setFoodLevel(20);
/*  42 */       p.setLevel(0);
/*  43 */       p.setExhaustion(0.0F);
/*  44 */       p.setExp(0.0F);
/*  45 */       p.setHealth((int) 20.0D);
/*  46 */       p.setSaturation(10.0F);
/*  47 */       if (!PhysicalFighters.NoClearInventory)
/*  48 */         p.getInventory().clear();
/*  49 */       if (PhysicalFighters.Respawn) {
/*  50 */         p.teleport(l);
/*     */       }
/*  52 */       if (PhysicalFighters.DefaultArmed) {
/*  53 */         p.getInventory().setHelmet(new ItemStack(302, 1));
/*  54 */         p.getInventory().setChestplate(new ItemStack(303, 1));
/*  55 */         p.getInventory().setLeggings(new ItemStack(316, 1));
/*  56 */         p.getInventory().setBoots(new ItemStack(317, 1));
/*  57 */         p.getInventory().setItem(0, new ItemStack(283, 1));
/*  58 */         p.getInventory().addItem(
/*  59 */           new ItemStack[] { new ItemStack(265, 64) });
/*  60 */         p.getInventory().addItem(
/*  61 */           new ItemStack[] { new ItemStack(266, 64) });
/*  62 */       } else if (!PhysicalFighters.NoClearInventory) {
/*  63 */         p.getInventory().setHelmet(null);
/*  64 */         p.getInventory().setChestplate(null);
/*  65 */         p.getInventory().setLeggings(null);
/*  66 */         p.getInventory().setBoots(null);
/*     */       }
/*  68 */       if (PhysicalFighters.MaxLevelSurvival) {
/*  69 */         p.setLevel(PhysicalFighters.Setlev);
/*     */       }
/*  71 */       if (PhysicalFighters.Kimiedition) {
/*  72 */         p.getInventory().setHelmet(new ItemStack(310, 1));
/*  73 */         p.getInventory().setChestplate(new ItemStack(311, 1));
/*  74 */         p.getInventory().setLeggings(new ItemStack(312, 1));
/*  75 */         p.getInventory().setBoots(new ItemStack(313, 1));
/*  76 */         p.getInventory().setItem(0, new ItemStack(276, 1));
/*  77 */         p.getInventory().addItem(
/*  78 */           new ItemStack[] { new ItemStack(265, 64) });
/*  79 */         p.getInventory().addItem(
/*  80 */           new ItemStack[] { new ItemStack(266, 64) });
/*  81 */         p.setLevel(500);
/*     */       }
/*  83 */       if (PhysicalFighters.Specialability) {
/*  84 */         Bukkit.broadcastMessage(ChatColor.GREEN + "인기있는 능력만 적용됩니다.");
/*  85 */         PhysicalFighters.Specialability = true;
/*     */       }
/*  87 */       if (PhysicalFighters.TableGive) {
/*  88 */         p.getInventory().addItem(
/*  89 */           new ItemStack[] { new ItemStack(116, 1) });
/*  90 */         p.getInventory().addItem(
/*  91 */           new ItemStack[] { new ItemStack(47, 64) });
/*     */       }
/*  93 */       if (PhysicalFighters.WoodGive) {
/*  94 */         p.getInventory().addItem(
/*  95 */           new ItemStack[] { new ItemStack(17, 64) });
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 101 */     if (PhysicalFighters.DefaultArmed) {
/* 102 */       Bukkit.broadcastMessage(ChatColor.GREEN + "기본 무장이 제공됩니다.");
/*     */     } else {
/* 104 */       Bukkit.broadcastMessage(ChatColor.RED + "기본 무장이 제공되지 않습니다.");
/*     */     }
/* 106 */     if (PhysicalFighters.MaxLevelSurvival) {
/* 107 */       Bukkit.broadcastMessage(ChatColor.GREEN + "만렙 서바이벌 모드입니다. 아이템 제공.");
/*     */     }
/*     */     
/* 110 */     for (Player p : this.ms.ExceptionList) {
/* 111 */       p.teleport(l);
/*     */     }
/*     */   }
/*     */   
/*     */   public final class S_ScriptTimer extends Physical.Fighters.MinerModule.TimerBase {
/*     */     public S_ScriptTimer() {}
/*     */     
/*     */     public void EventStartTimer() {
/* 119 */       MainScripter.Scenario = MainScripter.ScriptStatus.GameStart;
/*     */     }
/*     */     
/*     */     public void EventRunningTimer(int count) {
/* 123 */       switch (count) {
/*     */       case 0: 
/* 125 */         S_GameStart.this.ms.s_GameWarnning.GameWarnningStop();
/* 126 */         break;
/*     */       case 3: 
/* 128 */         Bukkit.broadcastMessage(ChatColor.WHITE + 
/* 129 */           "모든 플레이어들의 능력을 확정했습니다.");
/* 130 */         break;
/*     */       case 5: 
/* 132 */         Bukkit.broadcastMessage(ChatColor.YELLOW + "잠시 후 게임이 시작됩니다.");
/* 133 */         break;
/*     */       case 10: 
/* 135 */         Bukkit.broadcastMessage(ChatColor.GOLD + "5초 전 ");
/* 136 */         break;
/*     */       case 11: 
/* 138 */         Bukkit.broadcastMessage(ChatColor.GOLD + "4초 전");
/* 139 */         break;
/*     */       case 12: 
/* 141 */         Bukkit.broadcastMessage(ChatColor.GOLD + "3초 전 ");
/* 142 */         break;
/*     */       case 13: 
/* 144 */         Bukkit.broadcastMessage(ChatColor.GOLD + "2초 전");
/* 145 */         break;
/*     */       case 14: 
/* 147 */         Bukkit.broadcastMessage(ChatColor.GOLD + "1초 전");
/* 148 */         break;
/*     */       case 15: 
/* 150 */         Bukkit.broadcastMessage(ChatColor.GREEN + "게임이 시작되었습니다. ");
/* 151 */         int c = 0;
/* 152 */         PhysicalFighters.log.info("플레이어들의 능력");
/* 153 */         for (AbilityBase a : AbilityList.AbilityList) {
/* 154 */           if (a.GetPlayer() != null) {
/* 155 */             PhysicalFighters.log.info(String.format(
/* 156 */               "%d. %s - %s", 
/* 157 */               new Object[] { Integer.valueOf(c), 
/* 158 */               a.GetPlayer().getName(), 
/* 159 */               a.GetAbilityName() }));
/* 160 */             c++;
/*     */           }
/*     */         }
/* 163 */         PhysicalFighters.log.info("-------------------------");
/* 164 */         if (PhysicalFighters.Invincibility) {
/* 165 */           Bukkit.broadcastMessage("시작 직후 " + 
/* 166 */             String.valueOf(PhysicalFighters.EarlyInvincibleTime) + 
/* 167 */             "분간은 무적입니다.");
/* 168 */           Physical.Fighters.MainModule.EventManager.DamageGuard = true;
/*     */         } else {
/* 170 */           Bukkit.broadcastMessage(ChatColor.RED + "초반 무적은 작동하지 않습니다.");
/*     */         }
/*     */         
/* 173 */         if (PhysicalFighters.RestrictionTime != 0)
/*     */         {
/* 175 */           AbilityBase.restrictionTimer.StartTimer(PhysicalFighters.RestrictionTime * 60);
/*     */         } else {
/* 177 */           Bukkit.broadcastMessage(ChatColor.YELLOW + 
/* 178 */             "제약 카운트는 동작하지 않습니다.");
/*     */         }
/* 180 */         S_GameStart.this.RespawnTeleport();
/* 181 */         S_GameStart.PlayDistanceBuffer = MainScripter.PlayerList.size() * 50;
/* 182 */         java.util.List<World> w = Bukkit.getWorlds();
/* 183 */         for (World wl : w) {
/* 184 */           wl.setTime(1L);
/* 185 */           wl.setStorm(false);
/* 186 */           if (PhysicalFighters.AutoDifficultySetting)
/* 187 */             wl.setDifficulty(org.bukkit.Difficulty.EASY);
/* 188 */           wl.setWeatherDuration(0);
/* 189 */           wl.setSpawnFlags(wl.getAllowMonsters(), 
/* 190 */             !PhysicalFighters.NoAnimal);
/* 191 */           wl.setPVP(true);
/*     */         }
/* 193 */         for (AbilityBase b : AbilityList.AbilityList) {
/* 194 */           b.SetRunAbility(true);
/* 195 */           b.SetPlayer(b.GetPlayer(), false);
/*     */         }
/* 197 */         S_GameStart.this.ms.s_GameProgress.GameProgress();
/* 198 */         if (!PhysicalFighters.AutoCoordinateOutput) {
/*     */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public void EventEndTimer() {}
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\Script\S_GameStart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
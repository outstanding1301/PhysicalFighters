/*    */ package Physical.Fighters.MinerModule;
/*    */ 
/*    */ import Physical.Fighters.MainModule.AbilityBase;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*    */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*    */ import Physical.Fighters.PhysicalFighters;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public final class AUC
/*    */ {
/*    */   public static final void InfoTextOut(Player p)
/*    */   {
/*    */     AbilityBase a;
/* 16 */     if (Physical.Fighters.MajorModule.AbilityList.assimilation.GetPlayer() == p) {
/* 17 */       a = Physical.Fighters.MajorModule.AbilityList.assimilation;
/*    */     }
/*    */     else
/*    */     {
/* 21 */       a = AbilityBase.FindAbility(p);
/*    */     }
/*    */     
/* 24 */     if (a != null) {
/* 25 */       p.sendMessage(ChatColor.GREEN + "---------------");
/* 26 */       p.sendMessage(ChatColor.GOLD + "- 능력 정보 -");
/* 27 */       p.sendMessage(ChatColor.DARK_AQUA + "참고 : 능력 리스트중 가장 상단의 능력만 보여줍니다.");
/* 28 */       if (PhysicalFighters.ReverseMode) {
/* 29 */         p.sendMessage(ChatColor.AQUA + a.GetAbilityName() + ChatColor.WHITE + " [" + a.GetRank().GetText() + ChatColor.WHITE + "] ");
/*    */       }
/*    */       else
/* 32 */         p.sendMessage(ChatColor.AQUA + a.GetAbilityName() + ChatColor.WHITE + " [" + TypeTextOut(a) + "] " + a.GetRank().GetText());
/* 33 */       for (int l = 0; l < a.GetGuide().length; l++) {
/* 34 */         p.sendMessage(a.GetGuide()[l]);
/*    */       }
/* 36 */       if (!PhysicalFighters.ReverseMode)
/* 37 */         p.sendMessage(TimerTextOut(a));
/* 38 */       p.sendMessage(ChatColor.GREEN + "---------------");
/* 39 */       return;
/*    */     }
/* 41 */     p.sendMessage(ChatColor.RED + "능력이 없거나 옵저버입니다.");
/*    */   }
/*    */   
/*    */   public static final String TypeTextOut(AbilityBase ab) {
/* 45 */     AbilityBase.Type type = ab.GetAbilityType();
/* 46 */     if (!ab.GetRunAbility()) return ChatColor.RED + "능력 비활성화됨" + ChatColor.WHITE;
/* 47 */     if (type == AbilityBase.Type.Active_Continue) return ChatColor.GREEN + "액티브 " + ChatColor.WHITE + "/ " + ChatColor.GOLD + "지속" + ChatColor.WHITE;
/* 48 */     if (type == AbilityBase.Type.Active_Immediately) return ChatColor.GREEN + "액티브 " + ChatColor.WHITE + "/ " + ChatColor.GOLD + "즉발" + ChatColor.WHITE;
/* 49 */     if (type == AbilityBase.Type.Passive_AutoMatic) return ChatColor.GREEN + "패시브 " + ChatColor.WHITE + "/ " + ChatColor.GOLD + "자동" + ChatColor.WHITE;
/* 50 */     if (type == AbilityBase.Type.Passive_Manual) return ChatColor.GREEN + "패시브 " + ChatColor.WHITE + "/ " + ChatColor.GOLD + "수동" + ChatColor.WHITE;
/* 51 */     return "Unknown";
/*    */   }
/*    */   
/*    */   public static final String TimerTextOut(AbilityBase data) {
/* 55 */     if (data.GetAbilityType() == AbilityBase.Type.Active_Continue)
/* 56 */       return String.format(ChatColor.RED + "쿨타임 : " + ChatColor.WHITE + "%d초 / " + ChatColor.RED + "지속시간 : " + ChatColor.WHITE + "%d초", new Object[] { Integer.valueOf(data.GetCoolDown()), Integer.valueOf(data.GetDuration()) });
/* 57 */     if (data.GetAbilityType() == AbilityBase.Type.Active_Immediately)
/* 58 */       return String.format(ChatColor.RED + "쿨타임 : " + ChatColor.WHITE + "%d초 / " + ChatColor.RED + "지속시간 : " + ChatColor.WHITE + "없음", new Object[] { Integer.valueOf(data.GetCoolDown()) });
/* 59 */     if (data.GetAbilityType() == AbilityBase.Type.Passive_AutoMatic)
/* 60 */       return ChatColor.RED + "쿨타임 : " + ChatColor.WHITE + "없음 / " + ChatColor.RED + "지속시간 : " + ChatColor.WHITE + "없음";
/* 61 */     if (data.GetAbilityType() == AbilityBase.Type.Passive_Manual)
/* 62 */       return ChatColor.RED + "쿨타임 : " + ChatColor.WHITE + "없음 / " + ChatColor.RED + "지속시간 : " + ChatColor.WHITE + "없음";
/* 63 */     return "None";
/*    */   }
/*    */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\MinerModule\AUC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
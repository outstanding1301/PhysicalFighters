/*     */ package Physical.Fighters.AbilityList;
/*     */ 
/*     */ import Physical.Fighters.MainModule.AbilityBase;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Rank;
/*     */ import Physical.Fighters.MainModule.AbilityBase.Type;
/*     */ import Physical.Fighters.MainModule.CommandManager;
/*     */ import Physical.Fighters.MainModule.EventManager;
/*     */ import Physical.Fighters.MajorModule.AbilityList;
/*     */ import Physical.Fighters.MinerModule.CommandInterface;
/*     */ import Physical.Fighters.MinerModule.EventData;
/*     */ import Physical.Fighters.PhysicalFighters;
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ 
/*     */ public class Assimilation
/*     */   extends AbilityBase implements CommandInterface
/*     */ {
/*  25 */   private boolean ActiveAss = false;
/*     */   
/*     */   public Assimilation() {
/*  28 */     if (PhysicalFighters.SRankUsed) {
/*  29 */       InitAbility("흡수", AbilityBase.Type.Passive_Manual, AbilityBase.Rank.S, new String[] {
/*  30 */         "자신이 죽인 사람의 능력을 흡수합니다. 액티브 능력은", 
/*  31 */         "1개만 가능합니다. 미러링도 흡수가 가능하며 데스 노트의 경우", 
/*  32 */         "이미 능력을 썼더라도 다시 쓸수 있습니다. 자신에게 타격받은", 
/*  33 */         "사람은 배고픔이 빠르게 감소합니다. \"/va a\" 명령으로", 
/*  34 */         "자신이 흡수한 능력을 볼수 있습니다.", "흡수가 가능한 능력의 갯수에는 제한이 없습니다." });
/*  35 */       InitAbility(0, 0, true);
/*  36 */       EventManager.onEntityDamageByEntity.add(new EventData(this, 0));
/*  37 */       EventManager.onEntityDeath.add(new EventData(this, 1));
/*  38 */       cm.RegisterCommand(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public int A_Condition(Event event, int CustomData)
/*     */   {
/*  44 */     switch (CustomData) {
/*     */     case 0: 
/*  46 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*  47 */       if (PlayerCheck(Event0.getDamager()))
/*  48 */         return 0;
/*     */       break;
/*     */     case 1: 
/*  51 */       EntityDeathEvent Event1 = (EntityDeathEvent)event;
/*  52 */       if (((Event1.getEntity() instanceof Player)) && 
/*  53 */         (PlayerCheck(Event1.getEntity().getKiller())))
/*  54 */         return 1;
/*     */       break;
/*     */     }
/*  57 */     return -1;
/*     */   }
/*     */   
/*     */   public void A_Effect(Event event, int CustomData)
/*     */   {
/*  62 */     switch (CustomData) {
/*     */     case 0: 
/*  64 */       EntityDamageByEntityEvent Event0 = (EntityDamageByEntityEvent)event;
/*  65 */       if ((Event0.getEntity() instanceof Player)) {
/*  66 */         Player p = (Player)Event0.getEntity();
/*  67 */         p.setSaturation(0.0F);
/*     */       }
/*  69 */       break;
/*     */     case 1: 
/*  71 */       EntityDeathEvent Event1 = (EntityDeathEvent)event;
/*  72 */       if ((Event1.getEntity() instanceof Player)) {
/*  73 */         Player p = (Player)Event1.getEntity();
/*  74 */         AbilityBase a = AbilityBase.FindAbility(p);
/*  75 */         if (a != null) {
/*  76 */           a.AbilityCTimerCancel();
/*  77 */           a.AbilityDTimerCancel();
/*  78 */           if ((a.GetAbilityType() == AbilityBase.Type.Passive_AutoMatic) || 
/*  79 */             (a.GetAbilityType() == AbilityBase.Type.Passive_Manual)) {
/*  80 */             a.SetPlayer(GetPlayer(), false);
/*  81 */             GetPlayer().sendMessage(
/*  82 */               ChatColor.GREEN + "새로운 패시브 능력을 흡수하였습니다.");
/*  83 */             GetPlayer().sendMessage(
/*  84 */               ChatColor.YELLOW + "새로운 능력 : " + 
/*  85 */               ChatColor.WHITE + a.GetAbilityName());
/*  86 */           } else if (!this.ActiveAss) {
/*  87 */             a.SetPlayer(GetPlayer(), false);
/*  88 */             GetPlayer().sendMessage(
/*  89 */               ChatColor.GREEN + "새로운 액티브 능력을 흡수하였습니다.");
/*  90 */             GetPlayer().sendMessage(
/*  91 */               ChatColor.YELLOW + "새로운 능력 : " + 
/*  92 */               ChatColor.WHITE + a.GetAbilityName());
/*  93 */             GetPlayer().sendMessage(
/*  94 */               ChatColor.RED + "이제 액티브 흡수는 불가능합니다.");
/*  95 */             this.ActiveAss = true;
/*     */           } else {
/*  97 */             GetPlayer().sendMessage(
/*  98 */               ChatColor.RED + "흡수할수 없는 능력을 가지고 있었습니다.");
/*     */           }
/*     */         }
/*     */       }
/*     */       break;
/*     */     }
/*     */   }
/*     */   
/*     */   public void A_SetEvent(Player p)
/*     */   {
/* 108 */     this.ActiveAss = false;
/*     */   }
/*     */   
/*     */   public boolean onCommandEvent(CommandSender sender, Command command, String label, String[] data)
/*     */   {
/* 113 */     if (((sender instanceof Player)) && (PlayerCheck((Player)sender)) && 
/* 114 */       (data[0].equalsIgnoreCase("a")) && (data.length == 1)) {
/* 115 */       sender.sendMessage(ChatColor.GREEN + "-- 당신이 소유한 능력 --");
/* 116 */       for (AbilityBase a : AbilityList.AbilityList) {
/* 117 */         if (a.PlayerCheck(GetPlayer())) {
/* 118 */           GetPlayer().sendMessage(a.GetAbilityName());
/*     */         }
/*     */       }
/* 121 */       return true;
/*     */     }
/*     */     
/* 124 */     return false;
/*     */   }
/*     */ }


/* Location:              E:\플러그인\1.7.10모드능력자(95개).jar!\Physical\Fighters\AbilityList\Assimilation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */